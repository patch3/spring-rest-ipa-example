package org.example.clientrestipa.dto.connections;

import org.example.clientrestipa.dto.IDTO;

public class SimpleConnectionDTO<FirstType extends IDTO, SecondType extends IDTO>
        extends BaseConnectionDTO<FirstType, SecondType> implements IConnectionDTO {

    public SimpleConnectionDTO(FirstType firstEntity, SecondType secondEntity) {
        super(firstEntity, secondEntity);
    }
}
