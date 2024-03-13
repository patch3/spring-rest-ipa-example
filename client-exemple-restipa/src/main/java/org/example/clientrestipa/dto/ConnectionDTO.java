package org.example.clientrestipa.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ConnectionDTO extends BaseDTO {
    Long connectionId;

    public ConnectionDTO(Long id, Long connectionId) {
        super(id);
        this.connectionId = connectionId;
    }


}
