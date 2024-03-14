package org.example.clientrestipa.controllers.tables;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lombok.Value;
import org.example.clientrestipa.dto.BookDTO;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.dto.ConnectionDTO;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class ClientConnectionController extends BaseTableController<ClientDTO> {
    private final static String TABLE_NAME = "clientconnection";
    public ComboBox clientCombo;
    public ComboBox bookCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        super.table.getColumns().setAll(
                new LinkedHashSet<>(4) {{
                    add(new TableColumn<ClientDTO, String>("ID") {{
                        setCellValueFactory(new PropertyValueFactory<>("id"));
                    }});
                    add(new TableColumn<ClientDTO, String>("First name") {{
                        setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    }});
                    add(new TableColumn<ClientDTO, String>("Last name") {{
                        setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    }});
                    add(new TableColumn<ClientDTO, Long>("Book id") {{
                        setCellValueFactory(param -> new ReadOnlyObjectWrapper<Long>(){
                            private Long id;
                            public ObservableValue<Long> call(CellDataFeatures<ClientDTO, Long> p) {
                                return new ReadOnlyObjectWrapper(((ClientDTO)p.getValue()).getBooks().get(0).getId());
                            }
                        });
                    }});
                }}
        );
        super.updateTable();
    }
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public RestApiTableClient getRestApiTableClient() {
        return new RestApiTableClient(BaseTableController.REST_CLIENT, TABLE_NAME);
    }

    @Override
    public Class<ClientDTO> getDependentClass() {
        return ClientDTO.class;
    }

    @Override
    public ClientDTO getDTOMoreAdd() {
        return null;
    }

    @Value
    static class ClientAndBook {
        ClientDTO client;
        BookDTO book;
    }
}
