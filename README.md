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

# feel free to issue new stuff 

check the links for inspiration 

+ https://github.com/s4kibs4mi/java-developer-roadmap
+ https://github.com/akullpp/awesome-java
+ https://github.com/sindresorhus/awesome

# roadmap

1. add tests

    learn JUnit

2. add logging

    learn different logging facilites (i heard that tomcat uses ```juli``` library for logging, look into that second after inspecting standart jdk logging implementation)

3. check lombok library for easy boilerplate code generation

4. containerize the application in docker image for easy deployment

    Look into https://docs.docker.com/get-started/workshop/ for more info. Later deployment with kubernetes

5. create maven goal for quick deployment

6. implement `org.apache.catalina.startup.Bootstrap`

    Current implementation of tomcat server uses `org.apache.catalina.startup.Tomcat`, which is intended to be used in tests, to properly implement the server we should work with `org.apache.catalina.startup.Bootstrap`

# good to read or at least get acquainted

read through specs plus read the source code

+ https://jakarta.ee/specifications/

    + https://jakarta.ee/specifications/servlet/6.1/
    + https://jakarta.ee/specifications/pages/4.0/

+ https://docs.oracle.com/javase/specs/

    these are insane ones

    + https://docs.oracle.com/javase/specs/jls/se17/html/index.html
    + https://docs.oracle.com/javase/specs/jvms/se17/html/index.html