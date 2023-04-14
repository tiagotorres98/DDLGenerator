package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;

public class MapColumnNameByJoinColumnAnnotation implements IMapperColumn {

	@Override
	public void map(String block, Table table) {
		String result = "";
		try(Scanner scan = new Scanner(block)){
			while(scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith("@JoinColumn")) {
					result = line.trim().replaceAll(".*.name|,.*|[^A-Z-a-z-0-9-_]", "");
					table.getLastColumn().setName(result);
					table.getLastColumn().setNullable("NOT NULL");
				}
			}
		}
	}

}
