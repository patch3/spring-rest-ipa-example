package org.example.clientrestipa.controllers.tables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.clientrestipa.dto.ClientDTO;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public final class ClientController extends BaseControllerTable<ClientDTO> {
    private final static String TABLE_NAME = "client";

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;


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
                    add(createDeleteActionColumn());
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
        return new RestApiTableClient(BaseControllerTable.REST_CLIENT, TABLE_NAME);
    }

    @Override
    public Class<ClientDTO> getDependentClass() {
        return ClientDTO.class;
    }

    //@Override
    public ClientDTO getDTOMoreAdd() {
        return new ClientDTO(null, this.firstNameField.getText(), this.lastNameField.getText());
    }
}
