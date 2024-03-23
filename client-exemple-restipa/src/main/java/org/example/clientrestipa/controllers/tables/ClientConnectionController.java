package org.example.clientrestipa.controllers.tables;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.val;
import org.example.clientrestipa.controllers.BaseController;
import org.example.clientrestipa.dto.BookDTO;
import org.example.clientrestipa.dto.ClientConnectionDTO;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.managers.AlertManager;
import org.example.clientrestipa.utils.RestApiTableConnectionClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

public class ClientConnectionController extends BaseController implements Initializable {
    private final RestApiTableConnectionClient restTableConnectionClient =
            new RestApiTableConnectionClient(BaseTableController.REST_CLIENT, ClientDTO.TABLE_NAME, BookDTO.TABLE_NAME);

    private final Gson gson = new Gson();

    @FXML
    public Button addButton;

    @FXML
    public TableView<ClientAndBook> table;

    @FXML
    private ComboBox<ClientDTO> clientCombo;

    @FXML
    private ComboBox<BookDTO> bookCombo;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeTable();
        this.initializeAddMenu();
    }

    private void initializeAddMenu(){
        this.addButton.setOnAction(event -> {
            ClientDTO client = this.clientCombo.getSelectionModel().getSelectedItem();
            BookDTO book = this.bookCombo.getSelectionModel().getSelectedItem();
            if (client == null || book == null) {
                AlertManager.showErrorAlert("Please select client and book");
                return;
            }
            try {
                val connection = new ClientConnectionDTO(client.getId(), book.getId());
                restTableConnectionClient.addRecord(ClientDTO.TABLE_NAME, gson.toJson(connection));
            } catch (IOException | URISyntaxException e) {
                AlertManager.showErrorAlert(e.getMessage());
            }
        });

        val
        clientCombo.setItems(FXCollections.observableList());
    }

    private void initializeTable() {
        table.getColumns().setAll(
                new LinkedHashSet<>(4) {{
                    add(new TableColumn<ClientAndBook, Long>("ID client") {{
                        setCellValueFactory(
                                connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getIdClient())
                        );
                    }});
                    add(new TableColumn<ClientAndBook, String>("First name client") {{
                        setCellValueFactory(
                                connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getFirstNameClient())
                        );
                    }});
                    add(new TableColumn<ClientAndBook, String>("Last name client") {{
                        setCellValueFactory(
                                connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getLastNameClient())
                        );
                    }});
                    add(new TableColumn<ClientAndBook, Long>("Id book") {{
                        setCellValueFactory(
                                connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getIdBook())
                        );
                    }});
                    add(new TableColumn<ClientAndBook, String>("Name book") {{
                        setCellValueFactory(
                                connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getNameBook())
                        );
                    }});
                    add(deleteActionDelete());
                }}
        );
        this.updateTable();
    }

    private TableColumn<ClientAndBook, Button> deleteActionDelete() {
        return new TableColumn<>("Action delete") {{
            setCellValueFactory(connectionCell -> new SimpleObjectProperty<>(new Button("Delete")));
            setCellFactory(
                    param -> new TableCell<>() {
                        @Override
                        protected void updateItem(Button item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                item.setOnAction(event -> {
                                    try {
                                        val objDTO = getTableView().getItems().get(getIndex());
                                        restTableConnectionClient.deleteRecord(objDTO.getIdClient(), objDTO.getIdBook());
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
                                    } catch (URISyntaxException ex) {
                                        ex.printStackTrace();
                                        AlertManager.showErrorAlert("Недействительный адрес");
                                    }
                                });
                                setGraphic(item);
                            }
                        }
                    }
            );
        }};
    }


    public void updateTable() {
        try {
            Type typeList = TypeToken.getParameterized(List.class, ClientDTO.class).getType();
            List<ClientDTO> clientList = gson.fromJson(
                    restTableConnectionClient.getTable(),
                    typeList
            );
            val connectionList = clientList.stream().flatMap(
                            clientItem -> clientItem.getBooks().stream()
                                    .map(bookItem -> new ClientAndBook(clientItem, bookItem))
                    ).toList();

            this.table.setItems(FXCollections.observableList(connectionList));
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            AlertManager.showErrorAlert("Недействительный адрес");
        }
    }

    record ClientAndBook(ClientDTO client, BookDTO book) {
        public Long getIdClient() {
            return client.getId();
        }
        public Long getIdBook() {
            return book.getId();
        }
        public String getFirstNameClient() {
            return client.getFirstName();
        }
        public String getLastNameClient() {
            return client.getLastName();
        }
        public String getNameBook() {
            return book.getName();
        }
    }
}
