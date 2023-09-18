package exercise;

import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import io.javalin.Javalin;

import java.util.List;

import exercise.model.User;
import io.javalin.http.NotFoundResponse;
import java.util.Collections;
import java.util.Optional;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });


        app.get("/users/{id}", ctx -> {
            //  var userId = (ctx.pathParam("id"));
            // var userId = ctx.pathParamAsClass("id", Integer.class);

            var userId = ctx.pathParam("id");

            Optional<User> us = USERS.stream()
                    .filter(c -> c.getId() == (Integer.parseInt(userId)))
                    .findFirst();

            var user = new UserPage(us.get());

            if (us.isEmpty()) {
                throw new NotFoundResponse("User not found");
            }
            ctx.render("users/show.jte", Collections.singletonMap("user", user));
        });

        app.get("/users", ctx -> {
            var page = new UsersPage(USERS);

            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        //  ctx.render("users/index.jte");});

        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    //<td><a href='/users/show?id=${users.get("id")}'>${users.get("id")}</a></td>
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
