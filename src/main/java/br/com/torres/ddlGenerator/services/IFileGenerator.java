package br.com.torres.ddlGenerator.services;

import java.util.List;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.enums.ScriptFileFormateType;

public interface IFileGenerator {

	public void generate(List<Script> scripts, String pathExport);

	public Boolean executable(ScriptFileFormateType type);
}
