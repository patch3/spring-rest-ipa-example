package org.example.springrestipaserver.repository;


import org.example.springrestipaserver.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFirstNameStartingWith(String prefix);

    @NonNull
    @Override
    List<Client> findAll();
}
