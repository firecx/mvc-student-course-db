package org.example.tomcat;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Server {
    static public void main(String[] args) {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        tomcat.setConnector(connector);

        String docBase = new File(".").getAbsolutePath();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--docBase") && i + 1 < args.length) {
                docBase = args[i + 1];
            }
        }
        System.out.println("docBase: " + docBase);
        tomcat.addWebapp("", docBase);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                tomcat.stop();
                tomcat.destroy();
                System.out.println("Shutdown complete successfully");
            } catch (LifecycleException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }));

        tomcat.getServer().await();
    }
}
