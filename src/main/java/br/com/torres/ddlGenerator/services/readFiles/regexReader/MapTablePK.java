package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTablePK implements IMapperTable {

	
	public void map(String line, Table table) {
		String result = "";
		if(line.trim().startsWith("@AttributeOverride")) {
			result = line.trim()
					.replaceAll("^.*.@Column", "")
					.replaceAll("[)].*", "")
					.replaceAll(",.*", "")
					.replaceAll("^.*.=", "")
					.replaceAll("[^A-Z-a-z-0-9-_]", "");
			table.setPrimaryKey(result);
		}
	}

}
