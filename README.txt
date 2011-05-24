+-----------+
| Gitoscope |
+-----------+

WHAT IS Gitoscope?

Gitoscope is a cross platform web application to browse git repositories in a
fast and easy way.

WHAT IS THE PROJECT VALUES?

The main aim is to provide compact, convenient and cross platform tool to
browse git repositories. The main values are following:

o Cross platform
o Usability
o Robustness
o Customization
o RESTful interface
o Simple and self-describing code

Performance is not the main value but it should not conflict with usability.

WHAT IS THE Gitoscope BASED ON?

Gitoscope is based on amazing Spring Framework 3. It also uses jgit library to
access git repositories. Ant and ivy are used for builds.

At the moment Gitoscope does not need a RDB access, it extracts all the needed
information from repository. But future releases may require access to RDB to
store tool configuration.

HOW CAN I BUILD Gitoscope?

Firstly you need to configure repository search service by base directory name
(the directory that contains your git repos). It can be done by editing
{root}/web/WEB-INF/conf/service-context.xml file (you need projectService
bean).

Now you can execute the `build-all' ant target. It will download all the
required libraries and perform full compilation and WAR creation.

CONTACT INFORMATION

Roman Kashitsyn
email: roman.kashitsyn@gmail.com
