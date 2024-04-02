package org.example.clientrestipa.dto.connections;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class IdConnectionDTO implements IConnectionDTO {
    Long id;
    Long connectionId;
}
