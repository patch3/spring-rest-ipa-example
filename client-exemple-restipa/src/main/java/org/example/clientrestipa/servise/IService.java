package org.example.clientrestipa.servise;

import org.example.clientrestipa.dto.IDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IService<T extends IDTO> {
    <U extends IDTO> void save(U entity) throws IOException, URISyntaxException;

    //void update(T entity);
    void delete(Long id) throws IOException, URISyntaxException;

    List<T> findAll() throws IOException, URISyntaxException;
}
