package br.com.torres.ddlGenerator.factory;

import java.util.List;

import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;

public interface IReadFileToFillTableEntityFactory {

	public List<IReadFileToFillTableEntity> get();
	
}
