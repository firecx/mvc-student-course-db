package org.example.tomcat;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Server {
   static public void main(String[] args) throws LifecycleException {
      Tomcat tomcat = new Tomcat();

      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setScheme("http");
      connector.setPort(8080);
      tomcat.setConnector(connector);

      String docBase = new File(".").getAbsolutePath();
      tomcat.addWebapp("", docBase);

      tomcat.start();
      tomcat.getServer().await();
   }
}
