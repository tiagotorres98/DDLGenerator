package br.com.torres.ddlGenerator.services.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;
import br.com.torres.ddlGenerator.enums.ScriptTypes;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.services.IFileGenerator;
import br.com.torres.ddlGenerator.services.IFileReader;
import br.com.torres.ddlGenerator.services.IGenerateDDL;
import br.com.torres.ddlGenerator.services.IGenerateScript;
import br.com.torres.ddlGenerator.services.IReadDirectories;

public class GenerateDDL implements IGenerateDDL {

	private final IReadDirectories readDirectories;
	private final IFileReader fileReader;
	private final List<IGenerateScript> scritps;
	private final List<IAdjustments> adjustments;
	private final List<IFileGenerator> fileGenerator;
	private List<Table> tables;
	private List<Script> script;

	public GenerateDDL(final List<IFileGenerator> fileGenerator,final List<IGenerateScript> scritps, final List<IAdjustments> adjustments,
			final IReadDirectories readDirectories, final IFileReader fileReader) {
		this.readDirectories = readDirectories;
		this.fileReader = fileReader;
		this.adjustments = adjustments;
		this.scritps = scritps;
		this.fileGenerator = fileGenerator;
	}

	public void generate() {
		tables = new ArrayList<>();
		File[] files = readDirectories.read();
		readFiles(files);
		adjustments();
		createScripts(ScriptTypes.SQLSERVER);
		createFile("C:\\Users\\tiago.torres\\Desktop\\TESTE_DDL\\",ScriptFileFormateType.ALL_SCRIPTS_AT_ONE_FILE);
	}
	
	public void createFile(String path, ScriptFileFormateType type) {
		fileGenerator.stream()
		.filter(f-> f.executable(type))
		.forEach(f-> f.generate(script, path));
	}

	public void createScripts(ScriptTypes type) {
		scritps.stream()
		.filter(f-> f.executable(type))
		.forEach(s -> script = s.generate(this.tables));
	}

	public void adjustments() {
		adjustments.forEach(a -> a.adjusting(this.tables));
	}

	public void readFiles(File[] files) {
		Arrays.asList(files).forEach(f -> tables.add(fileReader.readFile(f)));
	}

}
