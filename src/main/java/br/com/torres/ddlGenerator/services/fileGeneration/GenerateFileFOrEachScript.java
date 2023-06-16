package br.com.torres.ddlGenerator.services.fileGeneration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;
import br.com.torres.ddlGenerator.services.IFileGenerator;

public class GenerateFileFOrEachScript implements IFileGenerator {

	private final String FILE_NAME = "R__%04d_%s.sql";// FILE_COUNT | TABLE_NAME

	@Override
	public void generate(List<Script> scripts, String pathExport) {
		// TODO Auto-generated method stub
		try {
			writeFile(scripts, pathExport);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(List<Script> scripts, String pathExport) throws IOException {
		int cont = 1;
		for (Script script : scripts) {
			String fileName = String.format(FILE_NAME, cont,script.getName());
			FileWriter myWriter = new FileWriter(pathExport.concat(fileName));
			myWriter.write(script.getScript());
			myWriter.close();
			cont++;
		}
	}

	@Override
	public Boolean executable(ScriptFileFormateType type) {
		// TODO Auto-generated method stub
		return type.equals(ScriptFileFormateType.FILE_FOR_EACH_SCRIPT);
	}

}
