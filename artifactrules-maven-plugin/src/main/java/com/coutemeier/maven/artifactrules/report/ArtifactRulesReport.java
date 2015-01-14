package com.coutemeier.maven.artifactrules.report;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.maven.doxia.sink.Sink;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.coutemeier.maven.artifactrules.report.dao.ReportResultsDAO;
import com.coutemeier.maven.artifactrules.report.model.RuleSet;
import com.coutemeier.maven.artifactrules.report.model.ReportResults;
import com.coutemeier.maven.artifactrules.report.model.SimpleArtifact;

@Mojo( name = "artifactrules", defaultPhase=LifecyclePhase.SITE)
public final class ArtifactRulesReport extends ArtifactRulesAbstractReport {
    /**
     * The filename to use for the report.
    */
    @Parameter(defaultValue = "artifactrules-report", property = "outputName", required = true)
    private String outputName;



    public String getOutputName() {
        return outputName;
    }

    public void doGenerateReport(final ResourceBundle bundle, final Sink sink) {
        final ReportResults results = ReportResultsDAO.INSTANCE.importResults(this.getNormalizedResultsFilename());
        sink.head();
        sink.title();
        sink.text( bundle.getString( "report.artifactrules.header" ) );
        sink.title_();
        sink.head_();
        sink.body();

        doGenerateSectionConfigurations(results, bundle, sink);

        doGenerateSectionOffendingArtifacts(results.getOffendingArtifacts(), bundle, sink);
        doGenerateSectionBannedArtifacts(results.getBannedArtifacts(), bundle, sink);
        doGenerateSectionExcludedArtifacts(results.getExcludedArtifacts(), bundle, sink);


        sink.body_();
        sink.head_();
    }

    private void doGenerateSectionConfigurations(final ReportResults results, final ResourceBundle bundle, final Sink sink) {
        this.doGenerateSection1(bundle, sink, "configurations.header", "configurations.body");

        sink.table();
        this.outputRowAndHeaderCells(bundle, sink, "id", "author", "description", "url", "published");
        for(RuleSet rule: results.getRuleSets()) {
            this.outputRowAndCells(sink, rule.getId(), rule.getAuthor(), rule.getDescription(), rule.getUrl(), rule.getUrl());
        }
        sink.table_();
        sink.section1_();
    }

    /**
     * OBSOLETED
    private void doGenerateArtifactsSections(final ReportResults results, final ResourceBundle bundle, final Sink sink) {
        this.doGenerateSection1(bundle, sink, "artifacts.in.problems.header", "artifacts.in.problems.body");

        doGenerateSectionOffendingArtifacts(results.getOffendingArtifacts(), bundle, sink);
        doGenerateSectionBannedArtifacts(results.getBannedArtifacts(), bundle, sink);
        doGenerateSectionExcludedArtifacts(results.getExcludedArtifacts(), bundle, sink);
        sink.section1_();
    }
     */

    private void doGenerateSectionOffendingArtifacts(final List<SimpleArtifact> artifacts, final ResourceBundle bundle, final Sink sink) {
        doGenerateSectionArtifacts(artifacts, bundle, sink, "offending.artifacts.header", "offending.artifacts.body");
    }

    private void doGenerateSectionBannedArtifacts(final List<SimpleArtifact> artifacts, final ResourceBundle bundle, final Sink sink) {
        doGenerateSectionArtifacts(artifacts, bundle, sink, "banned.artifacts.header", "banned.artifacts.body");
    }

    private void doGenerateSectionExcludedArtifacts(final List<SimpleArtifact> artifacts, final ResourceBundle bundle, final Sink sink) {
        doGenerateSectionArtifacts(artifacts, bundle, sink, "excluded.artifacts.header", "excluded.artifacts.body");
    }

    private void doGenerateSectionArtifacts(final List<SimpleArtifact> artifacts, final ResourceBundle bundle, final Sink sink, final String headerKey, final String descriptionKey) {
        this.doGenerateSection1(bundle, sink, headerKey, descriptionKey);
        sink.table();
        outputRowAndHeaderCells(bundle, sink, "artifact", "version", "configuration");
        for(SimpleArtifact artifact: artifacts) {
            outputRowAndCells(sink, artifact.getId(), artifact.getVersion(), artifact.getRulesAsString());
        }
        sink.table_();
        sink.section1_();
    }
}