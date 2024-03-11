package org.example.springrestipaserver.controllers.api;

import org.example.springrestipaserver.models.Book;
import org.example.springrestipaserver.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public List<Book> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return bookRepository.findAll(sort);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateEntity(
            @PathVariable Long id,
            @RequestBody Book updatedEntity
    ) {
        return bookRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedEntity.getName());
                    return bookRepository.save(existing);
                }).orElse(null);
    }
}
