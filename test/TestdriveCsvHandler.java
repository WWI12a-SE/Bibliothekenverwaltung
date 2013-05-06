/**
 * Main-Klasse, um CSV-Handler zu testen
 * @author ja
 *
 */

package test;

import java.io.IOException;

import core.*;

public class TestdriveCsvHandler {

	public static void main(String[] args) throws IOException{
		
		/*
		System.out.println("Bestand:\n");
		
		CsvHandler oCsv = new CsvHandler("stock");		
		oCsv.viewMap();
		*/
		System.out.println("Benutzer:\n");
		
		CsvHandler oCsv2 = new CsvHandler("users");		
		//oCsv2.viewMap();
		
		System.out.println("Benutzer mit ID ausgeben:\n");
		oCsv2.viewLineById("schnatti");
	}

}
