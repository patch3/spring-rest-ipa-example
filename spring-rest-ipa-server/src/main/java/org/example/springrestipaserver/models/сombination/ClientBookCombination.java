package org.example.springrestipaserver.models.сombination;


import lombok.Data;
import org.example.springrestipaserver.models.Book;
import org.example.springrestipaserver.models.Client;

@Data
public class ClientBookCombination {
    private Client client;
    private Book book;

    /*public String[] getArrayString() {
        return new String[]{
                client.getId().toString(),
                client.getFirstName(),
                client.getLastName(),
                book.getId().toString(),
                book.getName(),
        };
    }*/

}
