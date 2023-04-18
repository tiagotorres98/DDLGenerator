package br.com.torres.ddlGenerator.factory;

import java.util.List;

import br.com.torres.ddlGenerator.services.IFileGenerator;

public interface IFileGeneratorFactory {

	public List<IFileGenerator> get();
	
}
