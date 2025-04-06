# repo content

web application classes are in `org.example.mvc`

embedded tomcat server classes are in `org.example.tomcat`



# how to run

place yourself in directory with pom, then

```shell
mvn clean package
```
windows:
```powershell
java -classpath "target/mvc-0.0.1/WEB-INF/lib/*;target/mvc-0.0.1/WEB-INF/classes/." org.example.tomcat.Server
```
linux:
```shell
java -classpath "target/mvc-0.0.1/WEB-INF/lib/*:target/mvc-0.0.1/WEB-INF/classes/." org.example.tomcat.Server
```

stop the server with `Ctrl+C`

# state of embedded tomcat server

currently tomcat web server is configured to start on port `8080` and uses `http` protocol

access local server with:

```
http://localhost:8080
```

tomcat server implementation uses empty context string `""`, which triggers `welcome-page` servlet `GET` response on the address above 

# todo

- remove `ru.ivt.mvc` example
- fix `request.getRequestDispatcher("add.jsp")` returning `null`, after fixing this issue future development should become pretty easy
- after fixing the issue above add `welcome.jsp` page, on which database stats should be displayed, like number of students and courses, modification count, requests count etc.
- current implementation of tomcat server uses `org.apache.catalina.startup.Tomcat`, which is intended to be used in tests, to properly implement the server we should work with `org.apache.catalina.startup.Bootstrap`