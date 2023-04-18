package br.com.torres.ddlGenerator.services.fileGeneration;

import java.util.List;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;
import br.com.torres.ddlGenerator.services.IFileGenerator;

public class GenerateFileFOrEachScript implements IFileGenerator {

	@Override
	public void generate(List<Script> scripts, String pathExport) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean executable(ScriptFileFormateType type) {
		// TODO Auto-generated method stub
		return type.equals(ScriptFileFormateType.FILE_FOR_EACH_SCRIPT);
	}

}
