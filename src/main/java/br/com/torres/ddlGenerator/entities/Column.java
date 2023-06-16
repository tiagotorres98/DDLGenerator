package br.com.torres.ddlGenerator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {

	private String name;
	private String type;
	private String nullable;
	private String length;
	
	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", nullable=" + nullable + ", length=" + length + "]";
	}
	
	
}
