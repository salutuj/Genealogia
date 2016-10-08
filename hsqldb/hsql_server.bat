@cd /D %~dp0
java -classpath hsqldb.jar org.hsqldb.Server -database genealogia -props genealogia.properties
pause