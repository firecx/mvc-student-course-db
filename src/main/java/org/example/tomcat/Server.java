package org.example.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.example.mvc.controller.AddServlet;
import org.example.mvc.controller.WelcomeServlet;

public class Server {
   static public void main(String[] args) throws LifecycleException {
      Tomcat tomcat = new Tomcat();

      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setScheme("http");
      connector.setPort(8080);

      tomcat.setConnector(connector);

      Context context = tomcat.addContext("", new File(".").getAbsolutePath());
      System.out.println(new File(".").getAbsolutePath());

      Tomcat.addServlet(context, "welcome-page", new WelcomeServlet());
      context.addServletMappingDecoded("", "welcome-page");

      Tomcat.addServlet(context, "add-page", new AddServlet());
      context.addServletMappingDecoded("/add", "add-page");

      tomcat.start();
      tomcat.getServer().await();
   }
}
