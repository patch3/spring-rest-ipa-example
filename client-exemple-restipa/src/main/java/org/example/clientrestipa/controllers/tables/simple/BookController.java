package org.example.clientrestipa.controllers.tables.simple;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.clientrestipa.configs.SocketConfig;
import org.example.clientrestipa.dto.BookDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookController extends BaseSimpleTableController<BookDTO> {
    @FXML
    public TextField nameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }


    @Override
    public Class<BookDTO> getClassForDataRequest() {
        return BookDTO.class;
    }

    @Override
    public String getTableName() {
        return SocketConfig.TABLE_NAME_BOOK;
    }

    @Override
    public BookDTO getDTOMoreAdd() {
        return new BookDTO(null, nameField.getText());
    }

    @Override
    protected List<TableColumn<BookDTO, ?>> initializeListColumnForTable() {
        return new ArrayList<>(3) {{
            add(new TableColumn<BookDTO, Long>("ID") {{
                setCellValueFactory(new PropertyValueFactory<>("id"));
            }});
            add(new TableColumn<BookDTO, String>("Name book") {{
                setCellValueFactory(new PropertyValueFactory<>("name"));
            }});
            add(createDeleteActionColumn());
        }};
    }
}
