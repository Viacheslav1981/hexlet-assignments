package exercise.controller;

import java.util.List;

import exercise.dto.*;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    List<BookDTO> index() {
        return bookService.getAll();
    }

    @GetMapping(path = "/{id}")
    BookDTO show(@PathVariable long id) {
        return bookService.getOne(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    BookDTO create(@Valid @RequestBody BookCreateDTO bookCreateDTO) {

        return bookService.create(bookCreateDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookDTO update(@Valid @RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable long id) {
        return bookService.update(bookUpdateDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable long id) {
        bookService.delete(id);
    }


}
