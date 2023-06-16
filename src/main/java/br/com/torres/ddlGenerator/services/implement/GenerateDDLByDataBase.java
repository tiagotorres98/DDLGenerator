package br.com.torres.ddlGenerator.services.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;
import br.com.torres.ddlGenerator.services.ICreateScriptTables;
import br.com.torres.ddlGenerator.services.IFileGenerator;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.IGenerateScript;

public class GenerateDDLByDataBase implements IGenerateDDL {

	private final List<IGenerateScript> scritps;
	private final List<IFileGenerator> fileGenerator;
	private List<Table> tables;
	private List<Script> script;

	public GenerateDDLByDataBase(final List<IGenerateScript> scritps, final List<IFileGenerator> fileGenerator) {
		this.scritps = scritps;
		this.fileGenerator = fileGenerator;
	}

	public void generate() {
		ICreateScriptTables createTables = new CreateScriptsTables(); 
		script = new ArrayList<Script>();
		script.addAll(createTables.create());
		createFile("D:\\ZZZ_DDL_SISTEMAS_MANAGER\\",ScriptFileFormateType.FILE_FOR_EACH_SCRIPT);
	}
	
	public void createFile(String path, ScriptFileFormateType type) {
		fileGenerator.stream()
		.filter(f-> f.executable(type))
		.forEach(f-> f.generate(script, path));
	}

}
