FROM tomcat:9.0.73-jdk11-corretto

COPY target/fertilizer.war /usr/local/tomcat/webapps/

CMD chmod +x /usr/local/tomcat/bin/catalina.sh

CMD ["catalina.sh", "run"]