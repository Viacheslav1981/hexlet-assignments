package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;
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
    public void create(@RequestBody Post post, @PathVariable int id) {
        post.setSlug(post.getSlug());
        post.setTitle(post.getTitle());
        post.setBody(post.getBody());

        post.setUserId(id);

         posts.add(post);
         
    }

}
