package br.com.torres.ddlGenerator.services.readFiles;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IMapperFactory;
import br.com.torres.ddlGenerator.services.IMapper;
import br.com.torres.ddlGenerator.services.IMapperTable;
import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;
import br.com.torres.ddlGenerator.services.readFiles.regexReader.MapTableName;

public class ReadFileContentToFillTableEntity implements IReadFileToFillTableEntity {

	private final IMapperFactory mapperFactory;

	public ReadFileContentToFillTableEntity(final IMapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

	public void read(List<String> blocks, Table table) {
		final Optional<String> block = blocks.stream().findFirst();
		Scanner scan = new Scanner(block.orElse(""));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			
			mapperFactory.get().stream()
			.filter(f -> f instanceof IMapperTable)
			.forEach(m -> m.map(line, table));

		}
	}

}
