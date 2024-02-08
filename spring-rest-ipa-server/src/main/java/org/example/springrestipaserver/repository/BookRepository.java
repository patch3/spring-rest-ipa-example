package org.example.springrestipaserver.repository;


import org.example.springrestipaserver.models.Book;
import org.example.springrestipaserver.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    void save();

    @NonNull
    Optional<Book> findById(Long id);


}
