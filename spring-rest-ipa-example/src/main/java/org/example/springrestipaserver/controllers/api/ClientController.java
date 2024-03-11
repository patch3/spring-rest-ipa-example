package org.example.springrestipaserver.controllers.api;

import org.example.springrestipaserver.models.Client;
import org.example.springrestipaserver.projections.ClientProjection;
import org.example.springrestipaserver.repository.ClientRepository;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientRepository clientRepository;


    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @BatchDataSource()
    @GetMapping()
    public List<ClientProjection> getAll() {
        return clientRepository.findAllProjection();
    }

    @GetMapping("/{id}")
    public ClientProjection getById(@PathVariable Long id) {
        return clientRepository.findProjectionById(id).orElse(null);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Client updateEntity(
            @PathVariable Long id,
            @RequestBody Client updatedEntity) {
        return clientRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedEntity.getFirstName());
                    existing.setLastName(updatedEntity.getLastName());
                    return clientRepository.save(existing);
                })
                .orElse(null);
    }
}
