package br.com.torres.ddlGenerator.services.readFiles;

import java.util.List;
import java.util.Optional;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.IMapperColumnByColumnAnnotation;
import br.com.torres.ddlGenerator.services.IMapperColumnByJoinColumnAnnotation;
import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;

public class ReadFileContentToFillTableColumnEntity implements IReadFileToFillTableEntity {

	private final IMapperFactory mapperFactory;

	public ReadFileContentToFillTableColumnEntity(final IMapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

	public void read(List<String> blocks, Table table) {
		blocks.forEach(b -> {
			mapLineToColumn(Optional.of(b), table);
		});

		blocks.forEach(b -> {
			mapLineToJoinColumn(Optional.of(b), table);
		});
	}
	
	public void mapLineToJoinColumn(Optional<String> block, Table table) {
		mapperFactory.get().stream().filter(f -> f instanceof IMapperColumnByJoinColumnAnnotation).forEach(mc -> {
			table.getColumns().add(new Column());
			mc.map(block.orElse(""), table);
			
			mapperFactory.get().stream().filter(f -> f instanceof IMapperColumn).forEach(m -> {
				m.map(block.orElse(""), table);
			});
		});
	}

	public void mapLineToColumn(Optional<String> block, Table table) {
		
		mapperFactory.get().stream().filter(f -> f instanceof IMapperColumnByColumnAnnotation).forEach(mc -> {
			table.getColumns().add(new Column());
			mc.map(block.orElse(""), table);
			
			mapperFactory.get().stream().filter(f -> f instanceof IMapperColumn).forEach(m -> {
				m.map(block.orElse(""), table);
			});
		});		
	}
}
