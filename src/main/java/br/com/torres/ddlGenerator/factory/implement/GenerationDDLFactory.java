package br.com.torres.ddlGenerator.factory.implement;

import br.com.torres.ddlGenerator.factory.IFileReaderFactory;
import br.com.torres.ddlGenerator.factory.IGenerationDDLFactory;
import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.factory.IAdjustmentsFactory;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.implement.GenerateDDL;

public class GenerationDDLFactory implements IGenerationDDLFactory {

	private final IReadDirectoriesFactory readDirectoriesFactory;
	private final IFileReaderFactory fileReaderFactory;
	private final IAdjustmentsFactory validationsFactory;

	public GenerationDDLFactory(final IReadDirectoriesFactory readDirectoriesFactory,
			final IFileReaderFactory fileReaderFactory, final IAdjustmentsFactory validationsFactory) {
		this.readDirectoriesFactory = readDirectoriesFactory;
		this.fileReaderFactory = fileReaderFactory;
		this.validationsFactory = validationsFactory;
	}

	public IGenerateDDL get() {
		return new GenerateDDL(validationsFactory.get(),readDirectoriesFactory.get(), fileReaderFactory.get());
	}

}
