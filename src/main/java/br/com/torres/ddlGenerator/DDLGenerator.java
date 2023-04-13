package br.com.torres.ddlGenerator;

import br.com.torres.ddlGenerator.factory.IGenerationDDLFactory;
import br.com.torres.ddlGenerator.factory.implement.GenerationDDLFactory;

public class DDLGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGenerationDDLFactory factory = new GenerationDDLFactory();
		factory.get().generate();
	}

}
