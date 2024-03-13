package org.example.clientrestipa.controllers.tables;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.dto.ConnectionDTO;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class ClientConnectionController extends BaseTableController<ClientDTO> {
    private final static String TABLE_NAME = "client-connection";
    public ComboBox clientCombo;
    public ComboBox bookCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        super.table.getColumns().setAll(
                new LinkedHashSet<>(4) {{
                    add(new TableColumn<ConnectionDTO, String>("ID") {{
                        setCellValueFactory(new PropertyValueFactory<>("id"));
                    }});
                    add(new TableColumn<ClientDTO, String>("First name") {{
                        setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    }});
                    add(new TableColumn<ClientDTO, String>("Last name") {{
                        setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    }});
                }}
        )
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
    public ConnectionDTO getDTOMoreAdd() {
        return null;
    }
}
