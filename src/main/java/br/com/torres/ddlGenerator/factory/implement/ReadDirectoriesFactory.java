package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IReadDirectoriesFactory;
import br.com.torres.ddlGenerator.services.IReadDirectories;
import br.com.torres.ddlGenerator.services.readDirectories.ReadDirectoriesByFixedPathOnCode;

public class ReadDirectoriesFactory implements IReadDirectoriesFactory{
	
	public IReadDirectories get() {
		// TODO Auto-generated method stub
		List<IReadDirectories> list = new ArrayList<IReadDirectories>();
		//list.add(new ReadDirectoriesByScanner());
		list.add(new ReadDirectoriesByFixedPathOnCode());
		return list.get(0);
	}

}
