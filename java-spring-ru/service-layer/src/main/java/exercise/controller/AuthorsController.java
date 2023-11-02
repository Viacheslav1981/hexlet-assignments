package exercise.controller;

import exercise.dto.*;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    List<AuthorDTO> index() {
        return authorService.getAll();
    }

    @GetMapping(path = "/{id}")
    AuthorDTO show(@PathVariable long id) {
        return authorService.getOne(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    AuthorDTO create(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {

        return authorService.create(authorCreateDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    AuthorDTO update(@Valid @RequestBody AuthorUpdateDTO authorUpdateDTO, @PathVariable long id) {
        return authorService.update(authorUpdateDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable long id) {
        authorService.delete(id);
    }


}
