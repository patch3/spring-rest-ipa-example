package org.example.clientrestipa.servise.connections;

import org.example.clientrestipa.dto.IDTO;
import org.example.clientrestipa.dto.connections.IdConnectionDTO;
import org.example.clientrestipa.servise.TableService;
import org.example.clientrestipa.utils.RestApiTableConnectionClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class TableConnectionService<T extends IDTO>
        extends TableService<T> implements IConnectionService<T> {

    protected final RestApiTableConnectionClient restApiTableConnectionClient;


    public TableConnectionService(TableService<T> tableService, String secondTableName) {
        super(tableService);
        this.restApiTableConnectionClient =
                new RestApiTableConnectionClient(super.restTableClient, secondTableName);
    }

    public TableConnectionService(String firstTableName, String secondTableName, Class<T> entryClass) {
        super(firstTableName, entryClass);
        this.restApiTableConnectionClient =
                new RestApiTableConnectionClient(super.restTableClient, secondTableName);
    }

    public void save(IdConnectionDTO connection) throws IOException, URISyntaxException {
        restApiTableConnectionClient.addRecord(connection.getId(), connection.getConnectionId());
    }

    public void delete(IdConnectionDTO connection) throws IOException, URISyntaxException {
        restApiTableConnectionClient.deleteRecord(connection.getId(), connection.getConnectionId());
    }
}
