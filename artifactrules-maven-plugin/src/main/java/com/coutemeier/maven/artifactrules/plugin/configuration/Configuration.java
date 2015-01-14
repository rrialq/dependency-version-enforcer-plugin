package com.coutemeier.maven.artifactrules.plugin.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import com.thoughtworks.xstream.converters.basic.DateConverter;

import static com.coutemeier.utils.SpecialMessageConstants.*;

import com.coutemeier.maven.artifactrules.plugin.configuration.Configuration;
import com.coutemeier.maven.artifactrules.plugin.configuration.ConfigurationUtils;

/*
 * <p>A configuration is a set of environment configurations.</p>
 * <p>Configurations can be inherited, so a configuration can be merged with other configurations.</p>
 * <p>Every configuration file of the rules is reflected in a corresponding instance of this class.</p>
 * <p>The merge procedure of the configurations follows the rules:</p>
 * <ol>
 *     <li>configuration/inherited = true</li>
 *     <li>The current configuration fields id, author, version and published replaced with the new values.</li>
 * </ol>
*/

@XStreamAlias("environments")
public final class Configuration {
    /**
      *
     */

    private String id = "";
    private String author = "";
    private String version = "";


    private final Date published = null;

    /**
     * The new configuration can inherit the current configuration, making it easy to extend the rules.
     */
    private boolean inherited = true;

    @XStreamImplicit
    @XStreamAlias("environment")
    private List<Environment> environments = Collections.EMPTY_LIST;

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

    public boolean isInherited() {
        return this.inherited;
    }

    @Override
    public String toString() {
        return new StringBuilder(128)
            .append(HEADER_1).append("CONFIGURATION=")
            .append(HEADER_1).append("          ID=").append(this.id)
            .append(HEADER_1).append("      AUTHOR=").append(this.author)
            .append(HEADER_1).append("     VERSION=").append(this.version)
            .append(HEADER_1).append("   PUBLISHED=").append(this.published)
            .append(HEADER_1).append("   INHERITED=").append(this.inherited)
            .append(HEADER_1).append("ENVIRONMENTS=").append(this.environments)
            .toString();
    }
}