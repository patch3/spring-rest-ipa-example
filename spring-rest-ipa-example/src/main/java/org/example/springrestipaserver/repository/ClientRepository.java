package org.example.springrestipaserver.repository;


import org.example.springrestipaserver.models.Client;
import org.example.springrestipaserver.projections.ClientProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFirstNameStartingWith(String prefix);

    Optional<ClientProjection> findProjectionById(Long id);
    @NonNull
    @EntityGraph(attributePaths = "books")
    List<Client> findAll();

    List<ClientProjection> findProjectionBy();
}