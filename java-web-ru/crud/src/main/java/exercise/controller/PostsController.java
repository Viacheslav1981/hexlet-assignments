package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
      //  var id = ctx.pathParamAsClass("id", Long.class).get();
        int pages = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int rowsPerPage = 5;
       // int offset = (pages - 1) * rowsPerPage;
        int index1 = ((rowsPerPage * pages) - rowsPerPage);
        int index2 = rowsPerPage * pages;

        var posts = PostRepository.getEntities().subList(index1, index2);
        var page = new PostsPage(posts, pages);
        ctx.render("posts/index.jte", Collections.singletonMap("postsPage", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
