package br.com.torres.ddlGenerator.services.adjustments;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.utils.JavaToSqlTypes;

public class AdjustingTypeOfColumns implements IAdjustments {

	@Override
	public void adjusting(List<Table> tables) {
		// TODO Auto-generated method stub
		List<Column> notHaveType = new ArrayList<>();
		for(Table table:tables) {
			for(Column column:table.getColumns()) {
				
			}
		}
	}
}
