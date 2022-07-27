#!/usr/bin/env sh
java -jar /Database-Manager.jar -t `echo $DB_TYPE` -r `echo $DB_URL` -u `echo $DB_USER` -p `echo $DB_PASSWORD`
