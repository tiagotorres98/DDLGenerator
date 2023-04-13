package br.com.torres.ddlGenerator.factory;

import java.util.List;

import br.com.torres.ddlGenerator.services.IMapper;

public interface IMapperFactory {

	public List<IMapper> get();
	
}
