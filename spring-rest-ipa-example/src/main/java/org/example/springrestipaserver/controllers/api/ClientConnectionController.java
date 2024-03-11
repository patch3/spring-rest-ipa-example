package org.example.springrestipaserver.controllers.api;

import jakarta.persistence.EntityNotFoundException;
import org.example.springrestipaserver.models.Book;
import org.example.springrestipaserver.models.Client;
import org.example.springrestipaserver.repository.BookRepository;
import org.example.springrestipaserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apu/clientbook")
public class ClientConnectionController {
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ClientConnectionController(ClientRepository clientRepository, BookRepository bookRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Client addConnection(
            @RequestBody Long idClient,
            @RequestBody Long idBook
        ) {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        Book book = bookRepository.findById(idBook).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        client.getBooks().add(book);
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteConnection(@PathVariable Long idClient, @PathVariable Long ) {
        clientRepository.deleteById(id);
    }
}
