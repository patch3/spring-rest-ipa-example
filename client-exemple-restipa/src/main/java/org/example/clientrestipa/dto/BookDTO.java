package org.example.clientrestipa.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BookDTO extends BaseDTO {

    public final static String TABLE_NAME = "book";

    String name;

    public BookDTO(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s",
                super.getId(), this.name
        );
    }
}

