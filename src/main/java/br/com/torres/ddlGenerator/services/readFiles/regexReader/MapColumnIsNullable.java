package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnIsNullable implements IMapperColumn {

	public void map(String line, Table table) {
		String result = "";
		if (RegexConditionsString.startsWith(line, "@Column")) {
			result = getType(line).replaceAll(".*.nullable|=|,.*|[()]", "");
			table.getLastColumn()
					.setNullable(result.isEmpty() ? "NULL" : result.toLowerCase().equals("true") ? "NULL" : "NOT NULL");
		} else if (RegexConditionsString.startsWith(line, "@NotNull")) {
			table.getLastColumn().setNullable("NOT NULL");
		}
	}

	public String getType(String block) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().toLowerCase().contains("nullable")) {
					result = line;
				}
			}
		} catch (Exception e) {
		}
		return result;
	}
}
