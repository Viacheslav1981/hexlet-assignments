package exercise;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;

import exercise.servlet.WelcomeServlet;
import exercise.servlet.UsersServlet;

public class App {

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 8002;
    }

    public static Tomcat getApp(int port) {
        Tomcat app = new Tomcat();

        app.setBaseDir(System.getProperty("java.io.tmpdir"));
        app.setPort(port);

        Context ctx = app.addContext("", new File(".").getAbsolutePath());

        app.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());

        // BEGIN
        app.addServlet(ctx, UsersServlet.class.getSimpleName(), new UsersServlet());
        ctx.addServletMappingDecoded("/users/*", UsersServlet.class.getSimpleName());
        
        // END

        return app;
    }

    public static void main(String[] args) throws LifecycleException {
        Tomcat app = getApp(getPort());
        app.start();
        app.getServer().await();

    }
}
