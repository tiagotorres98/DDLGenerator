package br.com.torres.ddlGenerator.services.adjustments;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;

public class RemoveInvalidsColumns implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		List<Column> invalids = new ArrayList<Column>();
		tables.forEach(t -> {
			
			t.getColumns().stream().forEach(c -> {
				if(Objects.isNull(c.getType()) || c.getType().contains("List<")) {
					invalids.add(c);
				}
			});
			
			t.getColumns().removeAll(invalids);
			invalids.clear();
		});
		
	}

}
