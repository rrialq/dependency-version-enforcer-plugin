package com.coutemeier.maven.enforcer.environments.api.phases;


public interface DevelopmentPhasesManager {
	public DevelopmentPhase find(String phase);
	public int indexOf(String phase);
	public DevelopmentPhase findFromVersion(String version);
}
