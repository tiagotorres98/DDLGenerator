package br.com.torres.ddlGenerator.factory.implement;

import br.com.torres.ddlGenerator.factory.IGenerationDDLFactory;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.implement.GenerateDDL;

public class GenerationDDLFactory implements IGenerationDDLFactory{

	public IGenerateDDL get() {
		return new GenerateDDL();
	}

}
