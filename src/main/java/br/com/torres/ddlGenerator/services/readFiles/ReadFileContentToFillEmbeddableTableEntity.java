package br.com.torres.ddlGenerator.services.readFiles;

import java.util.List;
import java.util.Optional;

import br.com.torres.ddlGenerator.entities.Column;
import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.services.IMapperColumn;
import br.com.torres.ddlGenerator.services.IMapperEmbeddable;
import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;

public class ReadFileContentToFillEmbeddableTableEntity implements IReadFileToFillTableEntity {

	private final IMapperFactory mapperFactory;

	public ReadFileContentToFillEmbeddableTableEntity(final IMapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

	public void read(List<String> blocks, Table table) {
		blocks.forEach(b -> {
			mapLineToEmbeddable(Optional.of(b), table);
		});

	}

	public void mapLineToEmbeddable(Optional<String> block, Table table) {
		table.getColumns().add(new Column());
		mapperFactory.get().stream().filter(f -> f instanceof IMapperEmbeddable).forEach(m -> {
			m.map(block.orElse(""), table);
		});
	}
}
