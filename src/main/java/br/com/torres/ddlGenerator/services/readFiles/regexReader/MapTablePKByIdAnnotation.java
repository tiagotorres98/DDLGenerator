package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;

public class MapTablePKByIdAnnotation implements IMapperColumn {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("@Id")) {
			result = getPk(line).replaceAll(".*.name|,.*|[^A-Z-a-z-0-9-_]", "");
			table.setPrimaryKey(result);
		}
	}
	
	public String getPk(String block) {
		String result = "";
		try(Scanner scan = new Scanner(block)){
			while(scan.hasNext()) {
				String line = scan.nextLine();
				if(line.trim().startsWith("@Column")) {
					result = line;
				}
			}
		}
		return result;
	}
	
}
