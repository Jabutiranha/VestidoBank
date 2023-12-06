FROM tomcat:latest


COPY target/*.war /usr/local/tomcat/webapps/

COPY web.xml /usr/local/tomcat/conf


EXPOSE 8080

CMD ["catalina.sh", "run"]
