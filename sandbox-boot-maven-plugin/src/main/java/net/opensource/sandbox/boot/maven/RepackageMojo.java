package net.opensource.sandbox.boot.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

@Mojo(name = "repackage", defaultPhase = LifecyclePhase.PACKAGE, requiresProject = true, threadSafe = true, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME, requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class RepackageMojo extends AbstractMojo {

    private final static String SANBOX = "META-INF/sandbox/sandbox-1.0.16-bin.zip";
    private final static String SANBOX_LOADER = "META-INF/sandbox-loader/sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar";

    /**
     * The Maven project.
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * Maven project helper utils.
     *
     * @since 1.0
     */
    @Component
    private MavenProjectHelper projectHelper;

    /**
     * Directory containing the generated archive.
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "${project.build.directory}", required = true)
    private File target;

    /**
     * The name of the main class. If not specified the first compiled class found that
     * contains a 'main' method will be used.
     *
     * @since 1.0
     */
    @Parameter
    private String premainClass;


    /**
     * Name of the generated archive.
     *
     * @since 1.0
     */
    @Parameter(defaultValue = "${project.build.finalName}", required = true)
    private String finalName;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello, world.");
        try {
            if (project.getPackaging().equals("pom")) {
                List<String> modules = project.getModules();
                return;
            }
            if (project.getPackaging().equals("jar")) {
                repackage();
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void repackage() {
        try {
            File sandbox_boot_agent = new File(target, "sandbox-boot-agent");
            sandbox_boot_agent.delete();
            sandbox_boot_agent.mkdir();

            //解压sandbox-1.0.16-bin.zip
            Enumeration<URL> sandboxResources = getClass().getClassLoader().getResources(SANBOX);
            while (sandboxResources.hasMoreElements()) {
                URL url = sandboxResources.nextElement();
                System.out.println(url);
                if (url.toString().contains("sandbox-1.0.16-bin.zip")) {
                }
            }

            //解压sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar
            Enumeration<URL> sandboxLoaderResources = getClass().getClassLoader().getResources(SANBOX_LOADER);
            while (sandboxLoaderResources.hasMoreElements()) {
                URL url = sandboxLoaderResources.nextElement();
                System.out.println(url);
                if (url.toString().contains("sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar")) {
                }
            }

            //把user-module.jar放到user-module目录下面，如果是pom，就把子包的jar全部放到user-module下面
            File user_modules_dir = new File(sandbox_boot_agent, "user-modules");
            user_modules_dir.delete();
            user_modules_dir.mkdir();

            //把上面两部解压的结果再archive to jar package
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
