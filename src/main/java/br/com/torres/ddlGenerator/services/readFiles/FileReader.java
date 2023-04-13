package br.com.torres.ddlGenerator.services.readFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.torres.ddlGenerator.entities.Table;
import br.com.torres.ddlGenerator.factory.IReadFileToFillTableEntityFactory;
import br.com.torres.ddlGenerator.factory.implement.ReadFileToFillTableEntityFactory;
import br.com.torres.ddlGenerator.services.IFileReader;
import br.com.torres.ddlGenerator.services.IReadFileToFillTableEntity;

public class FileReader implements IFileReader {

	private final IReadFileToFillTableEntityFactory readFileFactory = new ReadFileToFillTableEntityFactory();
	
	@Override
	public Table readFile(File file) {
		Table table = new Table();
		table.setColumns(new ArrayList<>());
		try {
			List<String> blocks = scanFileToBlockOfCode(file);
			fileToTable(blocks,table);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public void fileToTable(List<String> blocks,Table table) {
		for(IReadFileToFillTableEntity reader: readFileFactory.get()) {
			reader.read(blocks, table);
		}
		
		if(  //false &&
			 table.getName() != null 
			 //&& table.getPrimaryKey() == null
		) {System.out.println(table);}
		
	}
	
	public List<String> scanFileToBlockOfCode(File file) throws FileNotFoundException {
		boolean lineContainsPrivate = false;
		List<String> result = new ArrayList<>();
		Scanner scan = new Scanner(file);
		String block = "";
		while(scan.hasNext()) {
			String line = scan.nextLine();
			
			if(line.trim().startsWith("private")) {
				block += line + "\n";
				lineContainsPrivate = !lineContainsPrivate;
			}
			else {
				block += line + "\n";
			}
			
			if(lineContainsPrivate) {
				result.add(block);
				block = "";
				lineContainsPrivate = !lineContainsPrivate;
			}
			
		}
		
		return result;
	}
	
}
