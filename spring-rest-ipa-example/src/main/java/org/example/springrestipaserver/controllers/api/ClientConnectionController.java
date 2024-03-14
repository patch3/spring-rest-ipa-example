package org.example.springrestipaserver.controllers.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.example.springrestipaserver.models.Client;
import org.example.springrestipaserver.repository.BookRepository;
import org.example.springrestipaserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientconnection")
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
        val client = clientRepository.findById(idClient).orElseThrow(
                () -> new EntityNotFoundException("Client not found"));
        val book   = bookRepository.findById(idBook).orElseThrow(
                () -> new EntityNotFoundException("Book not found"));
        client.getBooks().add(book);
        return clientRepository.save(client);
    }

    @DeleteMapping("/{clientId}/book/{bookId}")
    public void deleteConnection(
            @PathVariable Long clientId,
            @PathVariable Long bookId
    ) {
        val client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client not found"));
        val book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book not found"));

        client.getBooks().remove(book);
        clientRepository.save(client);
    }
}
