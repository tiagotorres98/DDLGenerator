package br.com.torres.ddlGenerator.factory.implement;

import java.util.ArrayList;
import java.util.List;

import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.services.IMapper;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapClassName;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnIsNullable;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnIsUnique;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnLength;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnName;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnNameByJoinColumnAnnotation;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnType;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnTypeByEnumeratedTag;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapColumnTypeByJoinColumn;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapEmbeddable;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTableName;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePK;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKByIdAnnotation;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKTypeByAbstractAuditableDefault;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKTypeByAbstractPersistable;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKTypeByAttributeOverrideColumnDefinition;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKTypeByFromtisAbstractAuditable;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTablePKTypeByIdAnnotation;

public class MapperFactory implements IMapperFactory {
	
	public List<IMapper> get(){
		List<IMapper> list = new ArrayList<>();
		list.add(new MapColumnName());
		list.add(new MapColumnNameByJoinColumnAnnotation());
		list.add(new MapTableName());
		list.add(new MapClassName());
		list.add(new MapTablePK());
		list.add(new MapTablePKTypeByAttributeOverrideColumnDefinition());
		list.add(new MapTablePKTypeByFromtisAbstractAuditable());
		list.add(new MapTablePKTypeByAbstractAuditableDefault());
		list.add(new MapTablePKTypeByAbstractPersistable());
		list.add(new MapTablePKByIdAnnotation());
		list.add(new MapTablePKTypeByIdAnnotation());
		list.add(new MapColumnType());
		list.add(new MapColumnIsNullable());
		list.add(new MapColumnLength());
		list.add(new MapColumnIsUnique());
		list.add(new MapColumnTypeByJoinColumn());
		list.add(new MapColumnTypeByEnumeratedTag());
		list.add(new MapEmbeddable());
		return list;
	}

}
