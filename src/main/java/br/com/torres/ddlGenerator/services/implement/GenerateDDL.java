package br.com.torres.ddlGenerator.services.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.factory.implement.FileReaderFactory;
import br.com.torres.ddlGenerator.factory.implement.ReadDirectoriesFactory;
import br.com.torres.ddlGenerator.services.IFileReader;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.IReadDirectories;
import br.com.torres.ddlGenerator.services.IAdjustments;

public class GenerateDDL implements IGenerateDDL {

	private final IReadDirectories readDirectories;
	private final IFileReader fileReader;
	private final List<IAdjustments> adjustments;
	private List<Table> tables;
	
	public GenerateDDL(final List<IAdjustments> adjustments,final IReadDirectories readDirectories,final IFileReader fileReader) {
		this.readDirectories = readDirectories;
		this.fileReader = fileReader;
		this.adjustments = adjustments;
	}
		
	public void generate() {
		tables = new ArrayList<>();
		File[] files = readDirectories.read();
		readFiles(files);
		adjustments();
	}
	
	public void adjustments() {
		adjustments.forEach(a -> a.adjusting(this.tables));
	}
	
	public void readFiles(File[] files) {
		Arrays.asList(files).forEach(f->tables.add(fileReader.readFile(f)));
	}

}
