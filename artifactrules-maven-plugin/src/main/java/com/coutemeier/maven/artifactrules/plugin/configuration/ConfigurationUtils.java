package com.coutemeier.maven.artifactrules.plugin.configuration;

import java.io.InputStream;
import java.io.IOException;

import java.util.List;

import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.DomDriver;

public enum ConfigurationUtils {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ConfigurationUtils.class.getName());

    public static List<Configuration> readConfiguration(List<String> filenames) {
        return null;
    }

    public static Environment readEnvironment(String filename) {
        final InputStream is = ConfigurationUtils.class.getResourceAsStream(filename);
        if (is != null) {
            final XStream xstream = new XStream(new DomDriver());
            xstream.processAnnotations(Environment.class);
            try {
                return (Environment) xstream.fromXML(is);

            } finally {
                try {
                    is.close();
                } catch (IOException cause) {}
            }
        } else {
            LOGGER.warning("Archivo no encontrado: " + filename);
        }
        return null;
    }

    public static Configuration readConfiguration(String filename) {
        final InputStream is = ConfigurationUtils.class.getResourceAsStream(filename);
        if (is != null) {
            final XStream xstream = new XStream(new DomDriver());
            xstream.processAnnotations(Configuration.class);
            try {
                return (Configuration) xstream.fromXML(is);

            } finally {
                try {
                    is.close();
                } catch (IOException cause) {
                    cause.printStackTrace();
                }
            }
        } else {
            LOGGER.warning("Archivo no encontrado: " + filename);
        }
        return null;
    }
}