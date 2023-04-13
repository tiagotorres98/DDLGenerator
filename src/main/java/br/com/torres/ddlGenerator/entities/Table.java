package br.com.torres.ddlGenerator.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Table {

	private String name;
	private String className;
	private String primaryKey;
	private String primaryKeyType;
	private List<Column> columns;

	public Column getLastColumn(){
		if (Objects.isNull(columns)) {
			setColumns(new ArrayList<Column>());
			getColumns().add(new Column());
		}
		int count = getColumns().size();
		return getColumns().get(count - 1);
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ", className=" + className + ", primaryKey=" + primaryKey + ", primaryKeyType="
				+ primaryKeyType + ", columns=" + columns + "]";
	}

}
