/**
 * Main-Klasse, um CSV-Handler zu testen
 * @author ja
 *
 */

package test;
import core.*;

public class TestdriveCsvHandler {

	public static void main(String[] args) {
		CsvHandler oCsv = new CsvHandler("users");
		System.out.println(oCsv.read());
	}

}
