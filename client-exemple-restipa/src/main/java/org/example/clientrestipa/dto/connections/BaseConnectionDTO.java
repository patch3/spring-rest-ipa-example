package org.example.clientrestipa.dto.connections;

import lombok.Data;
import org.example.clientrestipa.dto.IDTO;

@Data
public abstract class BaseConnectionDTO<FirstType extends IDTO, SecondType extends IDTO> implements IConnectionDTO {

    private final FirstType firstEntity;

    private final SecondType secondEntity;

    /*public FirstType getFirstEntity() {
        return firstEntity;
    }

    public SecondType getSecondEntity() {
        return secondEntity;
    }*/

    @Override
    public Long getId() {
        return firstEntity.getId();
    }

    @Override
    public Long getConnectionId() {
        return secondEntity.getId();
    }
}

