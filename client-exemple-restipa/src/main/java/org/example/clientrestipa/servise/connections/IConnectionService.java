package org.example.clientrestipa.servise.connections;

import org.example.clientrestipa.dto.IDTO;
import org.example.clientrestipa.dto.connections.IdConnectionDTO;
import org.example.clientrestipa.servise.IService;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IConnectionService<T extends IDTO> extends IService<T> {
    void save(IdConnectionDTO connection) throws IOException, URISyntaxException;

    void delete(IdConnectionDTO connection) throws IOException, URISyntaxException;

}
