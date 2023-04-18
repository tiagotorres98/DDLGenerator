package br.com.torres.ddlGenerator.services.readFiles.regexReader.utils;

import java.util.Scanner;

public class RegexConditionsString {

	public static Boolean startsWith(String block, String text) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().startsWith(text)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;
	}

	public static Boolean contains(String block, String text) {
		String result = "";
		Scanner scan = new Scanner(block);
		try {
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (line.trim().contains(text)) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;
	}
}
