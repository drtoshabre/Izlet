package util;

import java.util.Scanner;

public class ScannerUtility {

	public static void inputUserData(String value, Scanner sc, String promptMessage) {
		try {
			System.out.println(promptMessage);
			value = sc.nextLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void chooseOption(int marker, Scanner sc, String promptMessage) {
		try {
			System.out.println(promptMessage);
			marker = sc.nextInt();
			sc.nextLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
