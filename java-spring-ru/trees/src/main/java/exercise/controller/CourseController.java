package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    @GetMapping(path = "/{id}/previous")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getPrevious(@PathVariable long id) {
        var course = courseRepository.findById(id);
        String path = course.getPath();

        List<Course> courseList = new ArrayList<>();

        if (path != null) {
            courseList = Arrays.stream(path.split("\\."))
                    .map(x -> courseRepository.findById(Long.parseLong(x)))
                    .toList();
        }

        return courseList;
    }


}
