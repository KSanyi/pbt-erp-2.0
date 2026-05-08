package com.example;

import org.eclipse.jetty.ee10.webapp.MetaInfConfiguration;
import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.ResourceFactory;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws Exception {
        // Jetty's classpath scanner throws on non-existent entries - strip them first.
        fixClasspath();

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");

        // Resolve the webapp/ folder from the classpath (contains the empty ROOT marker).
        URL webRoot = Application.class.getResource("/webapp/");
        context.setBaseResource(ResourceFactory.of(context).newResource(webRoot));

        // Fail loudly at startup instead of swallowing servlet init errors.
        context.setThrowUnavailableOnStartupException(true);

        // Use Jetty's default configuration pipeline (includes AnnotationConfiguration).
        // setConfigurationDiscovered(true) activates it so all @WebServlet/@WebListener
        // annotations and ServletContainerInitializers are discovered.
        // Vaadin depends on this for LookupServletContainerInitializer and friends.
        context.setAttribute(MetaInfConfiguration.CONTAINER_JAR_PATTERN, ".*\\.jar|.*/classes/.*");
        context.setConfigurationDiscovered(true);

        server.setHandler(context);
        server.start();
        System.out.println("Server started → http://localhost:8080");
        server.join();
    }

    private static void fixClasspath() {
        String cp = System.getProperty("java.class.path");
        if (cp == null) return;
        String fixed = Arrays.stream(cp.split("[" + File.pathSeparator + "]"))
                .filter(e -> !e.isBlank() && new File(e).exists())
                .collect(Collectors.joining(File.pathSeparator));
        if (!fixed.equals(cp)) {
            System.setProperty("java.class.path", fixed);
        }
    }
}
