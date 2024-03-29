package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

//@Component
//@Scope("prototype")
public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean night is created!");
    }
}
