/**
 * Main-Klasse, um CSV-Handler zu testen
 * @author ja
 *
 */

import core.*;

public class TestdriveCsvHandler {

	public static void main(String[] args) {
		CsvHandler oCsv = new CsvHandler("users");
		oCsv.getCsv();
	}

}
