package com.coutemeier.maven.plugin.artifactrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


import org.apache.maven.artifact.Artifact;

import org.apache.maven.model.Dependency;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import org.apache.maven.project.MavenProject;


import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import com.coutemeier.maven.artifactrules.plugin.configuration.Configuration;
import com.coutemeier.maven.artifactrules.plugin.configuration.ConfigurationUtils;

/**
 * Se encarga de generar el fichero con los incumplimientos.
 */
@Mojo(name="follow-rules", defaultPhase=LifecyclePhase.PACKAGE, requiresProject=true, requiresDependencyResolution=ResolutionScope.COMPILE)
public class FollowRules extends AbstractMojo {
    public static final String FOLLOW_RULES_FOR_ENVIRONMENT_KEY = "follow.rules.for.environment";

    public static final List<String> CONFIGURATION_FILENAMES_DEFAULT = Arrays.asList("artifactrules-default-configuration.xml");

    /**
     * As mensaxes que emite o plugin.
     */
    private final ResourceBundle bundle = ResourceBundle.getBundle( "artifactrules");

    /**
     * Maven Project
     */
    @Parameter( property = "project", required = true, readonly = true )
    private MavenProject project;

    @Parameter( property = "follow-transitives", defaultValue = "true" )
    private boolean followTransitiveDependencies;

    @Parameter( property = "environment", defaultValue = "development" )
    private String environment;

    @Parameter( property = "failBuildOnError", defaultValue = "true" )
    private boolean failBuildOnError;

    /**
     * A list of configuration files with the definition rules of the environments.
     * It is not mandatory, so if the default definition rules is good for you, you can forget it.
     */
    @Parameter( property = "configuration", required = false)
    private ArrayList<String> configurationFilenames;

    public void execute() throws MojoExecutionException {
        getLog().info( bundle.getString( FOLLOW_RULES_FOR_ENVIRONMENT_KEY ) + environment );

        // project.getDependencyArtifacts() = dependencias del proyecto (excluidas transitivas)
        final List<Dependency> dependencies = getDependenciesToCheck( project );
        getLog().info(dependencies.toString());
        for(Dependency artifact: dependencies) {
            getLog().info(artifact.getArtifactId() + ' ' + artifact.getVersion() + " REAL");
        }

        getLog().info("-------------------------------------------------------------------------");

        // Dependencias transitivas del proyecto. Requiere @Mojo.requiresDependencyResolution
        final Set<Artifact> artifacts = (Set<Artifact>) project.getArtifacts();
        for(Artifact artifact : artifacts) {
            getLog().info(artifact.getArtifactId() + " " + artifact.getVersion() + " " + artifact.getScope() + " TRANSITIVE");
        }

        for(String filename: this.getConfigurationFilenames()) {
            Configuration config = ConfigurationUtils.readConfiguration(filename);
            getLog().info("CONFIGURACION=" + config.toString());
        }
    }


    @SuppressWarnings("unchecked")
    protected List<Dependency> getDependenciesToCheck(MavenProject project) {
        Set<Artifact> dependencies = null;
        if (followTransitiveDependencies) {
            dependencies = project.getArtifacts();
        } else {
            dependencies = project.getDependencyArtifacts();
        }

        //return dependencies;
        return project.getDependencies();
    }


    public void setConfigurationFilenames(final ArrayList<String> configurationFilenames) {
// This way we have a default value for a multivalued parameter.
// Because of a bug in Maven 2.2.1, can not define the parameter as List<String>.
        this.configurationFilenames = new ArrayList<String>();
        if (configurationFilenames == null || configurationFilenames.size() == 0) {
            this.configurationFilenames.addAll(CONFIGURATION_FILENAMES_DEFAULT);
        } else {
            this.configurationFilenames.addAll(configurationFilenames);
        }
    }

    public List<String> getConfigurationFilenames() {
        return this.configurationFilenames;
    }
}