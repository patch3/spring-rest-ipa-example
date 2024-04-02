package org.example.clientrestipa.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;


@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookDTO extends BaseDTO {
    String name;

    List<ClientDTO> clients;

    public BookDTO(Long id, String name) {
        super(id);
        this.name = name;
        this.clients = null;
    }

    public BookDTO(Long id, String name, List<ClientDTO> clients) {
        super(id);
        this.name = name;
        this.clients = clients;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s",
                super.getId(), this.name
        );
    }

    @Override
    public List<ClientDTO> getConnection() {
        return clients;
    }
}

