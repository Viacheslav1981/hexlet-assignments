package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

//@Component
//@Scope("prototype")
public class Day implements Daytime {
    private String name = "day";
    public String getName() {
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean day is created!");
    }
}
