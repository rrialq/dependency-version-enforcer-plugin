package com.coutemeier.maven.enforcer.environments.impl.environments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.coutemeier.maven.enforcer.environments.api.environments.Environment;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;

public final class EnvironmentImpl implements Environment {
	private final String[] alias;
	private final String id;

	/*
	 * Minimal valid developmentPhase for current environment.
	 */
	private final List<DevelopmentPhase> phases;
	
	public EnvironmentImpl(List<DevelopmentPhase> phases, final String... alias) {
		final String[] aliases = Arrays.copyOf(alias, alias.length);
		Arrays.sort(aliases);
		for(int i=0; i < aliases.length; i++) {
			aliases[i] = aliases[i].trim();
		}
		this.alias = aliases;
		this.id = alias[0];
		final List<DevelopmentPhase> localPhases = new ArrayList<DevelopmentPhase>();
		localPhases.addAll(phases);
		this.phases = localPhases;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public List<DevelopmentPhase> getPhases() {
		return this.phases;
	}
	
	@Override
	public boolean isEnvironment(String environment) {
		return Arrays.binarySearch(this.alias, environment.trim()) > -1;
	}
}