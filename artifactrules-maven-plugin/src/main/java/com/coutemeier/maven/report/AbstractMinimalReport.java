package com.coutemeier.maven.report;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributeSet;
import org.apache.maven.doxia.siterenderer.Renderer;
import org.apache.maven.model.ReportPlugin;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

/**
 * Abstraction of a minimal coutemeier report plugin.
 * @author rrialq
 * @since 1.0.0
 */
public abstract class AbstractMinimalReport extends AbstractMavenReport {

    /**
     * A constant to avoid generate individual instances.
     */
    private static final SinkEventAttributeSet SECTIONS_ATTR_SET = new SinkEventAttributeSet();

    /**
     * Output directory where files should be created at.
     */
    @Parameter(property = "project.reporting.outputDirectory")
    private File outputDirectory;



    /**
    * Maven Project
    */
    @Component
    private MavenProject project;


    /**
    * Doxia Site Renderer
    */
    @Component
    private Renderer siteRenderer;

    /**
     * Whether to build an aggregated report at the root, or build individual reports.
     */
    @Parameter(defaultValue = "false", property = "aggregate")
    private boolean aggregate;




    public final void executeReport( Locale locale )
    throws MavenReportException {
        if ( this.isSkipped() ) {
            return;
        }
        this.doGenerateReport( getBundle( locale ), getSink() );
    }

    /**
     * Whether the report should be generated or not.
     */
    protected boolean isSkipped() {
        return false;
    }

    /**
     * Whether the report should be generated when there are no test results.
     *
     * @return {@code true} if and only if the report should be generated when there are no result files at all.
     */
    protected boolean isGeneratedWhenNoResults() {
        return false;
    }

    protected Renderer getSiteRenderer() {
        return this.siteRenderer;
    }

    protected MavenProject getProject() {
        return this.project;
    }

    protected String getOutputDirectory() {
        return this.outputDirectory.getAbsolutePath();
    }

    protected void doGenerateSection1(final ResourceBundle bundle, final Sink sink, final String headerKey, final String bodyKey) {
        sink.section1();
        sink.sectionTitle1();
        sink.text( bundle.getString( headerKey ) );
        sink.sectionTitle1_();
        sink.paragraph();
        sink.text( bundle.getString( bodyKey ) );
        sink.paragraph_();
    }

    protected void doGenerateSection2(final ResourceBundle bundle, final Sink sink, final String headerKey, final String bodyKey) {
        sink.section2();
        sink.sectionTitle2();
        sink.text( bundle.getString( headerKey ) );
        sink.sectionTitle2_();
        sink.paragraph();
        sink.text( bundle.getString( bodyKey ) );
        sink.paragraph_();
    }

    protected void outputRowAndHeaderCells(final ResourceBundle bundle, final Sink sink, final String ... keys) {
        sink.tableRow();
        outputHeaderCells(bundle, sink, keys);
        sink.tableRow_();
    }

    protected void outputHeaderCells(final ResourceBundle bundle, final Sink sink, final String ... keys) {
        sink.tableRow();
        for(String key: keys) {
            sink.tableHeaderCell();
            sink.text(bundle.getString(key));
            sink.tableHeaderCell_();
        }
        sink.tableRow_();
    }

    protected void outputRowAndCells(final Sink sink, final String ... values) {
        sink.tableRow();
        outputCells(sink, values);
        sink.tableRow_();        
    }

    protected void outputCells(final Sink sink, final String ... values) {
        for(String value: values) {
            sink.tableCell();
            sink.text(value);
            sink.tableCell_();
        }
    }

    protected ResourceBundle getBundle( final Locale locale ) {
        return ResourceBundle.getBundle( "artifactrules-report", locale, this.getClass().getClassLoader() );
    }

    public abstract String getOutputName();
    public abstract String getName(final Locale locale);
    public abstract String getDescription(final Locale locale);
    protected abstract void doGenerateReport(ResourceBundle bundle, Sink sink);
}