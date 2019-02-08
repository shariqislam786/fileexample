How to run the project

1. take war file from target folder and deploy on your tomcat (i tested using tomcat8) so use that to avoid issues
2. or import the project in eclipse add a tomcat to that eclipse and use eclipse run on server functionality

How to check hit the api's 

the address will be http://localhost:8080/fileexample-1.0/downloadingServletServerDownload for server download and then to local and
 http://localhost:8080/fileexample-1.0/downloadingServlet for direct download. the url can change if you tomcat runs on different port or your context name is different(determined by your war name of you can set that in your ide)

I have added two sysouts in the sever download version, you can see in tomcat folder in logs/catalina.log that it spends most of its time in downloading the file locally.

you can rebuild the war using maven by specifying command: mvn clean package
