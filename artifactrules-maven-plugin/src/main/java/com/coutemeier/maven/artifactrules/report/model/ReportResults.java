package com.coutemeier.maven.artifactrules.report.model;

import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import com.thoughtworks.xstream.converters.basic.DateConverter;

import com.coutemeier.maven.artifactrules.report.model.SimpleArtifact;


@XStreamAlias("reportResults")
public final class ReportResults {
    @XStreamImplicit
    @XStreamAlias("ruleset")
    private List<RuleSet> ruleSets = Collections.EMPTY_LIST;


    @XStreamImplicit
    private List<SimpleArtifact> excluded = Collections.EMPTY_LIST;

    @XStreamImplicit
    private List<SimpleArtifact> offending = Collections.EMPTY_LIST;

    @XStreamImplicit
    private List<SimpleArtifact> banned = Collections.EMPTY_LIST;

    public List<RuleSet> getRuleSets() {
        return this.ruleSets;
    }

    public List<SimpleArtifact> getExcludedArtifacts() {
        return this.excluded;
    }

    public List<SimpleArtifact> getBannedArtifacts() {
        return this.banned;
    }

    public List<SimpleArtifact> getOffendingArtifacts() {
        return this.offending;
    }
}