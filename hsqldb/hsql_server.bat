@cd /D %~dp0
java -classpath ..\war\WEB-INF\lib\hsqldb.jar org.hsqldb.Server -database test
pause