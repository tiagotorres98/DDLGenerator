package br.com.torres.ddlGenerator.services.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.factory.implement.FileReaderFactory;
import br.com.torres.ddlGenerator.factory.implement.ReadDirectoriesFactory;
import br.com.torres.ddlGenerator.services.IFileReader;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.IReadDirectories;

public class GenerateDDL implements IGenerateDDL {

	private final IReadDirectoriesFactory readDirectoriesFactory = new ReadDirectoriesFactory();
	private final IFileReaderFactory fileReaderFactory = new FileReaderFactory();
	private final IReadDirectories readDirectories = readDirectoriesFactory.get();
	private final IFileReader fileReader = fileReaderFactory.get();
	
	public void generate() {
		File[] files = readDirectories.read();
		readFiles(files);
	}
	
	public List<Table> readFiles(File[] files) {
		List<Table> list = new ArrayList<>();
		for(File file:files) {
			list.add(fileReader.readFile(file));
		}
		return list;
	}

}
