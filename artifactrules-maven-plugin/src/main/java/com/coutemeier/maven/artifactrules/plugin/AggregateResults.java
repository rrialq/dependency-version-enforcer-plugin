package com.coutemeier.maven.plugin.artifactrules;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Says "Hi" to the user.
 * @goal aggregate-results
 * @phase site
 * @threadsafe
 *
 */
public class AggregateResults extends AbstractMojo {
    public void execute() throws MojoExecutionException {
        getLog().info( "AggregateResults" );
    }
}