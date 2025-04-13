# project goal

main purpose of this project is to be a playground for learning basic tools and concepts of java programming world

# repo content

web application classes are in `org.example.mvc`

embedded tomcat server classes are in `org.example.tomcat`



# how to run

place yourself in directory with pom, then

```shell
mvn clean package
```

to start the server automatically (after stopping maven with ```Ctrl+c``` you have to manually find and kill server process)

```shell
mvn clean verify -Pexec
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

1. remove `ru.ivt.mvc` example
2. fix `request.getRequestDispatcher("/add.jsp")` returning `null`
3. add `welcome.jsp` page, on which database stats should be displayed (number of students and courses, modification count, requests count etc.)
4. **continuously update todo and roadmap**

    Check the links for imagination 
    
    + https://github.com/s4kibs4mi/java-developer-roadmap
    + https://github.com/akullpp/awesome-java
    + https://github.com/sindresorhus/awesome

# roadmap

1. add tests

    Learn JUnit

2. check lombok library for easy boilerplate code generation

3. containerize the application in docker image for easy deployment

    Look into https://docs.docker.com/get-started/workshop/ for more info. Later deployment with kubernetes

4. create maven goal for quick deployment

5. implement `org.apache.catalina.startup.Bootstrap`

    Current implementation of tomcat server uses `org.apache.catalina.startup.Tomcat`, which is intended to be used in tests, to properly implement the server we should work with `org.apache.catalina.startup.Bootstrap`