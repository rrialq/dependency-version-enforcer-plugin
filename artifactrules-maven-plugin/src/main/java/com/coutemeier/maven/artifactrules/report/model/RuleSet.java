package com.coutemeier.maven.artifactrules.report.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class RuleSet {
    private String id;
    private String author;
    private String version;
    private Date published;
    private String description;

    private String url;

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getVersion() {
        return this.version;
    }

    public Date getPublished() {
        return this.published;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDescription() {
        return this.description;
    }
}