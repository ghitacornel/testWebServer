FROM tomcat:jdk14-openjdk-oracle
MAINTAINER ghita.cornel@gmail.com

ADD target/testWebServer.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]