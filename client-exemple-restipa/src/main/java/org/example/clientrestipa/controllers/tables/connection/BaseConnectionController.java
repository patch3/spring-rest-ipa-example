package org.example.clientrestipa.controllers.tables.connection;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import lombok.val;
import org.example.clientrestipa.controllers.tables.BaseTableController;
import org.example.clientrestipa.dto.BaseDTO;
import org.example.clientrestipa.dto.connections.IdConnectionDTO;
import org.example.clientrestipa.dto.connections.SimpleConnectionDTO;
import org.example.clientrestipa.managers.AlertManager;
import org.example.clientrestipa.servise.TableService;
import org.example.clientrestipa.servise.connections.TableConnectionService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseConnectionController<T extends BaseDTO, U extends BaseDTO>
        extends BaseTableController<SimpleConnectionDTO<T, U>, T, IdConnectionDTO> {

    protected final TableConnectionService<T> tableConnectionService;
    @FXML
    private ComboBox<T> firstCombo;
    @FXML
    private ComboBox<U> secondCombo;


    public BaseConnectionController() {
        super();
        this.tableConnectionService = new TableConnectionService<>(super.tableService, this.getConnectionTableName());
    }

    @Override
    protected List<SimpleConnectionDTO<T, U>> prepareInfoInUpdateTable(List<T> dto) throws IOException, URISyntaxException {
        return dto.stream().flatMap(
                clientItem -> clientItem.getConnection().stream()
                        .map(bookItem -> new SimpleConnectionDTO<>(clientItem, (U) bookItem))
        ).toList();
    }

    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        this.initializeCombo();
    }

    private void initializeCombo() {
        val tableSecond = new TableService<>(this.getConnectionTableName(), this.getClassForDataSecondRequest());
        try {
            this.firstCombo.setPromptText("Item");
            this.firstCombo.getItems().addAll(this.tableService.findAll());

            this.secondCombo.setPromptText("Connection item");
            this.secondCombo.getItems().addAll(tableSecond.findAll());
        } catch (IOException ex) {
            AlertManager.showErrorAlert("Ошибка получения данных с сервера!\n" + ex.getMessage());
        } catch (URISyntaxException ex) {
            AlertManager.showErrorAlert("Недействительный адрес\n" + ex.getMessage());
        }

    }

    @Override
    public IdConnectionDTO getDTOMoreAdd() {
        val selectedFirstItem = this.firstCombo.getValue();
        val selectedSecondItem = this.secondCombo.getValue();

        if (selectedFirstItem == null || selectedSecondItem == null) return null;

        return new IdConnectionDTO(selectedFirstItem.getId(), selectedSecondItem.getId());
    }


    public abstract String getConnectionTableName();

    public abstract Class<U> getClassForDataSecondRequest();


    @Override
    protected void deleteItem(SimpleConnectionDTO<T, U> dto)
            throws IOException, URISyntaxException {
        this.tableConnectionService.delete(
                new IdConnectionDTO(dto.getId(), dto.getConnectionId())
        );
    }
}
