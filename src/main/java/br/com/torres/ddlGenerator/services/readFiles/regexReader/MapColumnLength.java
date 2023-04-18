package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnLength implements IMapperColumn {

	public void map(String line, Table table) {
		String result = "";
		String type = "";
		if (RegexConditionsString.startsWith(line, "@Column")) {
			type = getType(line).trim();

			if (type.startsWith("@Length")) {
				result = type.replaceAll(".*.max|=|[)]", "").trim();
				table.getLastColumn().setLength(result.isEmpty() ? "100" : result);
			} else if (type.startsWith("@Column")) {
				result = type.replaceAll(".*.length|=|,.*|[()]", "").trim();
				table.getLastColumn().setLength(result.isEmpty() ? "100" : result);
			}
		}
	}

	public String getType(String block) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().toLowerCase().contains("length")) {
					result = line;
				}
			}
		} catch (Exception e) {
		}
		return result;
	}
}
