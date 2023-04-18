package br.com.torres.ddlGenerator.services;

import java.util.List;

import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.enums.ScriptTypes;

public interface IGenerateScript {

	public List<Script> generate(List<Table> tabl);
	
	public Boolean executable(ScriptTypes type);
	
}
