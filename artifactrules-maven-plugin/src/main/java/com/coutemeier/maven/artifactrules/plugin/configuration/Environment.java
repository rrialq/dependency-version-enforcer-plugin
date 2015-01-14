package com.coutemeier.maven.artifactrules.plugin.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Arrays;

import org.apache.maven.model.Dependency;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import static com.coutemeier.utils.SpecialMessageConstants.*;

/**
 *     <li>When <code>environment/inherited = false</code> the previous environment configuration is deleted.</li>
 *     <li>When <code>environment/inherited = true</code> the previous environment configuration is merged
 *         with the current environment configuration (if exists).</li>
 */
@XStreamAlias("environment")
public final class Environment {
    private String id;
    private boolean inherited = false;

    @XStreamImplicit
    @XStreamAlias("alias")
    private final List<String> aliases = Collections.EMPTY_LIST;

    @XStreamImplicit
    private final List<Dependencies> dependencies = Collections.EMPTY_LIST;

    @XStreamImplicit
    @XStreamAlias("include")
    private final List<Dependencies> include = Collections.EMPTY_LIST;

    @XStreamImplicit
    @XStreamAlias("exclude")
    private final List<Dependencies> exclude = Collections.EMPTY_LIST;

    @XStreamImplicit
    @XStreamAlias("dependency")
    private final List<Dependency> excluded = Collections.EMPTY_LIST;    

    public String getId() {
        return this.id;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public boolean isInherited() {
        return this.inherited;
    }

    public List<Dependencies> getDependencies() {
        return this.dependencies;
    }

    public boolean appliesTo(String artifact) {
        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder(256)
            .append(HEADER_2).append("          ID=").append(id).append('(').append(this.aliases).append(')')
            .append(HEADER_2).append("  INHERITED=").append(this.inherited)
            .append(HEADER_2).append("DEPENDENCIES=").append(this.dependencies)
            .append(HEADER_2).append("M DEPENDENCY=").append(this.excluded)
            .toString();
    }
}