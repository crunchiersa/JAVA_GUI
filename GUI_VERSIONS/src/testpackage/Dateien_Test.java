
package testpackage;

import java.io.IOException;
import java.util.ArrayList;
import dateien.Datei;
import dateien.HCMDatei;

@SuppressWarnings("unused")
public class Dateien_Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> liste = new ArrayList<String>();
		
		liste.add(0, "null");
		liste.add(1, "eins");
		
		HCMDatei datei = new HCMDatei();
		
		datei.setInhaltSchreiben(liste);
		datei.pub_pruefSchreib();
	}

}
