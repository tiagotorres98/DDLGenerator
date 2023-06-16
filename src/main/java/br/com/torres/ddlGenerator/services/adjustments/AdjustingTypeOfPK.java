package br.com.torres.ddlGenerator.services.adjustments;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.utils.JavaToSqlTypes;

public class AdjustingTypeOfPK implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		List<Table> notHaveType = new ArrayList<>();
		
		tables.stream()
		.filter(f-> f.getName() != null)
		.forEach(t->{	
			String type = t.getPrimaryKeyType().toLowerCase();
			if(!JavaToSqlTypes.canConvertToSqlType(type)) {
				notHaveType.add(t);
			}
			t.setPrimaryKeyType(JavaToSqlTypes.convert(type));
		});	
		
		notHaveType.stream().forEach(n ->{
			tables.stream()
			.filter(f-> f.getName() != null)
			.forEach(t-> {
				if(t.getClassName().toLowerCase().equals(n.getPrimaryKeyType().toLowerCase())) {
					n.setPrimaryKeyType(n.getPrimaryKey());
				}else {
					n.setPrimaryKeyType(JavaToSqlTypes.convert("long"));
				}
			});
		});
		
		tables.stream()
		.filter(f-> f.getPrimaryKey() == null)
		.forEach(t->{	
			t.setPrimaryKey("ID");
		});	
	}
}
