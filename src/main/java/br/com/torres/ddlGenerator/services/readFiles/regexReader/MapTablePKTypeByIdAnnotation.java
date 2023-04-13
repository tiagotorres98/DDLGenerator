package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;

public class MapTablePKTypeByIdAnnotation implements IMapperColumn {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("@Id")) {
			result = getType(line)
					 .replaceAll("private", "")
					 .trim()
					 .replaceAll(" .*", "");
			table.setPrimaryKeyType(result);
		}
	}
	
	public String getType(String block) {
		String result = "";
		try(Scanner scan = new Scanner(block)){
			while(scan.hasNext()) {
				String line = scan.nextLine();
				if(line.trim().startsWith("private")) {
					result = line;
				}
			}
		}
		return result;
	}
	
}
