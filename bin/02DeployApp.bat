@echo off

rem #########################################################
rem #          Cerberus Application Deploy Script           #
rem #########################################################

CALL %CD%\00Config.bat

rem ###### Script start here ######

cd %MYPATH%
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-0.9.0
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-0.9.1
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.0.0
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.0.1
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.0.2
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.0
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.1
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.1
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.2
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.3
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.4
CALL %GLASSFISHPATH%asadmin undeploy --target server --cascade=true Cerberus-1.1.5
CALL %GLASSFISHPATH%asadmin deploy --target server --contextroot Cerberus --availabilityenabled=true %MYPATH%\..\Cerberus-1.1.5.war
