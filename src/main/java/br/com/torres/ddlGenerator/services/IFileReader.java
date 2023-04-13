package br.com.torres.ddlGenerator.services;

import java.io.File;

import br.com.torres.ddlGenerator.entities.Table;

public interface IFileReader {

	public Table readFile(File file);
	
}
