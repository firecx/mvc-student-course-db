# project goal

main purpose of this project is to be a playground for learning basic tools and concepts of java programming world

# repo content

web application classes are in `org.example.mvc`

# how to run

you will need a docker
place yourself in directory with docker-compose.yml, then run

unix:

```shell
docker compose up
```

stop the server with:
```shell
docker compose stop
```

# access the server

currently tomcat web server is configured programmatically to start on port `8080`,
but the docker container redirects to port `80` and uses `http` protocol

access local server with:

```
http://localhost
```

# feel free to issue new stuff 

check the links for inspiration 

+ https://github.com/s4kibs4mi/java-developer-roadmap
+ https://github.com/akullpp/awesome-java
+ https://github.com/sindresorhus/awesome

# roadmap

1. It's being worked on

# good to read or at least get acquainted

read through specs plus read the source code

+ https://jakarta.ee/specifications/

    + https://jakarta.ee/specifications/servlet/6.1/
    + https://jakarta.ee/specifications/pages/4.0/

+ https://docs.oracle.com/javase/specs/

    these are insane ones

    + https://docs.oracle.com/javase/specs/jls/se17/html/index.html
    + https://docs.oracle.com/javase/specs/jvms/se17/html/index.html