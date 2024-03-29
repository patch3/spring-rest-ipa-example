package org.example.clientrestipa.controllers.tables;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.val;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.controllers.BaseController;
import org.example.clientrestipa.dto.BaseDTO;
import org.example.clientrestipa.managers.AlertManager;
import org.example.clientrestipa.utils.RestApiClient;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseTableController<T extends BaseDTO> extends BaseController {
    protected final static RestApiClient REST_CLIENT = new RestApiClient(SocketConfig.URL_CONNECT);
    private final Gson gson = new Gson();

    private final Class<T> type = this.getDependentClass();
    private final RestApiTableClient restApiTableClient = this.getRestApiTableClient();

    @FXML
    protected TableView<T> table;

    @FXML
    protected Button addButton;

    @FXML
    protected AnchorPane anchorPaneBase;


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
        return new TableColumn<T, Button>("Action delete") {{
                setCellValueFactory(param -> new SimpleObjectProperty<>(new Button("Delete")));
                setCellFactory(param -> new TableCell<>() {
                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            item.setOnAction(event -> {
                                try {
                                    val objDTO = getTableView().getItems().get(getIndex());
                                    getRestApiTableClient().deleteRecord(objDTO.getId());
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
            }};
    }

    public void addEntry(T dto) {
        val data = gson.toJson(dto);
        try {
            getRestApiTableClient().addRecord(data);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Недействительный адрес");
        }
        this.updateTable();
    }

    protected void closeStage() {
        ((Stage) anchorPaneBase.getScene().getWindow()).close();
    }

    public abstract String getTableName();

    public abstract RestApiTableClient getRestApiTableClient();

    public abstract Class<T> getDependentClass();

    public abstract T getDTOMoreAdd();
}
