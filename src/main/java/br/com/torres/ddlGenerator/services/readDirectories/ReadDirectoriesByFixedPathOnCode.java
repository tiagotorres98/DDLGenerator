package br.com.torres.ddlGenerator.services.readDirectories;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.torres.ddlGenerator.services.IReadDirectories;

public class ReadDirectoriesByFixedPathOnCode implements IReadDirectories {

	public File[] read() {
		
		final List<File> directories = new ArrayList<File>();
		final String path1 = "D:\\000_PROJETO_DEV\\MANAGER\\FRAMEWORKS\\1.0.5 -SNAPSHOT\\FM-FRAMEWORK\\src\\main\\java\\br\\com\\fromtis\\infra\\domain\\";
		final String path2 = "D:\\000_PROJETO_DEV\\MANAGER\\MASTER\\FM-VALIDACAOFUNDOS\\fromtis-manager-validacao-fundos\\src\\main\\java\\br\\com\\fromtis\\manager\\validacao\\fundos\\domain\\";
		final String path3 = "D:\\000_PROJETO_DEV\\MANAGER\\MASTER\\FM-VALIDACAOFUNDOS\\fromtis-manager-validacao-fundos\\src\\main\\java\\br\\com\\fromtis\\manager\\validacao\\fundos\\domain\\importacao\\";
		final String path4 = "D:\\000_PROJETO_DEV\\MANAGER\\FRAMEWORKS\\1.0.5 -SNAPSHOT\\FM-FRAMEWORK-SEC\\fromtis-security-def\\src\\main\\java\\br\\com\\fromtis\\infra\\security\\domain\\";
		final String path5 = "D:\\000_PROJETO_DEV\\MANAGER\\MASTER\\FM-PRECIFICACAO\\fromtis-manager-precificacao\\src\\main\\java\\br\\com\\fromtis\\manager\\precificacao\\domain\\";
		final String path6 = "D:\\000_PROJETO_DEV\\MANAGER\\MASTER\\FM-GLOBAL\\fromtis-manager-global\\src\\main\\java\\br\\com\\fromtis\\manager\\global\\domain\\";
		
		directories.addAll(Arrays.asList(new File(path1).listFiles()));
		directories.addAll(Arrays.asList(new File(path2).listFiles()));
		directories.addAll(Arrays.asList(new File(path3).listFiles()));
		directories.addAll(Arrays.asList(new File(path4).listFiles()));
		directories.addAll(Arrays.asList(new File(path5).listFiles()));
		directories.addAll(Arrays.asList(new File(path6).listFiles()));
		
		for(int i = 0;i<directories.size();i++) {
			if(!directories.get(i).isFile()) {
				directories.remove(i);
			}
		}
		
		File[] result = new File[directories.size()];

		directories.toArray(result);
		
		return result;
	}
}
