package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IAdjustmentsFactory;
import br.com.torres.ddlGenerator.services.IAdjustments;
import br.com.torres.ddlGenerator.services.adjustments.*;

public class AdjustmentsFactory implements IAdjustmentsFactory {

	@Override
	public List<IAdjustments> get() {
		// TODO Auto-generated method stub
		List<IAdjustments> list = new ArrayList<>();
		list.add(new AdjustingTypeOfPK());
		list.add(new AdjustingTypeOfColumns());
		return list;
	}

}
