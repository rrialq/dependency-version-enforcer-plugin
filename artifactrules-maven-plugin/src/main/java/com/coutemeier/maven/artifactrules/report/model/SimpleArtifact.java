package com.coutemeier.maven.artifactrules.report.model;

import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import com.coutemeier.utils.CollectionsUtils;

public final class SimpleArtifact {

    private String id;

    private String version;

    @XStreamImplicit
    @XStreamAlias("rule")
    private List<String> rules = Collections.EMPTY_LIST;

    public String getId() {
        return this.id;
    }

    public String getVersion() {
        return this.version;
    }

    public List<String> getRules() {
        return this.rules;
    }

    public String getRulesAsString() {
        return CollectionsUtils.asString(this.rules);
    }
}