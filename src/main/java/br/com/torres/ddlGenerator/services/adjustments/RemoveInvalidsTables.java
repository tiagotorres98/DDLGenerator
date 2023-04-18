package br.com.torres.ddlGenerator.services.adjustments;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;

public class RemoveInvalidsTables implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		List<Table> invalids = new ArrayList<Table>();
		tables.forEach(t -> {
			if(Objects.isNull(t.getName())) {
				invalids.add(t);
			}
		});
		
		tables.removeAll(invalids);
		invalids.clear();
	}

}
