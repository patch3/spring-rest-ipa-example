package org.example.clientrestipa.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * Класс для взаимодействия с REST API сервером для работы с таблицами.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestApiClient {

    private String apiUrl;

    /**
     * Копирующий конструктор для создания нового экземпляра RestApiClient на основе существующего.
     *
     * @param restApiClient Существующий экземпляр RestApiClient, от которого будут скопированы данные.
     */
    public RestApiClient(RestApiClient restApiClient) {
        this.apiUrl = restApiClient.apiUrl;
    }

    /**
     * Получает таблицу с указанным именем.
     *
     * @param tableName Имя таблицы.
     * @return Ответ от сервера в виде строки.
     * @throws IOException        Если произошла ошибка при выполнении запроса.
     * @throws URISyntaxException Если указанный API-эндпоинт содержит недопустимый синтаксис URI.
     */
    public String getTable(String tableName) throws IOException, URISyntaxException {
        return sendRequest("GET", tableName, null);
    }

    /**
     * Добавляет новую запись в указанную таблицу.
     *
     * @param tableName  Имя таблицы.
     * @param recordData Данные новой записи в формате JSON.
     * @return Ответ от сервера в виде строки.
     * @throws IOException        Если произошла ошибка при выполнении запроса.
     * @throws URISyntaxException Если указанный API-эндпоинт содержит недопустимый синтаксис URI.
     */
    public String addRecord(String tableName, String recordData) throws IOException, URISyntaxException {
        return sendRequest("POST", tableName, recordData);
    }

    /**
     * Удаляет запись из указанной таблицы по идентификатору.
     *
     * @param tableName Имя таблицы.
     * @param recordId  Идентификатор записи для удаления.
     * @return Ответ от сервера в виде строки.
     * @throws IOException        Если произошла ошибка при выполнении запроса.
     * @throws URISyntaxException Если указанный API-эндпоинт содержит недопустимый синтаксис URI.
     */
    public String deleteRecord(String tableName, Long recordId) throws IOException, URISyntaxException {
        return sendRequest("DELETE", tableName + "/" + recordId, null);
    }


    /**
     * Удаляет запись из указанной таблицы по идентификатору.
     *
     * @param tableName      Имя таблицы.
     * @param firstRecordId  Первый идентификатор записи для удаления.
     * @param secondRecordId второй идентификатор записи для удаления.
     * @return Ответ от сервера в виде строки.
     * @throws IOException        Если произошла ошибка при выполнении запроса.
     * @throws URISyntaxException Если указанный API-эндпоинт содержит недопустимый синтаксис URI.
     */
    public String deleteRecord(String tableName, Long firstRecordId, String secondTableName, Long secondRecordId) throws IOException, URISyntaxException {
        return sendRequest(
                "DELETE",
                String.format(
                        "%s/%d/%s/%d",
                        tableName, firstRecordId, secondTableName, secondRecordId
                ),
                null
        );
    }


    /**
     * Отправляет HTTP-запрос к указанному API-эндпоинту.
     *
     * @param method   HTTP-метод запроса (например, "GET", "POST").
     * @param endpoint API-эндпоинт, к которому будет отправлен запрос.
     * @param data     Данные для отправки в теле запроса (может быть null, если данные не требуются).
     * @return Ответ от API в виде строки.
     * @throws IOException        Если произошла ошибка ввода-вывода при отправке запроса.
     * @throws URISyntaxException Если указанный API-эндпоинт содержит недопустимый синтаксис URI.
     */
    protected String sendRequest(String method, String endpoint, String data) throws IOException, URISyntaxException {
        URL url = new URI(apiUrl + endpoint).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        if (data != null) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        return readResponse(connection);
    }

    /**
     * Читает ответ от сервера.
     *
     * @param connection Открытое HTTP-соединение.
     * @return Ответ от сервера в виде строки.
     * @throws IOException Если произошла ошибка при чтении ответа.
     */
    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        connection.disconnect();
        return content.toString();
    }
}
