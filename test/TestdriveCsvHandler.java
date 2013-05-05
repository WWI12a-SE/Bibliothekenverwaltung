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
		CsvHandler oCsv = new CsvHandler("stock");		
		oCsv.viewMap();
		
		CsvHandler oCsv2 = new CsvHandler("users");		
		oCsv2.viewMap();
	}

}
