package br.com.torres.ddlGenerator.factory;

import java.util.List;

import br.com.torres.ddlGenerator.services.IGenerateScript;

public interface IGenerateScriptsFactory {

	public List<IGenerateScript> get();
	
}
