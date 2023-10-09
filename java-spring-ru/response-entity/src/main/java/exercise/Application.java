package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        URI location  = URI.create(post.getId());

        return ResponseEntity.created(location).body(post);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index() {

        var result = posts.stream().toList();


        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(result);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return post.map(value -> ResponseEntity.ok()
                .body(value)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {

        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var postUp = maybePost.get();
            postUp.setId(post.getId());
            postUp.setBody(post.getBody());
            postUp.setTitle(post.getTitle());
           return ResponseEntity.ok().body(postUp);

        } else {
           // return ResponseEntity.noContent().build();
            return ResponseEntity.status(204).body(post);
        }
      //  return post;
    }


    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
