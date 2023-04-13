package br.com.torres.ddlGenerator.services;

import java.util.List;

import br.com.torres.ddlGenerator.entities.Table;

public interface IReadFileToFillTableEntity {

	public void read(List<String> blocks, Table table);
	
}
