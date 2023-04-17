package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnTypeByEnumeratedTag implements IMapperColumn {

	@Override
	public void map(String block, Table table) {
		String result = "";
		if (RegexConditionsString.startsWith(block, "@Enumerated")) {
			result = getType(block).trim().replaceAll(".*.EnumType|[.)]", "");
			table.getLastColumn().setType(result);
		}
	}

	public String getType(String block) {
		String result = "";
		try (Scanner scan = new Scanner(block)) {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith("@Enumerated")) {
					result = line;
				}
			}
		}
		return result;
	}

}
