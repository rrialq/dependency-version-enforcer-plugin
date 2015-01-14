package com.coutemeier.maven.enforcer.environments.impl.environments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coutemeier.maven.enforcer.environments.api.environments.Environment;
import com.coutemeier.maven.enforcer.environments.api.environments.EnvironmentsManager;

public class EnvironmentManagerImpl implements EnvironmentsManager {
	private final List<Environment> environments;
	
	public EnvironmentManagerImpl(List<Environment> environments) {
		List<Environment> environmentsCopy = new ArrayList<Environment>();
		if (environments != null) {
			environmentsCopy.addAll(environments);
		}
		this.environments = Collections.unmodifiableList(environmentsCopy); 
	}

	@Override
	public Environment find(String alias) {
		int index = this.indexOf(alias);
		if (index > -1) {
			return this.environments.get(index);
		}
		return null;
	}

	@Override
	public int indexOf(String alias) {
		Environment environment;
		for(int i=0; i < this.environments.size(); i++) {
			environment = this.environments.get(i);
			if (environment != null && environment.isEnvironment(alias)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public Environment get(int index) {
		return this.environments.get(index);
	}

}
