set ProjectPath=%~dp0
cd %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\jars\*
java org.testng.TestNG TestNG.xml
PAUSE