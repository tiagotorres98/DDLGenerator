package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTableName implements IMapperTable {

	
	public void map(String line, Table table) {
			
		String result = "";
		if(line.trim().startsWith("@Table")) {
			result = line.trim()
					.replaceAll("[{].*.[}]", "")
					.replaceAll("@Table.*.name", "")
					.replaceAll(",.*", "")
					.replaceAll("[^0-9-A-Z-a-z_]", "");
			table.setName(result);
		}
		
	}

}
