package com.coutemeier.maven.enforcer.environments.impl.phases;

import java.util.regex.Pattern;

import com.coutemeier.maven.enforcer.environments.api.phases.DevelopmentPhase;

public final class DevelopmentPhaseImpl implements DevelopmentPhase {
	private final int orderingValue;
	private final Pattern pattern;
	private final String id;
	private final String internalString;

	protected DevelopmentPhaseImpl(final String id, final Pattern pattern, final int orderingValue) {
		this.id = id.trim();
		this.orderingValue = orderingValue;
		this.pattern = pattern;
		internalString = id + "(" + orderingValue + ")=" + pattern.pattern();
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getOrderingValue() {
		return this.orderingValue;
	}

	@Override
	public int compareTo(DevelopmentPhase comparable) {
		if (comparable == null) {
			throw new NullPointerException();
		}
		return this.orderingValue - this.getOrderingValue();
	}
	
	@Override
	public boolean matches(String version) {
		return pattern.matcher(version).matches();
	}
	
	@Override
	public String toString() {
		return this.internalString;
	}
}