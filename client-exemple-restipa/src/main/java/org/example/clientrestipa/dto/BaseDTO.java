package org.example.clientrestipa.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {
    private transient final Gson gson = new Gson();

    public Long id;

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
