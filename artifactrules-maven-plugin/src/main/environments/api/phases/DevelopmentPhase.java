package com.coutemeier.maven.enforcer.environments.api.phases;

/**
 * <p>Fase de desarrollo.</p>
 * <p>La fase de desarrollo permiten establecer comprobar si los identificadores de versionado
 * de las dependencias cumplen o no cumplen con el esquema de versionado asociado.</p>
 * <p>La fase de desarrollo está asociada al entorno.</p>
 * @author rrial
 * @since 1.0.0
 */
public interface DevelopmentPhase extends Comparable<DevelopmentPhase> {
	public String getId();
	public boolean matches(String version);
	public int getOrderingValue();
}