package com.coutemeier.maven.enforcer.environments.api.configuration;



import org.apache.maven.plugin.logging.Log;

import com.coutemeier.maven.enforcer.environments.api.environments.Environment;
import com.coutemeier.maven.enforcer.environments.api.environments.EnvironmentsManager;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;

public interface DevelopmentConfiguration {
	public DevelopmentPhasesManager getPhasesManager();
	public EnvironmentsManager getEnvironments();
	
	public Environment findEnvironment(String environment);
	public DevelopmentPhase findPhase(String phase);
	public DevelopmentPhase findPhaseFromVersion(String version);
	
	public boolean isValidVersion(String version, String environment, Log logger);
}