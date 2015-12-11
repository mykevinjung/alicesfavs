export CATALINA_OPTS="$CATALINA_OPTS -DEXECUTION_ENV=prod -Dlog4j.configurationFile=env/prod/log4j2.xml -Xms256m -Xmx512m -XX:PermSize=64m -XX:MaxPermSize=128m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8086 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=52.27.245.48"

