package net.opensource.boot.loader.agent;

import org.springframework.boot.loader.ExecutableArchiveLauncher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.jar.JarFile;

import java.lang.instrument.Instrumentation;
import java.util.jar.Manifest;

public class AgentLauncher extends ExecutableArchiveLauncher {

    static final String BOOT_INF_LIB = "sandbox/lib/";

    private void launch(String featureString, Instrumentation inst) throws Exception {
        JarFile.registerUrlProtocolHandler();
        ClassLoader classLoader = createClassLoader(getClassPathArchives());
        launch(new Object[]{featureString, inst}, getPremainClass(), classLoader);
    }

    /**
     * Launch the application given the archive file and a fully configured classloader.
     *
     * @param args         the incoming arguments
     * @param premainClass the main class to run
     * @param classLoader  the classloader
     * @throws Exception if the launch fails
     */
    protected void launch(Object[] args, String premainClass, ClassLoader classLoader)
            throws Exception {
        Thread.currentThread().setContextClassLoader(classLoader);
        createPremainMethodRunner(premainClass, args, classLoader).run();
    }

    /**
     * Create the {@code MainMethodRunner} used to launch the application.
     *
     * @param premainClass the main class
     * @param args         the incoming arguments
     * @param classLoader  the classloader
     * @return the main method runner
     */
    protected PreMainMethodRunner createPremainMethodRunner(String premainClass, Object[] args, ClassLoader classLoader) {
        return new PreMainMethodRunner(premainClass, args);
    }


    /**
     * Returns the main class that should be launched.
     *
     * @return the name of the main class
     * @throws Exception if the main class cannot be obtained
     */
    protected String getPremainClass() throws Exception {
        Manifest manifest = getArchive().getManifest();
        String mainClass = null;
        if (manifest != null) {
            mainClass = manifest.getMainAttributes().getValue("Start-Premain-Class");
        }
        if (mainClass == null) {
            throw new IllegalStateException("No 'Start-Class' manifest entry specified in " + this);
        }
        return mainClass;
    }


    @Override
    protected boolean isNestedArchive(Archive.Entry entry) {
        if (entry.isDirectory()) {
            return false;
        }
        return entry.getName().startsWith(BOOT_INF_LIB);
    }

    /**
     * 启动加载
     *
     * @param featureString 启动参数
     *                      [namespace,prop]
     * @param inst          inst
     */
    public static void premain(final String featureString, final Instrumentation inst) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("start boot agent loader");
                    new AgentLauncher().launch(featureString, inst);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
