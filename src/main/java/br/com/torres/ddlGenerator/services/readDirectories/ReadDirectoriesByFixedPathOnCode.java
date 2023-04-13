package br.com.torres.ddlGenerator.services.readDirectories;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.torres.ddlGenerator.services.IReadDirectories;

public class ReadDirectoriesByFixedPathOnCode implements IReadDirectories {

	public File[] read() {
		
		final List<File> directories = new ArrayList<File>();
		final String path1 = "D:\\000_PROJETO_DEV\\MANAGER\\MASTER\\FM-GLOBAL\\fromtis-manager-global\\src\\main\\java\\br\\com\\fromtis\\manager\\global\\domain";
		final String path2 = "D:\\000_PROJETO_DEV\\BACEN3040\\11_12_27114_depara_tipo_recebivel\\3040-domain\\src\\main\\java\\br\\com\\fromtis\\bacen3040\\domain";
		final String path3 = "D:\\000_PROJETO_DEV\\CVM489\\develop\\informeMensal-domain\\src\\main\\java\\br\\com\\fromtis\\cvm489\\domain";
		
		directories.addAll(Arrays.asList(new File(path1).listFiles()));
		directories.addAll(Arrays.asList(new File(path2).listFiles()));
		directories.addAll(Arrays.asList(new File(path3).listFiles()));
		
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
