package org.example.clientrestipa.controllers.tables;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.val;
import org.example.clientrestipa.controllers.BaseController;
import org.example.clientrestipa.dto.IDTO;
import org.example.clientrestipa.managers.AlertManager;
import org.example.clientrestipa.servise.TableService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseTableController<TableType extends IDTO, DataType extends IDTO, ServerType extends IDTO> extends BaseController {

    protected final TableService<DataType> tableService;

    @FXML
    protected TableView<TableType> table;
    @FXML
    protected Button addButton;
    @FXML
    protected AnchorPane anchorPaneBase;


    public BaseTableController() {
        this.tableService = new TableService<>(this.getTableName(), this.getClassForDataRequest());
    }

    protected abstract List<TableType> prepareInfoInUpdateTable(List<DataType> dto) throws IOException, URISyntaxException;

    protected abstract Class<DataType> getClassForDataRequest();

    public abstract String getTableName();

    public abstract ServerType getDTOMoreAdd();


    protected abstract List<TableColumn<TableType, ?>> initializeListColumnForTable();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.addButton.setOnAction(event -> addEntryEvent(this.getDTOMoreAdd()));
        this.table.getColumns().setAll(initializeListColumnForTable());
        this.updateTable();
    }

    protected void updateTable() {
        try {
            val dtoList = this.tableService.findAll();
            ObservableList<TableType> data = FXCollections.observableList(this.prepareInfoInUpdateTable(dtoList));
            this.table.setItems(data);
        } catch (IOException ex) {
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
        } catch (URISyntaxException ex) {
            AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
        }
    }


    public void addEntryEvent(ServerType dto) {
        if (dto == null) {
            AlertManager.showErrorAlert("Не выбран объект");
            return;
        }

        try {
            this.tableService.save(dto);
        } catch (IOException ex) {
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
        } catch (URISyntaxException ex) {
            AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
        }
        this.updateTable();
    }

    // уникальный функционал удаления
    protected abstract void deleteItem(TableType dto) throws IOException, URISyntaxException;


    protected TableColumn<TableType, Button> createDeleteActionColumn() {
        return new TableColumn<>("Action delete") {{
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
                                deleteItem(objDTO);
                            } catch (IOException ex) {
                                AlertManager.showErrorAlert(
                                        "Ошибка получения данных с сервера!\n" + ex.getMessage());
                            } catch (URISyntaxException ex) {
                                AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
                            }
                            getTableView().getItems().remove(getIndex());
                        });
                        setGraphic(item);
                    }
                }
            });
        }};
    }
}
