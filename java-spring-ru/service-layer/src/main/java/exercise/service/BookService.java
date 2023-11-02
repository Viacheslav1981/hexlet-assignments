package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var result = books.stream()
                .map(bookMapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        var book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);

        return bookMapper.map(book);
    }

    public BookDTO getOne(long id) {
        var book = bookRepository.findById(id).get();
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookUpdateDTO, long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));

        bookMapper.update(bookUpdateDTO, book);
        bookRepository.save(book);

        return bookMapper.map(book);
    }

    public void delete(long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
        bookRepository.delete(book);
    }
}
