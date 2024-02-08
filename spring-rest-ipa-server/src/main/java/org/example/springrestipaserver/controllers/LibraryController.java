package org.example.springrestipaserver.controllers;

import org.example.springrestipaserver.models.Client;
import org.example.springrestipaserver.repository.BookRepository;
import org.example.springrestipaserver.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class LibraryController {
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    public LibraryController(ClientRepository clientRepository, BookRepository bookRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/clients")
    public List<Client> clientListRequest(){
        return clientRepository.findAll();
    }

    @GetMapping()
    public List<Client> clientListRequest(){
        return clientRepository.findAll();
    }

}
