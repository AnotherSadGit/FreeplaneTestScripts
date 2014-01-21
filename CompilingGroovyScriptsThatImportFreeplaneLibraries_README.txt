Compiling Groovy Scripts that Import Freeplane Libraries
========================================================
Simon Elms, 21 Jan 2014

Open the directory containing the script to compile in the command prompt.

In the command prompt run groovyc using the -cp switch to specify the jar files containing the Freeplane libraries.

eg 

groovyc -cp "C:/Program Files/Freeplane/plugins/org.freeplane.plugin.script/lib/plugin.jar;C:/Program Files/Freeplane/core/org.freeplane.core/lib/freeplaneeditor.jar" BranchFormatter.NodeShape.groovy