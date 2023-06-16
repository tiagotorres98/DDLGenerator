package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTablePKTypeByAbstractAuditableDefault implements IMapperTable {

	
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("AbstractAuditableDefault<")) {
			result = line.replaceAll("^.*.AbstractAuditableDefault<|>.*", "");
			table.setPrimaryKeyType(result);
			//System.out.println(result);
		}
	}
}
