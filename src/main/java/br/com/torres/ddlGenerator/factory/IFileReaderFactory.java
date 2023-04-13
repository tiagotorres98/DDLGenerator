package br.com.torres.ddlGenerator.factory;

import br.com.torres.ddlGenerator.services.IFileReader;

public interface IFileReaderFactory {

	public IFileReader get();
	
}
