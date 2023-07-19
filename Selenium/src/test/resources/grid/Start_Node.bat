cd /D %~dp0
 
title Start Windows Desktop Node

set GRID_PATH="selenium-server-4.10.0.jar"

java -jar %GRID_PATH% -role node -nodeConfig node_config.json

pause