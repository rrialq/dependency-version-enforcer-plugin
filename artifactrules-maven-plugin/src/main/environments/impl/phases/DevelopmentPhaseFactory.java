package com.coutemeier.maven.enforcer.environments.impl.phases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;

public final class DevelopmentPhaseFactory {
	private static final Logger LOGGER = Logger.getLogger(DevelopmentPhaseFactory.class.getName());
	
	public static DevelopmentPhasesManager parse(String phasesValue) 
	throws IOException {
		List<DevelopmentPhase> phases = new ArrayList<DevelopmentPhase>(6);
		Scanner scanner =  null;
		String phaseDefinition = null;

		try {
			scanner = new Scanner(phasesValue);
			while (scanner.hasNext()) {
				phaseDefinition = scanner.nextLine();
				LOGGER.finest("PHASE_DEFINITION = " + phaseDefinition);
				phases.add(DevelopmentPhaseFactory.newPhase(phaseDefinition, phases.size() * 100));
			}
			return new DevelopmentPhasesManagerImpl(phases);

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	public static DevelopmentPhase newPhase(final String phaseDefinition, int orderingValue)
	throws IOException {
		int index = phaseDefinition.indexOf('=');
		if (index == -1) {
			throw new IOException("Erro. " + phaseDefinition + " non é válido");
		}
		String key = phaseDefinition.substring(0, index);
		String pattern = phaseDefinition.substring(index + 1);
		return DevelopmentPhaseFactory.newPhase(key, pattern, orderingValue);
	}
	
	public static DevelopmentPhase newPhase(final String phase, final String pattern, int orderingValue) {
		return new DevelopmentPhaseImpl(phase, Pattern.compile(pattern), orderingValue);
	}
}