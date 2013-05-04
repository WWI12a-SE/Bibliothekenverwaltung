package test;
/**
 * Main-Klasse, um CSV-Handler zu testen
 * @author ja
 *
 */

import core.*;

public class TestdriveCsvHandler {

	public static void main(String[] args) {
		System.out.println("Los...");
		CsvHandler oCsv = new CsvHandler("users");
		System.out.println(oCsv.read());
	}

}
