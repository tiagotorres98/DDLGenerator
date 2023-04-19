package br.com.torres.ddlGenerator.services.adjustments;

import java.util.List;
import java.util.Objects;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;

public class AdjustingVarcharType implements IAdjustments {

	private final String VARCHAR = "VARCHAR(%s)";
	private final String DEFAULT_LENGTH = "100";
	
	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		tables.stream()
		.filter(f-> f.getEmbeddableTable().equals(Boolean.FALSE))
		.forEach(t->{
			if(t.getPrimaryKeyType().equals("varchar")) {
				t.setPrimaryKeyType(String.format(VARCHAR, DEFAULT_LENGTH));
			}
			
			t.getColumns().forEach(c->{
				if(c.getType().equals("varchar")) {
					c.setType(String.format(VARCHAR, Objects.isNull(c.getLength())?"100":c.getLength()));
				}
			});
		});
	}

}
