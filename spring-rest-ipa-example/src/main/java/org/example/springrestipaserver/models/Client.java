package org.example.springrestipaserver.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springrestipaserver.projections.ClientProjection;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Client implements ClientProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "has",
            joinColumns = @JoinColumn(name = "clientId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id")
    )
    private List<Book> books;


    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
