package br.com.torres.ddlGenerator.services.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.torres.ddlGenerator.db.connection.DataSource;
import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.enums.Queries;
import br.com.torres.ddlGenerator.services.ICreateScriptTables;

public class CreateScriptsTables implements ICreateScriptTables {

	private final Connection con = DataSource.get();
	
	@Override
	public List<Script> create() {
		return getScripts();
	}
	
	public List<Script> getScripts(){
		String table = "";
		Script aux = null;
		List<Script> scripts = new ArrayList<Script>();
		for(Queries q: Queries.values()) {
			scripts.addAll(execSQLScripts(q));
		}
		
		Collections.sort(scripts, new Comparator<Script>() {
			  @Override
			  public int compare(Script u1, Script u2) {
			    return u1.getName().compareTo(u2.getName());
			  }
			});

		
		List<Script> result = new ArrayList<Script>();
		for(Script s:scripts) {
			if(s.getScript().contains("CREATE TABLE")) {
				aux = new Script(s.getName(), s.getScript());
				result.add(aux);
			}
			else {
				Script script = result.get(result.size()-1);
				script.setScript(script.getScript().concat(s.getScript()));
			}
		}	
		
		return result;
	}
	
	public List<Script> execSQLScripts(Queries q) {
		List<Script> scripts = new ArrayList<Script>();
		
		try {
			ResultSet rs = con.prepareStatement(q.getQuery()).executeQuery();
			while(rs.next()) {
				String table = rs.getString(1);
				String script = rs.getString(2);
				scripts.add(new Script(table,script));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

		return scripts;	
	}
	
}
