package br.com.torres.ddlGenerator.factory;

import java.util.List;

import br.com.torres.ddlGenerator.services.IAdjustments;

public interface IAdjustmentsFactory {

	public List<IAdjustments> get();
	
}
