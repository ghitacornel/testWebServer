# web server and java JRE
FROM tomcat:jdk14-openjdk-oracle

# copy war file to web server deployment folder
ADD target/testWebServer.war /usr/local/tomcat/webapps/

# copy JDBC Driver jar into server LIB folder
ADD docker/postgresql-42.2.13.jar /usr/local/tomcat/lib/

# copy server datasource config
ADD docker/context.xml /usr/local/tomcat/conf/

# start the web server
CMD ["catalina.sh", "run"]