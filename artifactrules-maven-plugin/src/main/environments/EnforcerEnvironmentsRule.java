package com.coutemeier.maven.enforcer.environments;

import java.io.IOException;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;

import com.coutemeier.maven.enforcer.rules.environments.configuration.ConfigurationUtils;

public class EnforcerEnvironmentsRule implements EnforcerRule {
	/**
	 * Specify if transitive dependencies should be searched (default) or only
	 * look at direct dependencies.
	 */
	private boolean searchTransitive = true;
	private String environment;
	private String configurationFilename;

	public void execute(EnforcerRuleHelper helper) throws EnforcerRuleException {
		helper.getLog().info("------------------------------------------------------");
		helper.getLog().info(this.getClass().getCanonicalName());
		helper.getLog().info("Comprobando se a versión das dependencias se axusta á contorna");
		if (environment == null) {
			throw new EnforcerRuleException("Non se especificou a contorna");
		}
		
		final Environments config;
		try {
			config = ConfigurationUtils.readConfiguration(configurationFilename);
		} catch (IOException cause) {
			throw new EnforcerRuleException("Non puiden ler o arquivo de configuración", cause);
		}

		helper.getLog().info("INICIANDO COMPROBACIÓN DEPENDENCIAS para:" + environment);
		try {
			MavenProject project = (MavenProject) helper.evaluate( "${project}" );
			Set<Artifact> dependencies = getDependenciesToCheck( project );
			for(Artifact artifact: dependencies) {
				if (config.isValidVersion(artifact.getVersion(), environment, helper.getLog())) {
					helper.getLog().info(artifact.getArtifactId() + " " + artifact.getVersion() + " TRUE");
				} else {
					helper.getLog().info(artifact.getArtifactId() + " " + artifact.getVersion() + " FALSE");
				}
			}
		} catch (ExpressionEvaluationException cause) {
			throw new EnforcerRuleException("Hai dependencias cuxa fase non se permite para a contorna", cause);
		}
	}

	/**
	 * If your rule is cacheable, you must return a unique id when parameters or
	 * conditions change that would cause the result to be different. Multiple
	 * cached results are stored based on their id.
	 * 
	 * The easiest way to do this is to return a hash computed from the values
	 * of your parameters.
	 * 
	 * If your rule is not cacheable, then the result here is not important, you
	 * may return anything.
	 */
	public String getCacheId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This tells the system if the results are cacheable at all. Keep in mind
	 * that during forked builds and other things, a given rule may be executed
	 * more than once for the same project. This means that even things that
	 * change from project to project may still be cacheable in certain
	 * instances.
	 */
	public boolean isCacheable() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * If the rule is cacheable and the same id is found in the cache, the
	 * stored results are passed to this method to allow double checking of the
	 * results. Most of the time this can be done by generating unique ids, but
	 * sometimes the results of objects returned by the helper need to be
	 * queried. You may for example, store certain objects in your rule and then
	 * query them later.
	 */
	public boolean isResultValid(EnforcerRule arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	protected Set<Artifact> getDependenciesToCheck(MavenProject project) {
		Set<Artifact> dependencies = null;
		if (searchTransitive) {
			dependencies = project.getArtifacts();
		} else {
			dependencies = project.getDependencyArtifacts();
		}
		return dependencies;
	}
}
