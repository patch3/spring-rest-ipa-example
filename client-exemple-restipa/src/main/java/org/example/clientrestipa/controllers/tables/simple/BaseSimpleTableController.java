package org.example.clientrestipa.controllers.tables.simple;

import org.example.clientrestipa.controllers.tables.BaseTableController;
import org.example.clientrestipa.dto.IDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class BaseSimpleTableController<T extends IDTO> extends BaseTableController<T, T, T> {
    @Override
    protected List<T> prepareInfoInUpdateTable(List<T> dto) {
        return dto;
    }

    @Override
    protected void deleteItem(T dto) throws IOException, URISyntaxException {
        super.tableService.delete(dto.getId());
    }
}
