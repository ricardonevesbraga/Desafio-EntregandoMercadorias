set MAVEN_OPTS=-Xms256m -Xmx512m -XX:PermSize=128m

call mvn clean install

call mvn war:war

