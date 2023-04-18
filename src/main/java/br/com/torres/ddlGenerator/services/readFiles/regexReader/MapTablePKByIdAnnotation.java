package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;

public class MapTablePKByIdAnnotation implements IMapperColumn {

	public void map(String line, Table table) {
		String result = "";
		if (line.trim().contains("@Id")) {
			result = getPk(line).replaceAll(".*.name|,.*|[^A-Z-a-z-0-9-_]", "");
			table.setPrimaryKey(result);
		}
	}

	public String getPk(String block) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith("@Column")) {
					result = line;
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

}
