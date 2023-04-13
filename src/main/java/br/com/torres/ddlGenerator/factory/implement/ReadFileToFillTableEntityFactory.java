package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.factory.IReadFileToFillTableEntityFactory;
import br.com.torres.ddlGenerator.services.IMapper;
import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;
import br.com.torres.ddlGenerator.services.readFiles.ReadFileContentToFillTableColumnEntity;
import br.com.torres.ddlGenerator.services.readFiles.ReadFileContentToFillTableEntity;

public class ReadFileToFillTableEntityFactory implements IReadFileToFillTableEntityFactory {

	private final IMapperFactory mapperFactory = new MapperFactory();
	
	@Override
	public List<IReadFileToFillTableEntity> get() {
		List<IReadFileToFillTableEntity> list = new ArrayList<>();
		list.add(new ReadFileContentToFillTableEntity(mapperFactory));
		list.add(new ReadFileContentToFillTableColumnEntity(mapperFactory));
		return list;
	}

}
