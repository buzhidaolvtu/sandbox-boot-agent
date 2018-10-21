package net.opensource.sandbox.boot.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import java.io.File;
import java.util.List;

@Mojo(name = "repackage", defaultPhase = LifecyclePhase.PACKAGE, requiresProject = true, threadSafe = true, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME, requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class RepackageMojo extends AbstractMojo {

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
        if (project.getPackaging().equals("pom")) {
            List<String> modules = project.getModules();
            return;
        }
        if (project.getPackaging().equals("jar")) {
            return;
        }
    }
}
