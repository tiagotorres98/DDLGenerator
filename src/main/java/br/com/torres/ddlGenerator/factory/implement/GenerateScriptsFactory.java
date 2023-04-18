package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IGenerateScriptsFactory;
import br.com.torres.ddlGenerator.services.IGenerateScript;
import br.com.torres.ddlGenerator.services.scripts.*;

public class GenerateScriptsFactory implements IGenerateScriptsFactory {

	@Override
	public List<IGenerateScript> get() {
		List<IGenerateScript> list = new ArrayList<IGenerateScript>();
		list.add(new GenerateSQLServerScritps());
		return list;
	}

}
