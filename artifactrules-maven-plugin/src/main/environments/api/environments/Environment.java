package com.coutemeier.maven.enforcer.environments.api.environments;

import java.util.List;

import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;

/**
 * Representa uno de los posibles entornos configurables.
 * @author rrial
 * @since 1.0.0
 */
public interface Environment {
	/**
	 * <p>Identificador del entorno.</p>
	 * <p>Ejemplos de identificadores son: <b>alfa</b>, <b>beta</b>, <b>rc</b>...</p>
	 * @return un texto con el identificador del entorno.
	 * @since 1.0.0
	 */
	public String getId();

	/**
	 * Informa acerca de si el entorno con el entorno actual.
	 * @param environment el identificador del entorno por el que preguntamos si se corresponde con este.
	 * @return <b>true</b> cuando el parámetro se aplica al entorno, <b>false</b> cuando no se aplica.
	 * @since 1.0.0
	 */
	public boolean isEnvironment(String environment);

	/**
	 * Fases admitidas para el entorno de desarrollo.
	 * @return las fases de desarrollo que se permiten en el entorno.
	 * @since 1.0.0
	 */
	public List<DevelopmentPhase> getPhases();
}