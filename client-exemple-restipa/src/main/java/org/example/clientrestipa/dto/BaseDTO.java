package org.example.clientrestipa.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO implements IDTO {
    private transient final Gson gson = new Gson();

    private Long id;

    public abstract List<? extends BaseDTO> getConnection();

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
