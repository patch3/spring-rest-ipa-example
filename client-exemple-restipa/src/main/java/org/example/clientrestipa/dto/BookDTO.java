package org.example.clientrestipa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookDTO extends BaseDTO {
    String name;

    public BookDTO(Long id, String name) {
        super(id);
        this.name = name;
    }
}

