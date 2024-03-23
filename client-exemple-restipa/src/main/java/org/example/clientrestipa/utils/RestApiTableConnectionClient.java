package org.example.clientrestipa.utils;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RestApiTableConnectionClient extends RestApiTableClient {
    private String connectionTableName;

    public RestApiTableConnectionClient(RestApiClient restApiClient, String tableName, String connectionTableName) {
        super(restApiClient, tableName);
        this.connectionTableName = connectionTableName;
    }

    public String deleteRecord(Long recordId, Long secondRecordId) throws IOException, URISyntaxException {
        return sendRequest(
                "DELETE",
                String.format(
                        "%s/%d/%s/%d",
                        super.tableName,
                        recordId,
                        connectionTableName,
                        secondRecordId
                    ),
                null
        );
    }

    public String addRecord(String tableName,  String recordData) throws IOException, URISyntaxException {
        return sendRequest("POST", tableName, recordData);
    }
}
