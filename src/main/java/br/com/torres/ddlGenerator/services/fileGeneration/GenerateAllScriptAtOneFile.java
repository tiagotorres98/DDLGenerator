package br.com.torres.ddlGenerator.services.fileGeneration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;
import br.com.torres.ddlGenerator.services.IFileGenerator;

public class GenerateAllScriptAtOneFile implements IFileGenerator {

	private final String FILE_NAME = "DDL.sql";
	private final String LOG_BEGIN_CREATE_OBJECT = "print('INICIO: CRIANDO OBJETO %s.') \n";
	private final String LOG_END_CREATE_OBJECT = "print('FIM: OBJETO %s CRIADO.') \nGO\n";

	@Override
	public void generate(List<Script> scripts, String pathExport) {
		// TODO Auto-generated method stub
		try {
		      FileWriter myWriter = new FileWriter(pathExport.concat(FILE_NAME));
			  myWriter.write("BEGIN TRAN \n");
		      writeFile(myWriter,scripts);
		      myWriter.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public void writeFile(FileWriter myWriter,List<Script> scripts) throws IOException {
		for(Script script: scripts) {
			if(!Objects.isNull(script.getScript())) {
				myWriter.write(String.format(LOG_BEGIN_CREATE_OBJECT, script.getName()));
				myWriter.write(script.getScript());
				myWriter.write(String.format(LOG_END_CREATE_OBJECT, script.getName()));
			}
		}
	}

	@Override
	public Boolean executable(ScriptFileFormateType type) {
		// TODO Auto-generated method stub
		return type.equals(ScriptFileFormateType.ALL_SCRIPTS_AT_ONE_FILE);
	}

}
