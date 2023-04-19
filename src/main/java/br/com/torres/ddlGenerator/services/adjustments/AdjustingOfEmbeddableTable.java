package br.com.torres.ddlGenerator.services.adjustments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.utils.JavaToSqlTypes;

public class AdjustingOfEmbeddableTable implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		
		List<Table> notHaveType = new ArrayList<>();
		
		tables.stream()
		.filter(f-> f.getName() == null)
		.forEach(t->{	
			tablesWithEmbeddable(tables).forEach(et->{
				if(et.getPrimaryKeyType().equals(t.getClassName())) {
					String compositeKey = "";
					et.getColumns().addAll(t.getColumns());
					Collections.reverse(et.getColumns());
					
					Iterator<Column> columns = t.getColumns().iterator();
					while(columns.hasNext()) {
						compositeKey += columns.next().getName();
						if(columns.hasNext()) compositeKey += ",";
					}
					et.setPrimaryKey(compositeKey);
					et.setEmbeddableTable(Boolean.TRUE);
					notHaveType.add(t);
				}
			});
		});	
		
		tables.removeAll(notHaveType);
	}
	
	public Stream<Table> tablesWithEmbeddable(List<Table> tables) {
		return tables
		.stream()
		.filter(f-> f.getName() != null)
		.filter(f-> !JavaToSqlTypes.canConvertToSqlType(f.getPrimaryKeyType()));
	}
}
