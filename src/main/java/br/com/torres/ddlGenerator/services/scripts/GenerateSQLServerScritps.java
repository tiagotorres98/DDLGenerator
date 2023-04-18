package br.com.torres.ddlGenerator.services.scripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Script;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.enums.ScriptTypes;
import br.com.torres.ddlGenerator.services.IGenerateScript;
import br.com.torres.ddlGenerator.utils.JavaToSqlTypes;

public class GenerateSQLServerScritps implements IGenerateScript {

	private final String VERIFY_TABLE_EXISTS = "IF NOT EXISTS (SELECT TOP 1 1 FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[%s]') AND type in (N'U')) \nBEGIN \n %s \nEND \n\n"; //TABLE_NAME | TABLE_CREATION
	private final String CREATE_TABLE = "CREATE TABLE %s (%s)"; //TABLE_NAME | PK_CREATION
	private final String CREATE_PK = "[%s] %s %s NOT NULL"; // FIELD | TYPE | IDENTITY(1,1)
	private final String CREATE_COLUMN = "ALTER TABLE %s ADD [%s] %s %s"; // FIELD | TYPE | NULLABLE 
	private final String VERIFY_COLUMN_EXISTS = "IF NOT EXISTS(SELECT TOP 1 1 FROM SYS.TABLES T JOIN SYS.COLUMNS C ON C.object_id = T.object_id WHERE T.name = '%s' AND C.name = '%s') \nBEGIN \n %s \nEND \n\n"; // TABLE_NAME | COLUMN_NAME | COLUMN_CREATION
	private final String VERIFY_UNIQUE_CONSTRAINT = "IF NOT EXISTS (SELECT TOP 1 1 FROM  SYS.OBJECTS O WHERE OBJECT_NAME(O.PARENT_OBJECT_ID) = '%s') \nBEGIN \n %s \nEND \n\n"; //TABLE_NAME | CREATE_CONSTRAINT
	private final String UNIQUE_FIELDS = "ALTER TABLE %s ADD CONSTRAINT %s UNIQUE (%s)"; //TABLE_NAME | CONSTRAINT_NAME | UNIQUE_COLUMNS
	
	@Override
	public List<Script> generate(List<Table> table) {
		// TODO Auto-generated method stub
		List<Script> list = new ArrayList<Script>();
		table.stream().forEach(t -> {
			list.add(new Script(t.getName(),scriptFormat(t)));
		});
		return list;
	}

	public String scriptFormat(Table table) {
		String result = "";
		result += createTableScript(table);
		result += createColumnsScript(table);
		result += createUniqueConstraint(table);
		return result;
	}
	
	public String createTableScript(Table table) {
		return stringFormatVerifyIfTableExists(table);
	}

	public String createColumnsScript(Table table) {
		String result = 
		table.getColumns().stream()
		.map(c->stringFormatVerifyIfColumnExists(table, c))
		.collect(Collectors.joining());		
		return result;
	}
	
	public String createUniqueConstraint(Table table) {
		String result = "";
		if(!Objects.isNull(table.getUniqueColumns())) {
			result += stringFormatVerifyIfUniqueConstraintExists(table);
		}
		return result;
	}
	
	public String stringFormatVerifyIfUniqueConstraintExists(Table table) {
		return String.format(VERIFY_UNIQUE_CONSTRAINT, table.getName(),stringFormateCreateUniqueConstraint(table));
	}
	
	public String stringFormateCreateUniqueConstraint(Table table) {
		String contraintName = "UC_".concat(table.getName());
		return String.format(UNIQUE_FIELDS, table.getName(),contraintName,table.getUniqueColumns());
	}
	
	public String stringFormatCreateTable(Table table) {
		return String.format(CREATE_TABLE, table.getName(), stringFormatCreatePK(table));
	}

	
	public String stringFormatVerifyIfTableExists(Table table) {
		return String.format(VERIFY_TABLE_EXISTS, table.getName(),stringFormatCreateTable(table));
	}

	public String stringFormatUniqueConstraint(Table table) {
		return String.format(CREATE_TABLE, table.getName(), stringFormatCreatePK(table));
	}

	public String stringFormatCreatePK(Table table) {
		Boolean isIdentity = JavaToSqlTypes.isNumberTypes(table.getPrimaryKeyType());
		return String.format(CREATE_PK, table.getPrimaryKey(), table.getPrimaryKeyType(),isIdentity ? "IDENTITY(1,1)":"");
	}

	public String stringFormatVerifyIfColumnExists(Table table, Column column) {
		return String.format(VERIFY_COLUMN_EXISTS, table.getName(), column.getName(),stringFormatCreateColumn(table,column));
	}
	
	public String stringFormatCreateColumn(Table table, Column column) {
		return String.format(CREATE_COLUMN, table.getName(),column.getName(), column.getType(), column.getNullable());
	}

	@Override
	public Boolean executable(ScriptTypes type) {
		// TODO Auto-generated method stub
		return type.equals(ScriptTypes.SQLSERVER);
	}
}
