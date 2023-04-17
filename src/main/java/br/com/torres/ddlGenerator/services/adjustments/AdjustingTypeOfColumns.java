package br.com.torres.ddlGenerator.services.adjustments;

import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.utils.JavaToSqlTypes;

public class AdjustingTypeOfColumns implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		tablesStream(tables).forEach(t -> {
			columnsStream(t.getColumns()).forEach(c->{
				setColumsTypeByClassType(tables,c,t);
				setColumnsToSqlType(c,t);
			});
		});
	}
	
	public void setColumnsToSqlType(Column c,Table t) {
		if(JavaToSqlTypes.canConvertToSqlType(c.getType())) {
			c.setType(JavaToSqlTypes.convert(c.getType()));
		}
		
	}
	
	public void setColumsTypeByClassType(List<Table> tables,Column c, Table t) {
		tablesStream(tables).forEach(tt-> {
			if(tt.getClassName().toLowerCase().equals(c.getType().toLowerCase())){
				c.setType(t.getPrimaryKeyType());
			}
		});
	}

	public Stream<Column> columnsStream(List<Column> columns) {
		return columns.stream().filter(f -> f.getName() != null);
	}

	public Stream<Table> tablesStream(List<Table> tables) {
		return tables.stream().filter(f -> f.getName() != null);
	}
}
