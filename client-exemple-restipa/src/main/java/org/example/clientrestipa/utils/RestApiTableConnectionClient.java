package org.example.clientrestipa.utils;

public class RestApiTableConnectionClient extends RestApiTableClient {
    private String connectionTableName;
    public RestApiTableConnectionClient(RestApiClient restApiClient, String tableName, String connectionTableName) {
        super(restApiClient, tableName);
        this.connectionTableName = connectionTableName;
    }

    /*public String deleteRecord(Long recordId, Long secondRecordId) {
        return sendRequest("DELETE", super.tableName + "/" + recordId + "/", null);
    }*/
}
