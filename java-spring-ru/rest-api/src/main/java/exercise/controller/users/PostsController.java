package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;

import exercise.model.Post;
import exercise.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> show(@PathVariable int id) {

        return posts.stream()
                .filter(post -> post.getUserId() == id)
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post, @PathVariable String id) {

        post.setSlug(post.getSlug());
        post.setTitle(post.getTitle());
        post.setBody(post.getBody());

        post.setUserId(Integer.parseInt(id));

        posts.add(post);

        return post;

    }

}
