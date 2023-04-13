package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTablePKTypeByAttributeOverrideColumnDefinition implements IMapperTable {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().startsWith("@AttributeOverride")) {
			if (line.contains("columnDefinition")) {
				result = line.replaceAll("^.*.columnDefinition.*.=.[^A-Z-a-z-0-9-_]|\".*", "");
				table.setPrimaryKeyType(result);
			}
		} 
	}
}
