package exercise.controller;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var token = Security.generateToken();
        ctx.cookie("token",token);

        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        Long id = user.getId();

        ctx.redirect(NamedRoutes.userPath(id));

        //  ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(422);
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
      //  var user = UserRepository.find(id)
       //         .orElseThrow(() -> new NotFoundResponse("User not found"));

        boolean isUser = UserRepository.find(id)
                .isPresent();

        if (isUser) {
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("User not found"));

            String testToken = user.getToken();
            var visited = String.valueOf(ctx.cookie("token"));

            String testCoock = ctx.cookie("token");

            if (!(user.getToken().equals(ctx.cookie("token")) &&
                    Objects.equals(user.getId(), id))) {
                ctx.redirect(NamedRoutes.buildUserPath());
               // UserRepository.delete(id);

            } else {
                ctx.render("users/show.jte", Collections.singletonMap("user", user));
            }

        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
           // UserRepository.clear();

        }


    }
}



