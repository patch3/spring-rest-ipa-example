package org.example.clientrestipa.controllers.tables.connection;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.dto.BookDTO;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.dto.connections.IConnectionDTO;
import org.example.clientrestipa.dto.connections.SimpleConnectionDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientConnectionController
        extends BaseConnectionController<ClientDTO, BookDTO> {

    
    @Override
    protected List<TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, ?>> initializeListColumnForTable() {
        return new ArrayList<>(6) {{
            add(new TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, Long>("ID client") {{
                setCellValueFactory(connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getId()));
            }});
            add(new TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, String>("First name client") {{
                setCellValueFactory(connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getFirstEntity().getFirstName()));
            }});
            add(new TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, String>("Last name client") {{
                setCellValueFactory(connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getFirstEntity().getLastName()));
            }});
            add(new TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, Long>("Id book") {{
                setCellValueFactory(connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getConnectionId()));
            }});
            add(new TableColumn<SimpleConnectionDTO<ClientDTO, BookDTO>, String>("Name book") {{
                setCellValueFactory(connectionCell -> new ReadOnlyObjectWrapper<>(connectionCell.getValue().getSecondEntity().getName()));
            }});
            add(createDeleteActionColumn());
        }};
    }

    @Override
    protected Class<ClientDTO> getClassForDataRequest() {
        return ClientDTO.class;
    }

    @Override
    public String getTableName() {
        return SocketConfig.TABLE_NAME_CLIENT;
    }

    @Override
    public String getConnectionTableName() {
        return SocketConfig.TABLE_NAME_BOOK;
    }

    @Override
    public Class<BookDTO> getClassForDataSecondRequest() {
        return BookDTO.class;
    }
}











    /*private final RestApiTableConnectionClient restTableConnectionClient =
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
                val connection = new ConnectionDTO(client.getId(), book.getId());
                restTableConnectionClient.addRecord(ClientDTO.TABLE_NAME, gson.toJson(connection));
            } catch (IOException | URISyntaxException e) {
                AlertManager.showErrorAlert(e.getMessage());
            }
        });
        try {
            Type typeList = TypeToken.getParameterized(List.class, ClientDTO.class).getType();
            List<ClientDTO> clientList = gson.fromJson(restTableConnectionClient.getTable(), typeList);
            clientCombo.setItems(FXCollections.observableList(clientList));
        } catch (IOException ex) {
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
        } catch (URISyntaxException ex) {
            AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
        }
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
                                        AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
                                    } catch (URISyntaxException ex) {
                                        AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
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
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
        } catch (URISyntaxException ex) {
            AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
        }
    }*/
