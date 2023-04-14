package br.com.torres.ddlGenerator.factory.implement;

import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IReadFileToFillTableEntityFactory;
import br.com.torres.ddlGenerator.services.IFileReader;
import br.com.torres.ddlGenerator.services.readFiles.FileReader;

public class FileReaderFactory implements IFileReaderFactory {

	private final IReadFileToFillTableEntityFactory readFileFactory = new ReadFileToFillTableEntityFactory();
	
	@Override
	public IFileReader get() {
		// TODO Auto-generated method stub
		return new FileReader(readFileFactory);
	}
}
