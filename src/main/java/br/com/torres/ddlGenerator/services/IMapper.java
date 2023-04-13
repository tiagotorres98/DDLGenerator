package br.com.torres.ddlGenerator.services;

import br.com.torres.ddlGenerator.entities.Table;

public interface IMapper {

	public void map(String line, Table table);
	
}
