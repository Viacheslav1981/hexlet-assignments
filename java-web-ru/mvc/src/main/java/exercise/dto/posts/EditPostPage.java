package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditPostPage {
    private Post post;

    private String name;
    private String body;
    private Map<String, List<ValidationError<Object>>> errors;

    public EditPostPage(Post post) {
        this.post = post;
    }

    public EditPostPage(String name, String body, Map<String, List<ValidationError<Object>>> errors) {
        this.name = name;
        this.body = body;
        this.errors = errors;
    }
}


// END
