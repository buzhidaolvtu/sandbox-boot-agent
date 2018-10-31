package net.opensource.sandbox.boot.maven;

import net.opensource.freedom.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.archiver.util.DefaultFileSet;
import org.codehaus.plexus.archiver.zip.ZipArchiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
                List<Dependency> dependencies = project.getDependencies();
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
            File sandbox_boot_agent_home = new File(target, "sandbox-boot-agent");
            sandbox_boot_agent_home.delete();
            sandbox_boot_agent_home.mkdirs();

            //解压sandbox-1.0.16-bin.zip
            Enumeration<URL> sandboxResources = getClass().getClassLoader().getResources(SANBOX);
            URL sandboxUrl = sandboxResources.nextElement();
            ZipUtils.unzip(sandboxUrl.openStream(), sandbox_boot_agent_home);

            //解压sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar
            Enumeration<URL> sandboxLoaderResources = getClass().getClassLoader().getResources(SANBOX_LOADER);
            URL sandboxLoaderUrl = sandboxLoaderResources.nextElement();
            ZipUtils.unzip(sandboxLoaderUrl.openStream(), sandbox_boot_agent_home);

            //把user-module.jar放到user-module目录下面，如果是pom，就把子包的jar全部放到user-module下面
            File user_modules_dir = new File(sandbox_boot_agent_home, "user-modules");
            user_modules_dir.delete();
            user_modules_dir.mkdirs();

            File[] files = target.listFiles();
            File jarFile = findJarFile(files);
            FileInputStream input = new FileInputStream(jarFile);
            FileOutputStream output = new FileOutputStream(new File(user_modules_dir, jarFile.getName()));
            IOUtils.copyLarge(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            ZipArchiver archiver = new ZipArchiver();
            archiver.setCompress(false);
            archiver.addFileSet(DefaultFileSet.fileSet(sandbox_boot_agent_home));
            archiver.setIncludeEmptyDirs(true);
            archiver.setDestFile(new File(sandbox_boot_agent_home, "sandbox-boot-agent.jar"));
            archiver.createArchive();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File findJarFile(java.io.File[] files) {
        for (File file : files) {
            if (file.getName().contains("jar-with-dependencies")) {
                return file;
            }
        }
        return null;
    }
}
