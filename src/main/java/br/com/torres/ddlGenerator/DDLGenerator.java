package br.com.torres.ddlGenerator;

import br.com.torres.ddlGenerator.factory.IAdjustmentsFactory;
import br.com.torres.ddlGenerator.factory.IFileGeneratorFactory;
import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IGenerateScriptsFactory;
import br.com.torres.ddlGenerator.factory.IGenerationDDLFactory;
import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.factory.implement.AdjustmentsFactory;
import br.com.torres.ddlGenerator.factory.implement.FileGeneratorFactory;
import br.com.torres.ddlGenerator.factory.implement.FileReaderFactory;
import br.com.torres.ddlGenerator.factory.implement.GenerateScriptsFactory;
import br.com.torres.ddlGenerator.factory.implement.GenerationDDLFactory;
import br.com.torres.ddlGenerator.factory.implement.ReadDirectoriesFactory;

public class DDLGenerator {

	private final static IReadDirectoriesFactory readDirectoriesFactory = new ReadDirectoriesFactory();
	private final static IFileReaderFactory fileReaderFactory = new FileReaderFactory();
	private final static IAdjustmentsFactory adjustmentsFactory = new AdjustmentsFactory();
	private final static IGenerateScriptsFactory generateScritpsFactory = new GenerateScriptsFactory();
	private final static IFileGeneratorFactory fileGeneratorFactory = new FileGeneratorFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		IGenerationDDLFactory factory = new GenerationDDLFactory(fileGeneratorFactory, generateScritpsFactory,
				readDirectoriesFactory, fileReaderFactory, adjustmentsFactory);
		factory.get().generate();
	}

}
