package br.com.torres.ddlGenerator.entities;

import lombok.Data;

@Data
public class Column {

	private String name;
	private String type;
	private String nullable;
	private String unique;
	private String size;
	
	
	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", nullable=" + nullable + ", unique=" + unique + ", size="
				+ size + "]";
	}
	
	
}
