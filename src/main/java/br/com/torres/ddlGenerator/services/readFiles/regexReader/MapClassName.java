package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperTable;

public class MapClassName implements IMapperTable {

	@Override
	public void map(String line,Table table) {
		String result = "";
		if(line.trim().startsWith("public class")) {
			result = line.trim()
					 .replaceAll("extends.*", "")
					 .replaceAll("implements.*", "")
					 .replaceAll("^.*.class", "")
					 .trim();
			
			table.setClassName(result);
		}
	}

}
