# web server and java JRE
FROM tomcat:jdk14-openjdk-oracle

# copy war file to web server deployment folder
ADD target/testWebServer.war /usr/local/tomcat/webapps/

# start the web server
CMD ["catalina.sh", "run"]