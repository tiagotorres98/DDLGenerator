package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnIsNullable implements IMapperColumn {

	@Override
	public void map(String line, Table table) {
		String result = "";
		if (RegexConditionsString.startsWith(line,"@Column")) {
			result = getType(line)
					.replaceAll(".*.nullable|=|,.*|[()]", "");
			table.getLastColumn().setNullable(result.isEmpty()?"true":result);
		}
	}

	public String getType(String block) {
		String result = "";
		try(Scanner scan = new Scanner(block)){
			while(scan.hasNext()) {
				String line = scan.nextLine();
				if(line.trim().toLowerCase().contains("nullable")) {
					result = line;
				}
			}
		}
		return result;
	}
}
