FROM tomcat:11.0

ADD target/fertilizer.war /usr/local/tomcat/webapps-javaee/

CMD chmod +x /usr/local/tomcat/bin/catalina.sh

CMD ["catalina.sh", "run"]