package kranchie.java.karten;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import kranchie.java.customExceptions.CustomUnchecked;

public class Kreditkarte_AT extends Karte {

	// Constructors
	public Kreditkarte_AT(String ccnr, String controlnr, int gueltigmonat, int gueltigjahr) {
		super(ccnr, controlnr, "Kreditkarte", gueltigmonat, gueltigjahr);
		super.setkartennrlaenge(16);
	}

	public Kreditkarte_AT() {
		super();
		super.setKartenArt("Kreditkarte");
		super.setkartennrlaenge(16);
	}

	// Public Methoden
	/**
	 * Description: Prüfen ob Kreditkartennummer gültig ist. Die Methode errechnet
	 * die Gültigkeit anhand folgendem Vorgehen: 1. Es wird die Summe aus den Werten
	 * geraden Stellen der Kreditkartennummer (2,4,6,8,10,12,14,16) errechnet. 2.
	 * Die Werte der ungeraden Stellen der Kreditkartennummer (1,3,5,7,9,11,13,15)
	 * werden mit 2 multipliziert, falls das Ergebnis >= 10 ist wird der Wert 9
	 * abgezogen. Die Zahlen werden summiert. 3. Es wird die Summe der beiden
	 * Teilsummen gebildet und Modulo 10 gerechnet. 4. Bei einem Ergebnis != 0 ist
	 * die Kreditkartennummer ungültig.
	 * 
	 * @author crunchie
	 * @return Boolean. True = Kreditkartennummer ist gültig. False =
	 *         Kreditkartennummer ist nicht gültig.
	 */
	public boolean checkCCNR() {
		boolean gueltigkeit;
		int repeat = 0;
		int summe_even = 0;
		int summe_odd = 0;
		int summe_all = 0;
		boolean nrinordnung = this.checkLaenge(this.getkartennrlaenge());
		try {
			if (nrinordnung == true) {
				for (int i = 0; i < 16; i++) {
					int wert = this.setwertcc(this.getKartenNummer().charAt(i));
					if (0 != i % 2 && i != 0) {
						summe_even = summe_even + wert;
					} else {
						if (wert * 2 >= 10) {
							wert = wert * 2 - 9;
						} else {
							wert *= 2;
						}
						summe_odd = summe_odd + wert;
					}
					summe_all = summe_even + summe_odd;
					repeat = repeat + 1;
				}
				summe_all %= 10;
				if (summe_all == 0) {
					gueltigkeit = true;
				} else {
					gueltigkeit = false;
				}
			} else {
				gueltigkeit = false;
			}
		} catch (CustomUnchecked e) {
			JOptionPane.showMessageDialog(null, e.getFehler());
			gueltigkeit = false;
		}
		return gueltigkeit;
	}

	public ArrayList<String> createCCNR() {
		ArrayList<String> ccnr = new ArrayList<String>();
		ccnr = this.getCCNR();
		return ccnr;
	}
	
	// Private Methoden

	private ArrayList<String> getCCNR() {
		ArrayList<String> ccnr = new ArrayList<String>();

		return ccnr;

	}
}
