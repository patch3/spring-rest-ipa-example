package org.example.clientrestipa.controllers.tables;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.val;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.controllers.BaseController;
import org.example.clientrestipa.dto.BaseDTO;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.managers.AlertManager;
import org.example.clientrestipa.utils.RestApiClient;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseControllerTable<T extends BaseDTO> extends BaseController {
    protected final static RestApiClient REST_CLIENT = new RestApiClient(SocketConfig.URL_CONNECT);
    private final Gson gson = new Gson();

    private final Class<T> type = this.getDependentClass();
    private final RestApiTableClient restApiTableClient = this.getRestApiTableClient();

    @FXML
    protected TableView<T> table;

    @FXML
    protected Button addButton;



    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(action -> this.addEntry(this.getDTOMoreAdd()));
    }


    public void updateTable() {
        try {
            Type typeList = TypeToken.getParameterized(List.class, type).getType();

            List<T> clientList = gson.fromJson(
                    restApiTableClient.getTable(),
                    typeList
            );
            ObservableList<T> data = FXCollections.observableList(clientList);

            this.table.setItems(data);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Недействительный адрес");
        }
    }


    protected TableColumn<T, Button> createDeleteActionColumn() {
        val column = new TableColumn<T, Button>("Action delete");

        column.setCellValueFactory(param -> new SimpleObjectProperty<>(new Button("Delete")));
        column.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    item.setOnAction(event -> {
                        try {
                            val objDTO = getTableView().getItems().get(getIndex());
                            getRestApiTableClient().deleteRecord(objDTO.getId().toString());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
                        } catch (URISyntaxException ex) {
                            ex.printStackTrace();
                            AlertManager.showErrorAlert("Недействительный адрес");
                        }
                        getTableView().getItems().remove(getIndex());
                    });
                    setGraphic(item);
                }
            }
        });
        return column;
    }

    public void addEntry(T dto) {
        val data = gson.toJson(dto);
        try {
            getRestApiTableClient().addRecord(data);
            this.updateTable();
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Недействительный адрес");
        }
    }

    public abstract String getTableName();

    public abstract RestApiTableClient getRestApiTableClient();

    public abstract Class<T> getDependentClass();

    public abstract T getDTOMoreAdd();
}
