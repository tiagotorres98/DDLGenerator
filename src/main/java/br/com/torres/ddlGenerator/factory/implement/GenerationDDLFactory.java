package br.com.torres.ddlGenerator.factory.implement;

import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IGenerateScriptsFactory;
import br.com.torres.ddlGenerator.factory.IGenerationDDLFactory;
import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.factory.IAdjustmentsFactory;
import br.com.torres.ddlGenerator.factory.IFileGeneratorFactory;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.implement.GenerateDDL;
import br.com.torres.ddlGenerator.services.implement.GenerateDDLByDataBase;

public class GenerationDDLFactory implements IGenerationDDLFactory {

	private final IReadDirectoriesFactory readDirectoriesFactory;
	private final IFileReaderFactory fileReaderFactory;
	private final IAdjustmentsFactory validationsFactory;
	private final IGenerateScriptsFactory generateScriptsFactory;
	private final IFileGeneratorFactory fileGeneratorFactory;

	public GenerationDDLFactory(final IFileGeneratorFactory fileGeneratorFactory,final IGenerateScriptsFactory generateScriptsFactory,
			final IReadDirectoriesFactory readDirectoriesFactory, final IFileReaderFactory fileReaderFactory,
			final IAdjustmentsFactory validationsFactory) {
		this.readDirectoriesFactory = readDirectoriesFactory;
		this.fileReaderFactory = fileReaderFactory;
		this.validationsFactory = validationsFactory;
		this.generateScriptsFactory = generateScriptsFactory;
		this.fileGeneratorFactory = fileGeneratorFactory;

	}

	public IGenerateDDL get() {
		//return new GenerateDDL(fileGeneratorFactory.get(),generateScriptsFactory.get(),validationsFactory.get(), readDirectoriesFactory.get(), fileReaderFactory.get());
		return new GenerateDDLByDataBase(generateScriptsFactory.get(),fileGeneratorFactory.get());
	}

}
