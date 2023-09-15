package exercise;

import io.javalin.Javalin;

import java.io.InputStream;
import java.util.List;

public final class App {

    public static Javalin getApp() {
        List<String> phones = Data.getPhones();
        List<String> domains = Data.getDomains();

        // BEGIN
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

       // app.get("/phones", ctx -> ctx.result((InputStream) phones));
        app.get("/phones", ctx -> ctx.json(phones));
        app.get("/domains", ctx -> ctx.json(domains));

        return app;
        
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
