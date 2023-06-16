package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumnByColumnAnnotation;

public class MapColumnName implements IMapperColumnByColumnAnnotation {

	public void map(String block, Table table) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith("@Column")) {
					result = line.replaceAll(".*.name|,.*|[^A-Z-a-z-0-9-_]", "");
					table.getLastColumn().setName(result);
				}
			}
		} catch (Exception e) {
		}
	}

}
