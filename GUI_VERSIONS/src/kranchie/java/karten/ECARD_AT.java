package kranchie.java.karten;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public class ECARD_AT extends Karte {

	// Variablen
	private ArrayList<Integer> multiplikator = new ArrayList<Integer>(Arrays.asList(3, 7, 9, 5, 8, 4, 2, 1, 6));

	// Constructors
	public ECARD_AT(String svnr) {
		super(svnr, 10);
		super.setKartenArt("Sozialversicherung");
	}

	public ECARD_AT() {
		super();
		super.setKartenArt("Sozialversicherung");
		super.setkartennrlaenge(10);
	}

	// Public Methoden
	/**
	 * Description: Die Methode errechnet die Gültigkeit anhand folgendem Vorgehen:
	 * 1. Die Werte der Stellen der Sozialversicherungsnummer, ausgenommen Stelle 4)
	 * werden mit Konstanten multipliziert. Die Zahlen werden summiert. 2. Die Summe
	 * wird Modulo 11 gerechnet. 3. Der Restwert wird mit der vierten Stelle der
	 * Sozialversicherung verglichen. Falls die Werte über- einstimmen, ist die
	 * Sozialversicherungsnummer gültig.
	 * 
	 * @author crunchie
	 * @return Boolean. True = Die Sozialversicherungsnummer ist gültig. False = Die
	 *         Sozialversicherungsnummer ist nicht gültig.
	 */
	public boolean checkSVNR() {
		boolean gueltigkeit;
		int repeat = 0;
		int summe = 0;
		int xe = 0;
		boolean nrinordnung = this.checkLaenge(this.getkartennrlaenge());

		if (nrinordnung == true) {
			try {
				for (int i = 0; i < 10; i++) {
					int wert = super.setwertcc(this.getKartenNummer().charAt(i));
					if (i < 3) {
						wert *= this.multiplikator.get(i);
						summe += wert;
					} else if (i > 3) {
						wert *= this.multiplikator.get(i - 1);
						summe += wert;
					} else if (i == 3) {
						xe = wert;
					}
					repeat = repeat + 1;
				}
				summe %= 11;
				if (xe == summe) {
					gueltigkeit = true;
				} else {
					gueltigkeit = false;
				}
			} catch (CustomUnchecked e) {
				JOptionPane.showMessageDialog(null, e.getFehler());
				gueltigkeit = false;
			}
		} else {
			gueltigkeit = false;
		}
		return gueltigkeit;
	}

	/**
	 * Description: Die Methode liefert eine bestimmte Anzahl von SVNR in einer
	 * Range zwischen 1111 und 9999 für das gelieferte Geburtsdatum.
	 * 
	 * @author crunchie
	 * @return ArrayList<String>: Beinhaltet die Werte der SVNR.
	 */
	public ArrayList<String> getSVNR(String geb, int anzahl) {
		ArrayList<String> svnr = new ArrayList<String>();
		svnr = this.calcSVNR(geb, 1111, 9999, anzahl, false);
		return svnr;
	}

	/**
	 * Description: Die Methode liefert eine Anzahl von SVNR für das Geburtsdatum in
	 * einer Range (zwischen initial und hoechstwert, wenn range = false) oder alle
	 * SVNR für das Geburtsdatum in der Range (zwischen initial und hoechstwert,
	 * wenn range = true).
	 * 
	 * @author crunchie
	 * @return ArrayList<String>: Beinhaltet die Werte der SVNR.
	 */
	public ArrayList<String> getSVNR(String geb, int initial, int hoechstwert, int anzahl, boolean range) {
		ArrayList<String> svnr = new ArrayList<String>();
		svnr = this.calcSVNR(geb, initial, hoechstwert, anzahl, range);
		return svnr;
	}

	// Private Methoden
	private ArrayList<String> calcSVNR(String gbdat, int initial, int hoechstwert, int anzahl, boolean range) {
		this.setKartenNummer(Integer.toString(initial) + gbdat);
		ArrayList<String> nummern = new ArrayList<String>();
		for (int i = initial; i <= hoechstwert; i++) {
			if (this.checkSVNR()) {
				nummern.add(this.getKartenNummer());
				this.setKartenNummer(++i + gbdat);
				if (!range && nummern.size() == anzahl) {
					break;
				}
			} else {
				this.setKartenNummer(++i + gbdat);
			}
		}
		return nummern;
	}
}
