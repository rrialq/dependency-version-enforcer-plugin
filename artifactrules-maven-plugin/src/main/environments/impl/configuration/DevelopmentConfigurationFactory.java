package com.coutemeier.maven.enforcer.environments.impl.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.coutemeier.maven.enforcer.environments.api.configuration.DevelopmentConfiguration;
import com.coutemeier.maven.enforcer.environments.api.environments.EnvironmentsManager;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;
import com.coutemeier.maven.enforcer.environments.impl.environments.EnvironmentFactory;
import com.coutemeier.maven.enforcer.environments.impl.phases.DevelopmentPhaseFactory;

public final class DevelopmentConfigurationFactory {
	public static final String KEY_PHASES = "phases";
	public static final String KEY_ENVIRONMENTS = "environments";
	public static final String DEFAULT_CONFIGURATION_FILENAME = "/enforcer-environment.properties";

	private static final Logger LOGGER = Logger.getLogger(DevelopmentConfigurationFactory.class.getName());

	public static final DevelopmentConfiguration readEnvironmentsConfiguration()
	throws IOException {
		return DevelopmentConfigurationFactory.readEnvironmentsConfiguration(DevelopmentConfigurationFactory.DEFAULT_CONFIGURATION_FILENAME);
	}
	
	public static final DevelopmentConfiguration readEnvironmentsConfiguration(final String filename) 
	throws IOException {
		final Properties p = new Properties();
		InputStream is = DevelopmentConfigurationFactory.class.getResourceAsStream(filename == null ? DevelopmentConfigurationFactory.DEFAULT_CONFIGURATION_FILENAME : filename);
		try {
			if (is != null) {
				p.load(is);
				DevelopmentPhasesManager phases = DevelopmentPhaseFactory.parse(p.getProperty(DevelopmentConfigurationFactory.KEY_PHASES));
				EnvironmentsManager environments = EnvironmentFactory.parse(p.getProperty(DevelopmentConfigurationFactory.KEY_ENVIRONMENTS), phases);
				return newConfiguration(phases, environments);

			} else {
				throw new IOException("Non atopei o arquivo");
			}
		}finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException cause) {
				
			}
		}
	}
	
	public static final DevelopmentConfiguration newConfiguration(DevelopmentPhasesManager phases, EnvironmentsManager environments) {
		return new DevelopmentConfigurationImpl(phases, environments);
	}
	
}
