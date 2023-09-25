package exercise;

import io.javalin.Javalin;
import exercise.controller.UsersController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });


     //   app.post(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);

      //  app.post(NamedRoutes.userPath("{id}"), UsersController::create);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        //<form action="${NamedRoutes.usersPath()}" method="post">


        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
       // app.get(NamedRoutes.usersPath(), UsersController::show);
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
