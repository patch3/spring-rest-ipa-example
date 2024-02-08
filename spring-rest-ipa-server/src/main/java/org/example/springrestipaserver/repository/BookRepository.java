package org.example.springrestipaserver.repository;


import org.example.springrestipaserver.models.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @NonNull
    List<Book> findAll(Sort sort);

    @NonNull
    Optional<Book> findById(@NonNull Long id);

    void deleteById(@NonNull Long id);

    void save(Book book);


    default void updateNameById(String name, @NonNull Long id) {
        Optional<Book> book = findById(id);
        book.orElseThrow(() -> new NoSuchElementException("Not found id = " + id))
                .setName(name);
        save(book.get());
    };


}
