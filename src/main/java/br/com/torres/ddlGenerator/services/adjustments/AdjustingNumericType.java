package br.com.torres.ddlGenerator.services.adjustments;

import java.util.List;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;

public class AdjustingNumericType implements IAdjustments {

	private final String NUMERIC = "NUMERIC(%s,%s)";
	private final String DEFAULT_PRECISION = "25";
	private final String DEFAULT_SCALE = "8";
	private final String DEFAULT_SCALE_TO_PK = "0";
	
	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		tables.stream()
		.filter(f-> f.getEmbeddableTable().equals(Boolean.FALSE)).forEach(t->{
			if(t.getPrimaryKeyType().equals("numeric")) {
				t.setPrimaryKeyType(String.format(NUMERIC, DEFAULT_PRECISION,DEFAULT_SCALE_TO_PK));
			}
			
			t.getColumns().forEach(c->{
				if(c.getType().equals("numeric")) {
					c.setType(String.format(NUMERIC,DEFAULT_PRECISION,DEFAULT_SCALE));
				}
			});
		});
	}

}
