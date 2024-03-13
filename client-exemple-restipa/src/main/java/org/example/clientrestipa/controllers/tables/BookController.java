package org.example.clientrestipa.controllers.tables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.clientrestipa.dto.BookDTO;
import org.example.clientrestipa.utils.RestApiTableClient;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class BookController extends BaseTableController<BookDTO> implements Initializable {
    public final static String TABLE_NAME = "book";

    @FXML
    public TextField nameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        super.table.getColumns().setAll(
                new LinkedHashSet<>(3) {{
                    add(new TableColumn<BookDTO, String>("ID") {{
                        setCellValueFactory(new PropertyValueFactory<>("id"));
                    }});
                    add(new TableColumn<BookDTO, String>("Name book") {{
                        setCellValueFactory(new PropertyValueFactory<>("name"));
                    }});
                    add(createDeleteActionColumn());
                }}
        );
        this.updateTable();
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
    public Class<BookDTO> getDependentClass() {
        return BookDTO.class;
    }

    //@Override
    public BookDTO getDTOMoreAdd() {
        return new BookDTO(null, nameField.getText());
    }
}
