package hu.pbt.erp;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.jetty.ee10.webapp.MetaInfConfiguration;
import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.util.resource.ResourceFactory;

import io.javalin.Javalin;

public class Application {

    public static void main(String[] args) throws Exception {
        fixClasspath();

        Javalin javalin = Javalin.create(config -> {
            config.jetty.modifyServer(server -> {
                // Javalin's attachHandler() detects a Handler.Sequence and appends its own
                // ServletContextHandler(/) to it, so Vaadin(/ui) takes priority.
                Handler.Sequence sequence = new Handler.Sequence();
                sequence.addHandler(createVaadinWebAppContext());
                server.setHandler(sequence);
            });

            config.routes.get("/new-order", ctx -> {
                String id = ctx.queryParam("id");
                System.out.println("Order received with id: " + id);
            });
        });
        
        javalin.start(8080);
    }
    
    private static WebAppContext createVaadinWebAppContext() {
        WebAppContext vaadinContext = new WebAppContext();
        vaadinContext.setContextPath("/ui");
        URL webRoot = Application.class.getResource("/webapp/");
        vaadinContext.setBaseResource(ResourceFactory.of(vaadinContext).newResource(webRoot));
        vaadinContext.setThrowUnavailableOnStartupException(true);
        vaadinContext.setAttribute(MetaInfConfiguration.CONTAINER_JAR_PATTERN, ".*\\.jar|.*/classes/.*");
        vaadinContext.setConfigurationDiscovered(true);

        return vaadinContext;
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