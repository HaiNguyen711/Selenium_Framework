cd /D %~dp0

title Start Selenium Hub Server

set GRID_PATH="selenium-server-4.10.0.jar"

java -jar %GRID_PATH% -role hub -hubConfig hub_config.json

pause
