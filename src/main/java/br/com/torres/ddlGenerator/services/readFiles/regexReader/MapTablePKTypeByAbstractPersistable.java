package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTablePKTypeByAbstractPersistable implements IMapperTable {

	
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("AbstractAuditable<")) {
			result = line.replaceAll("^.*.AbstractAuditable<|>.*", "");
			table.setPrimaryKeyType(result);
			//System.out.println(result);
		}
	}
}
