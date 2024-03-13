package org.example.clientrestipa.utils;

import lombok.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Класс для взаимодействия с REST API сервером для работы с конкретной таблицей.
 * Наследуется от {@link RestApiClient}.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RestApiTableClient extends RestApiClient {
    /**
     * Имя таблицы, с которой будет производиться взаимодействие.
     */
    protected String tableName;

    /**
     * Конструктор для создания нового экземпляра RestApiTableClient на основе существующего RestApiClient.
     *
     * @param restApiClient Существующий экземпляр RestApiClient, используемый для инициализации базового URL.
     * @param tableName     Имя таблицы для взаимодействия с сервером.
     */
    public RestApiTableClient(RestApiClient restApiClient, String tableName) {
        super(restApiClient);
        this.tableName = tableName;
    }

    /**
     * Создает новый экземпляр RestApiTableClient.
     *
     * @param apiUrl    Базовый URL API сервера.
     * @param tableName Имя таблицы для взаимодействия.
     */
    public RestApiTableClient(String apiUrl, String tableName) {
        super(apiUrl);
        this.tableName = tableName;
    }

    /**
     * Получает данные из таблицы, установленной в этом клиенте.
     *
     * @return Ответ от сервера в виде строки.
     * @throws IOException Если произошла ошибка при выполнении запроса.
     */
    public String getTable() throws IOException, URISyntaxException {
        return super.sendRequest("GET", this.tableName, null);
    }

    /**
     * Добавляет новую запись в таблицу, установленную в этом клиенте.
     *
     * @param recordData Данные новой записи в формате JSON.
     * @return Ответ от сервера в виде строки.
     * @throws IOException Если произошла ошибка при выполнении запроса.
     */
    public String addRecord(String recordData) throws IOException, URISyntaxException {
        return super.addRecord(this.tableName, recordData);
    }

    /**
     * Удаляет запись из таблицы, установленной в этом клиенте, по идентификатору.
     *
     * @param recordId Идентификатор записи для удаления.
     * @return Ответ от сервера в виде строки.
     * @throws IOException Если произошла ошибка при выполнении запроса.
     */
    public String deleteRecord(Long recordId) throws IOException, URISyntaxException {
        return super.deleteRecord(this.tableName, recordId);
    }
}
