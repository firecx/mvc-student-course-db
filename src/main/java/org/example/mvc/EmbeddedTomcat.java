package org.example.mvc;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;


public class EmbeddedTomcat {
    static public void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);

        tomcat.setConnector(connector);

        tomcat.addWebapp("", new File(".").getAbsolutePath());

        tomcat.start();
        System.out.println("Tomcat запущен: http://localhost:8080");
        tomcat.getServer().await();
        System.out.println("Tomcat закрыт: http://localhost:8080");
    }
}