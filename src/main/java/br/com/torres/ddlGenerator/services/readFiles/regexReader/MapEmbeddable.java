package br.com.torres.ddlGenerator.services.readFiles.regexReader;

import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.IMapperEmbeddable;

public class MapEmbeddable implements IMapperEmbeddable {

	public void map(String block, Table table) {
		String result = "";
		Scanner scan = new Scanner(block);
		if (block.contains("@Embeddable")) {
			table.setEmbeddableTable(Boolean.TRUE);
		}
	}

}
