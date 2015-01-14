package com.coutemeier.maven.artifactrules.plugin.configuration;



import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import com.thoughtworks.xstream.converters.extended.RegexPatternConverter;

import static com.coutemeier.utils.SpecialMessageConstants.*;

@XStreamConverter(value=SinglePatternConverter.class, strings={"artifacts"})
public final class Dependencies {
    private String id = "";
    private String type = "";
    private boolean overwrites = false;

    @XStreamImplicit
    @XStreamAlias("dependency")
    private final List<Pattern> dependencies = new ArrayList<Pattern>();

    @XStreamImplicit
    @XStreamAlias("scope")
    private List<String> scopes = new ArrayList<String>();

    public boolean isOverwrites() {
        return this.overwrites;
    }

    public List<Pattern> getDependencies() {
        return new ArrayList(this.dependencies);
    }

    public List<String> getScopes() {
        return new ArrayList(this.scopes);
    }

    @Override
    public String toString() {
        return new StringBuilder(128)
            .append(HEADER_3).append("       ID=").append(this.id)
            .append(HEADER_3).append("  REPLACE=").append(this.overwrites)
            .append(HEADER_3).append("ARTIFACTS=").append(this.dependencies)
            .append(HEADER_3).append("   SCOPES=").append(this.scopes)
            .append(HEADER_2)
            .toString();
    }
}