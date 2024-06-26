package org.example.clientrestipa.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ClientDTO extends BaseDTO {
    String firstName;
    String lastName;

    List<BookDTO> books;

    public ClientDTO(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = null;
    }

    public ClientDTO(Long id, String firstName, String lastName, List<BookDTO> books) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public ClientDTO(String firstName, String lastName) {
        super(null);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = null;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s %s",
                super.getId(), this.firstName, this.lastName
        );
    }

    @Override
    public List<BookDTO> getConnection() {
        return books;
    }
}