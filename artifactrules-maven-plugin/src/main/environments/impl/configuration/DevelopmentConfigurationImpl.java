package com.coutemeier.maven.enforcer.environments.impl.configuration;

import java.util.logging.Logger;

import org.apache.maven.plugin.logging.Log;

import com.coutemeier.maven.enforcer.environments.api.configuration.DevelopmentConfiguration;
import com.coutemeier.maven.enforcer.environments.api.environments.Environment;
import com.coutemeier.maven.enforcer.environments.api.environments.EnvironmentsManager;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;

public class DevelopmentConfigurationImpl implements DevelopmentConfiguration {
	private final DevelopmentPhasesManager phases;
	private final EnvironmentsManager environments;
	private static final Logger LOGGER = Logger.getLogger(DevelopmentConfigurationImpl.class.getName());


	
	public DevelopmentConfigurationImpl(DevelopmentPhasesManager phases, EnvironmentsManager environments) {
		this.phases = phases;
		this.environments = environments;
	}

	@Override
	public DevelopmentPhasesManager getPhasesManager() {
		return this.phases;
	}
	
	@Override
	public EnvironmentsManager getEnvironments() {
		return this.environments;
	}
	
	@Override
	public Environment findEnvironment(String environment) {
		return this.environments.find(environment);
	}

	@Override
	public DevelopmentPhase findPhase(String phase) {
		return phases.find(phase);
	}

	@Override
	public DevelopmentPhase findPhaseFromVersion(String version) {
		return this.phases.findFromVersion(version);
	}

	@Override
	public boolean isValidVersion(String version, String environment, Log logger) {
		Environment environmentt = this.findEnvironment(environment);
		DevelopmentPhase versionPhase = this.findPhaseFromVersion(version);
		//DevelopmentPhase environmentPhase = environmentt.getPhase();
		DevelopmentPhase environmentPhase = null;
		logger.info("Environment = " + environmentt);
		logger.info("versionPhase=" + versionPhase);
		logger.info("environmentPhase=" + environmentPhase);
		if (environmentt == null || versionPhase == null || environmentPhase == null) {
			return false;
		}
		return versionPhase.getOrderingValue() >= environmentPhase.getOrderingValue();
	}
	
	
}