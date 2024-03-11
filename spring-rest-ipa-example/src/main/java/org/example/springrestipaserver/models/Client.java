package org.example.springrestipaserver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @ManyToMany
    @JoinTable(name = "has",
            joinColumns = @JoinColumn(name = "clientId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id")
    )
    private Set<Book> books; // Исправлено на Set<Book>


    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
