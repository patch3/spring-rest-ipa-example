package org.example.springrestipaserver.controllers;

import lombok.val;
import org.example.springrestipaserver.models.Book;
import org.example.springrestipaserver.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/book")
public class BookController{
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public List<Book> getAll() {
        val sort = Sort.by(Sort.Direction.ASC, "name");
        return bookRepository.findAll(sort);
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return Optional.empty();
    }

    @PostMapping
    public void save(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) {

    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void updateNameById(String name, Long id) {
        BookRepository.super.updateNameById(name, id);
    }
}
