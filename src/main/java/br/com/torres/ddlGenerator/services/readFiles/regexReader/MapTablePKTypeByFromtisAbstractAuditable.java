package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapTablePKTypeByFromtisAbstractAuditable implements IMapperTable {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("AbstractPersistable<")) {
			result = line.replaceAll("^.*.AbstractPersistable<|>.*", "");
			table.setPrimaryKeyType(result);
			//System.out.println(result);
		}
	}
}
