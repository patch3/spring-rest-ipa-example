package org.example.springrestipaserver.repository;


import org.example.springrestipaserver.models.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @NonNull
    List<Book> findAll(Sort sort);

    @NonNull
    Optional<Book> findById(@NonNull Long id);

    void deleteById(@NonNull Long id);


}
