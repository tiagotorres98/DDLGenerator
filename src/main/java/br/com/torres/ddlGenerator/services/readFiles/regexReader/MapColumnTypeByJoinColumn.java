package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnTypeByJoinColumn implements IMapperColumn {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (RegexConditionsString.startsWith(line, "@JoinColumn")) {
			if (line.contains("columnDefinition")) {
				result = getColumnDefinition(line).replaceAll(".*.columnDefinition|=|\"[)]|\"", "").trim();
				table.getLastColumn().setType(result);
			} else {
				result = getType(line).replaceAll("private", "").trim().replaceAll(" .*", "");
				table.getLastColumn().setType(result);
			}
		}
	}
	
	public String getColumnDefinition(String block) {
		String result = "";
		try (Scanner scan = new Scanner(block)) {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().contains("columnDefinition")) {
					result = line;
				}
			}
		}
		return result;
	}

	public String getType(String block) {
		String result = "";
		try (Scanner scan = new Scanner(block)) {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith("private")) {
					result = line;
				}
			}
		}
		return result;
	}
}
