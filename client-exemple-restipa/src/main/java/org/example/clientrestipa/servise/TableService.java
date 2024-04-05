package org.example.clientrestipa.servise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.dto.IDTO;
import org.example.clientrestipa.utils.RestApiClient;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

@Getter
public class TableService<T extends IDTO> implements IService<T> {
    protected final static RestApiClient REST_CLIENT = new RestApiClient(SocketConfig.URL_CONNECT);
    protected final static Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    protected final RestApiTableClient restTableClient;

    protected final String tableName;
    protected final Class<T> entryClass;

    public TableService(String tableName, Class<T> entryClass) {
        this.tableName = tableName;
        this.entryClass = entryClass;
        this.restTableClient = new RestApiTableClient(REST_CLIENT, tableName);
    }

    public TableService(TableService<T> tableService) {
        this(tableService.tableName, tableService.entryClass);
    }

    public List<T> findAll() throws IOException, URISyntaxException {
        return GSON.fromJson(restTableClient.getTable(), this.getTypeList());
    }

    public <U extends IDTO> void save(U entity) throws IOException, URISyntaxException {
        restTableClient.addRecord(GSON.toJson(entity));
    }

    public void delete(Long id) throws IOException, URISyntaxException {
        restTableClient.deleteRecord(id);
    }

    //public abstract <T extends BaseDTO> void update(T entity);

    private Type getTypeList() {
        return TypeToken.getParameterized(List.class, getEntryClass()).getType();
    }

}
