package org.example.clientrestipa.controllers.tables.simple;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.dto.ClientDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public final class ClientController extends BaseSimpleTableController<ClientDTO> {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }


    @Override
    public Class<ClientDTO> getClassForDataRequest() {
        return ClientDTO.class;
    }

    @Override
    public String getTableName() {
        return SocketConfig.TABLE_NAME_CLIENT;
    }

    //@Override
    public ClientDTO getDTOMoreAdd() {
        return new ClientDTO(null, this.firstNameField.getText(), this.lastNameField.getText());
    }


    @Override
    protected List<TableColumn<ClientDTO, ?>> initializeListColumnForTable() {
        return new ArrayList<>(4) {{
            add(new TableColumn<ClientDTO, Long>("ID") {{
                setCellValueFactory(new PropertyValueFactory<>("id"));
            }});
            add(new TableColumn<ClientDTO, String>("First name") {{
                setCellValueFactory(new PropertyValueFactory<>("firstName"));
            }});
            add(new TableColumn<ClientDTO, String>("Last name") {{
                setCellValueFactory(new PropertyValueFactory<>("lastName"));
            }});
            add(createDeleteActionColumn());
        }};
    }


}
