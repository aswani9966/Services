@ECHO OFF 
TITLE Running Spring boot App
call mvn clean install
call mvn spring-boot:run
PAUSE