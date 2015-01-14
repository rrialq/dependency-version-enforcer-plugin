package com.coutemeier.maven.artifactrules.report;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.siterenderer.Renderer;
import org.apache.maven.model.ReportPlugin;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

import com.coutemeier.maven.report.AbstractMinimalReport;

/**
 * Abstraction of a minimal artifactrules report plugin.
 * @author rrialq
 * @since 1.0.0
 */

public abstract class ArtifactRulesAbstractReport extends AbstractMinimalReport {
    @Parameter(defaultValue="${project.build.directory}/artifactrules", property="resultsDirectory")
    private String resultsDirectory;

    @Parameter( defaultValue="artifactrules-results.xml", property="resultsFilename" )
    private String resultsFilename;


    @Override
    public String getName( Locale locale ) {
        return this.getBundle( locale ).getString( "report.artifactrules.name" );
    }

    @Override
    public String getDescription( Locale locale ) {
        return this.getBundle( locale ).getString( "report.artifactrules.description" );
    }


    /**
     * The output directory where the plugin generates the report and from where the report plugin reads the results file.
     * @String the directory where the results will be written and readed.
     * @since 1.0.0
     */
    protected String getResultsDirectory() {
        return this.resultsDirectory;
    }

    /**
     * The filename of results, to be written and to be readed.
     * @String the plugin writes the results to this file and the report plugin reads the results from it.
     * @since 1.0.0
     */
    protected String getResultsFilename() {
        return this.resultsFilename;
    }

    /**
     * The full name of the file normalized(from resultsDirectory and resultsFilename).
     * @return a text with the path of the filename normalized. The path can be absolute or relative.
     */
    public String getNormalizedResultsFilename() {
        final StringBuilder path = new StringBuilder(256).append(resultsDirectory);
        if (!resultsDirectory.endsWith("/")) {
            path.append('/');
        }
        return path.append(this.resultsFilename).toString();
    }
}