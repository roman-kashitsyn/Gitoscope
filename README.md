What is Gitoscope
=================

Gitoscope is a cross platform web application to browse git
repositories in a fast and easy way.

License
=======

The project is available under Apache 2.0 license. See LICENSE file
for details.

Project Dependencies
====================

Gitoscope is based on amazing Spring Framework 3. It also uses jgit
library to access git repositories. Apache Maven tool is used for
builds. Of course, there are other dependencies, see `pom.xml` for
details.

Building the Gitoscope
======================

Firstly you need to configure repository search service by base
directory name (the directory that contains your git repos). It can be
done by editing
`{root}/src/main/webapp/WEB-INF/spring/app/services.xml` file (your
aim is the `projectService` bean). At the moment you can not specify
multiple directories, but in future it will be possible. Run-time
configuration is also will be possible in future.

Now just run `mvn package` to get the WAR file.

Contact Information
===================

Roman Kashitsyn <roman.kashitsyn@gmail.com>
