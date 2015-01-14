package com.coutemeier.maven.enforcer.environments.api.environments;


public interface EnvironmentsManager {
	public Environment find(String alias);
	public int indexOf(String alias);
	public Environment get(int index);
}
