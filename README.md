Rules for environments
====
##About this plugin
Rules for environments is a new plugin for Maven 2/3. The main use of the plugin is to break the project construction if any dependency, or the project itself, does not meet a set of pre-established rules.

The plugin allows you to define a list of dependencies to include in the analysis, and / or to exclude it. It also allows you to define a list of dependencies that are not allowed to use. If a breach is detected, the construction of the project is stopped.

Have you ever wondered ...?

* Are beta versions good enought to deploy in a production environment?
>No, they aren't. **Beta versions** shouldn't be deployed in a production environment. Beta versions contains most of the major features planned for a version, but it may not be ready and it may contain bugs.

* Are SNAPSHOT versions ready for testing environments?
>No. A **SNAPSHOT** is the result of the development build for internal purposes. The test environment is an environment for testing a non development build.

* Should I deploy a release candidate in production environment?
>No. A **release candidate** is a *ready to publish* version, **unless errors are detected**, but the schema version does not tell us whether it has been accepted or not: we can release the rc0, rc1 and rc2 versions. We found errors in the rc2 version at test environment, that were not present in the rc1, we reject it, and approve the rc1.

* What about deploying final versions in development environment?
>This is possible, but you must be careful with the version. It is not recommended to deploy in the development environment a lower final version to that in the testing environment.

It is necessary to adopt a procedure for acceptance of the release candidate. The result of this procedure is the final version. This (final) version can be deployed to the production environment. And this (final) version should be build again, because some build rules should be different from the testing environment.

At this point I recommend that we ask ourselves whether it's reasonable to deploy, for example, a beta library in a production environment. Our software in production environment is always stable, in finally version, but what about the libraries which are packaged with our application?

My answer is: the same rules for all the pieces of our software.

Additional uses of this plugin are:

* Posibility of define forbidden artifacts.
* Inheritance of rulesets.
* Support of remote locations of rulesets.
* Break the build of non complaint artifact.

##Environments and scheme versions: Are they related?

**What about environment and scheme versions? Is there any relation between this terms?**
From my point of view this question has a simple answer:
>**Yes. A version of a library should enforce a minimum phase according to an environment**.

For example, a production environment should not contains libraries in other than final release or patch.

**Why this restriction?**
Production environment is a stable environment, so the minimum compliant version should be final: no snapshots versions, no alfa versions, no beta neither release candidate versions: only final and patches.

This is the final environment in the sequence of steps from development builds to finally builds.

###A simple proposal: Pseudo scheme versioning constraints
The table below shows one of the multiples proposals of pseudo-scheme versioning constraints (consider only the development phase for this example; yes, there is a direct relation between the development phase and the scheme version, as discused before):

| Environment | SNAPSHOT | alfa | beta | release candidate | final | patch|
|--|--|--|--|--|--|--|
|development|YES|YES|YES|YES|YES|YES
|ic|NO|NO|YES|YES|YES|YES
|test|NO|NO|NO|YES|YES|YES
|production|NO|NO|NO|NO|YES|YES

>**YES** means that the version scheme is allowed in the environment.
>**NO** means that the version scheme is not allowed in the environment.

##A very simple example to ilustrate this problem
###Our application: blogging.war
Suppose our application allows to manage blogs, and construction process generates a file name ++blogging.war++.
Suppose also that we use the following libraries (scopes are irrelevant for this example, as the name of the war):

In **compile scope**:
* org.springframework:spring-core:jar:3.0.5.RELEASE
* org.springframework:spring-expression:jar:3.0.5.RELEASE
* org.springframework:spring-beans:jar:3.0.5.RELEASE
* commons-collections:commons-collections:jar:3.0
* commons-loggin:commons-logging:jar:1.1.2
* our.enterprise.group:our-first-library:jar:0.1.1-a1
* our.enterprise.group:our-second-library:jar:2.1.1

In **test scope**:
* junit:junit:jar:4.11

Are there any problem with that dependency versions? Yes? No?

###The problems with the dependency versions: Why the environment is important?

(In progress)

##Some terms used here
* The **development environment** is the environment used by the developers. It is an internal environment, reserved to the developers of the application.
* The **ic environment** is the environment used by the integration continuous application to run the automated tests.
* The **test environment** is the environment where final users can test the application before giving its approval.
* The **production environment** is the environment with real data.

