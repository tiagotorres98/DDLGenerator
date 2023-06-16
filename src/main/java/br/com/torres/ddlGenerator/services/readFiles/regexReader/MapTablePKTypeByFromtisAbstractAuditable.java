package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.CreateAuditableColumns;

public class MapTablePKTypeByFromtisAbstractAuditable implements IMapperTable {

	
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("AbstractAuditable<")) {
			result = line.replaceAll("^.*.AbstractAuditable<|>.*", "");
			table.setPrimaryKeyType(result);	
			table.getColumns().addAll(CreateAuditableColumns.get());
		}
	}
}
