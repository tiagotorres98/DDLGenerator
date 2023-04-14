package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.services.IMapper;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.*;

public class MapperFactory implements IMapperFactory {

	@Override
	public List<IMapper> get() {
		List<IMapper> list = new ArrayList<>();
		list.add(new MapTableName());
		list.add(new MapClassName());
		list.add(new MapTablePK());
		list.add(new MapTablePKTypeByAttributeOverrideColumnDefinition());
		list.add(new MapTablePKTypeByFromtisAbstractAuditable());
		list.add(new MapTablePKTypeByAbstractPersistable());
		list.add(new MapTablePKByIdAnnotation());
		list.add(new MapTablePKTypeByIdAnnotation());
		list.add(new MapColumnName());
		list.add(new MapColumnNameByJoinColumnAnnotation());
		list.add(new MapColumnType());
		list.add(new MapColumnIsNullable());
		list.add(new MapColumnLength());
		list.add(new MapColumnIsUnique());
		list.add(new MapColumnTypeByJoinColumn());
		return list;
	}

}
