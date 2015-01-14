package com.coutemeier.maven.artifactrules.report.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.List;

import java.util.logging.Logger;

import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.DomDriver;

import org.apache.maven.artifact.DefaultArtifact;

import com.coutemeier.maven.artifactrules.report.model.ReportResults;
import com.coutemeier.maven.artifactrules.report.model.SimpleArtifact;

import com.coutemeier.utils.IOUtils;

public enum ReportResultsDAO {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ReportResultsDAO.class.getName());

    public ReportResults importResults(final String filename) {
        final File file = new File(filename);
        InputStream is = null;

        if (file.exists()) {
            try {
                is = new FileInputStream(file);
                final XStream xstream = new XStream(new DomDriver());
                xstream.processAnnotations(ReportResults.class);

                return (ReportResults) xstream.fromXML(is);
            } catch (IOException cause) {

            } finally {
                IOUtils.closeSilently(is);
            }
        }
        LOGGER.warning("Archivo no encontrado: " + filename);
        return null;
    }
}