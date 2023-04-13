package br.com.torres.ddlGenerator.services.readDirectories;

import java.io.File;
import java.util.Scanner;

import br.com.torres.ddlGenerator.services.IReadDirectories;

public class ReadDirectoriesByScanner implements IReadDirectories {

	public File[] read() {
		Scanner scanner = new Scanner(System.in);	
		System.out.print("Digite o endere�o de pasta das entidades da aplica��o: ");
		File folder = new File(scanner.next());
		if(!folder.isDirectory()) {
			System.out.println("O Endere�o digitado n�o � v�lido. Tente novamente...\n");
			read();
		}
		return folder.listFiles();
	}
}
