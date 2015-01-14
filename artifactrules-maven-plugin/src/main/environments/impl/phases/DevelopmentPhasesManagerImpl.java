package com.coutemeier.maven.enforcer.environments.impl.phases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;

public class DevelopmentPhasesManagerImpl implements DevelopmentPhasesManager {
	private final List<DevelopmentPhase> phases;
	
	public DevelopmentPhasesManagerImpl(List<DevelopmentPhase> phases) {
		List<DevelopmentPhase> phasesCopy = new ArrayList<DevelopmentPhase>();
		if (phases != null) {
			phasesCopy.addAll(phases);
		}
		this.phases = Collections.unmodifiableList(phasesCopy); 
	}

	@Override
	public DevelopmentPhase find(String phase) {
		int index = indexOf(phase);
		if (index > -1) {
			return this.phases.get(index);
		}
		return null;
	}

	@Override
	public int indexOf(final String phase) {
		DevelopmentPhase developmentPhase;
		final String phaseId = phase.trim();
		for(int i=0; i < phases.size(); i++) {
			developmentPhase = phases.get(i);
			if (developmentPhase != null && phaseId.equalsIgnoreCase(developmentPhase.getId())) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public DevelopmentPhase findFromVersion(String version) {
		for(DevelopmentPhase phase: this.phases) {
			if (phase.matches(version)) {
				return phase;
			}
		}
		return null;
	}

}
