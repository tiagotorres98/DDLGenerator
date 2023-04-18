package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IFileGeneratorFactory;
import br.com.torres.ddlGenerator.services.IFileGenerator;
import br.com.torres.ddlGenerator.services.fileGeneration.*;

public class FileGeneratorFactory implements IFileGeneratorFactory {

	@Override
	public List<IFileGenerator> get() {
		List<IFileGenerator> list = new ArrayList<IFileGenerator>();
		list.add(new GenerateAllScriptAtOneFile());
		list.add(new GenerateFileFOrEachScript());
		return list;
	}

}
