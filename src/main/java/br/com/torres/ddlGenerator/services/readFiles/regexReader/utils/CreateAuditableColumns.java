package br.com.torres.ddlGenerator.services.readFiles.regexReader.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Column;

public class CreateAuditableColumns {

	public static List<Column> get(){
		List<Column> result = new ArrayList<Column>();
		result.add(Column.builder().name("CREATED_BY").type("string").length("100").nullable("NOT NULL").build());
		result.add(Column.builder().name("CREATED_DATE").type("LocalDateTime").nullable("NOT NULL").build());
		result.add(Column.builder().name("LAST_MODIFIED_BY").type("string").length("100").nullable("NULL").build());
		result.add(Column.builder().name("LAST_MODIFIED_DATE").type("LocalDateTime").nullable("NOT NULL").build());
		return result;
	}
	
}

