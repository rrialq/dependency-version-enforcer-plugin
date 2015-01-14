package com.coutemeier.maven.enforcer.environments.impl.environments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.coutemeier.maven.enforcer.environments.api.environments.Environment;
import com.coutemeier.maven.enforcer.environments.api.environments.EnvironmentsManager;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;
import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhasesManager;
import com.coutemeier.maven.enforcer.environments.impl.phases.DevelopmentPhaseFactory;

public class EnvironmentFactory {
	private static final Logger LOGGER = Logger.getLogger(DevelopmentPhaseFactory.class.getName());

	public static EnvironmentsManager parse(String environmentsValue, DevelopmentPhasesManager phases) 
	throws IOException {
		List<Environment> developmentPhases = new ArrayList<Environment>(5);
		Scanner scanner =  null;
		String definition = null;

		try {
			scanner = new Scanner(environmentsValue);
			while (scanner.hasNext()) {
				definition = scanner.nextLine();
				developmentPhases.add(EnvironmentFactory.newEnvironment(phases, definition));
			}
			return new EnvironmentManagerImpl(developmentPhases);

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	public static Environment newEnvironment(final DevelopmentPhasesManager phases, final String environmentDefinition)
	throws IOException {
		int index = environmentDefinition.indexOf('=');
		if (index == -1) {
			throw new IOException("Erro. EnvironmentDefinition [" + environmentDefinition + "] non é válido");
		}
		String key = environmentDefinition.substring(0, index);
		String developmentPhase = environmentDefinition.substring(index + 1).trim();
		LOGGER.info("newEnvironment(" + key + ", " + developmentPhase + ")");
		return EnvironmentFactory.newEnvironment(phases, key, developmentPhase);
	}

	public static Environment newEnvironment(final DevelopmentPhasesManager phases, final String environment, final String phase) {
		DevelopmentPhase phasez = phases.find(phase);

		if (phasez == null) {
			LOGGER.info("FASE NON ATOPADA");
		}
		//return new EnvironmentImpl(phases.find(phase), environment.trim().split(","));
		return null;
	}
}
