package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.utils.RegexConditionsString;

public class MapColumnIsUnique implements IMapperColumn {

	public void map(String line, Table table) {
		String result = "";
		String unique = "";
		if (line.toLowerCase().contains("unique")) {
			unique = getUnique(line).trim();

			if (unique.startsWith("@Table")) {
				result = unique.replaceAll(".*.columnNames|[={}()\"]", "").trim();
			} else if (unique.startsWith("@Column")) {
				result = unique.replaceAll(".*.unique|,.*|[= ()]", "").trim();
				result = result.equals("true") ? table.getLastColumn().getName() : "";
			} else {
				result = unique.replaceAll(".*.columnNames|[={}()\"]", "").trim();
			}
			
			if(result.endsWith(",")) {
				StringBuffer t = new StringBuffer();
				t.append(result);
				result = t.deleteCharAt(t.length()-1).toString();
			}

			if (!result.isEmpty())
				table.setUniqueColumns(result);

		}
	}

	public String getUnique(String block) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().toLowerCase().contains("unique")) {
					result = line;
				}
			}
		} catch (Exception e) {
		}
		return result;
	}
}
