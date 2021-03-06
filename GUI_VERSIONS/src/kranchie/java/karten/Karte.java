package kranchie.java.karten;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public abstract class Karte {

	// Attribute
	private String kartenNummer;
	private String kontrollNummer;
	private String kartenArt;
	private int kartennrlaenge;
	private ArrayList<String> felder = new ArrayList<String>();
	private ArrayList<String> feldInhalt = new ArrayList<String>();
	private int gueltigKeitMonat;
	private int gueltigKeitJahr;
	private CustomUnchecked fehler = new CustomUnchecked("karten");

	// Constructors
	public Karte(String kartenNummer, String kontrollNummer, String kartenart, ArrayList<String> felder,
			ArrayList<String> feldInhalt, int gueltigKeitMonat, int gueltigKeitJahr, int nummerlaenge) {
		super();
		this.kartenNummer = kartenNummer;
		this.kontrollNummer = kontrollNummer;
		this.kartenArt = kartenart;
		this.felder = felder;
		this.feldInhalt = feldInhalt;
		this.gueltigKeitMonat = gueltigKeitMonat;
		this.gueltigKeitJahr = gueltigKeitJahr;
		this.setkartennrlaenge(nummerlaenge);
	}

	public Karte(String kartenNummer, String controllnr, String kartenart, int monatgueltig, int jahrgueltig) {
		super();
		this.kartenNummer = kartenNummer;
		this.kartenArt = kartenart;
		this.kontrollNummer = controllnr;
		this.gueltigKeitMonat = monatgueltig;
		this.gueltigKeitJahr = jahrgueltig;
	}

	public Karte(String kartenNummer, int nummerlaenge) {
		super();
		this.kartenNummer = kartenNummer;
		this.kartennrlaenge = nummerlaenge;
	}

	public Karte(String kartenNummer, String kartenart) {
		super();
		this.kartenNummer = kartenNummer;
		this.kartenArt = kartenart;
		this.kontrollNummer = "";
		this.gueltigKeitMonat = 00;
		this.gueltigKeitJahr = 00;
		this.setkartennrlaenge(00);
	}

	public Karte() {
		super();
		this.kartenNummer = "";
		this.kontrollNummer = "";
		this.kartenArt = "";
		this.gueltigKeitMonat = 00;
		this.gueltigKeitJahr = 00;
	}

	// Getters + Setters
	public String getKartenNummer() {
		return this.kartenNummer;
	}

	public void setKartenNummer(String kartenNummer) {
		this.kartenNummer = kartenNummer;
	}

	public String getKontrollNummer() {
		return this.kontrollNummer;
	}

	public void setKontrollNummer(String kontrollNummer) {
		this.kontrollNummer = kontrollNummer;
	}

	public String getKartenArt() {
		return this.kartenArt;
	}

	public void setKartenArt(String kartenArt) {
		this.kartenArt = kartenArt;
	}

	public ArrayList<String> getFelder() throws CustomUnchecked {
		if (this.checkFelder()) {
			return this.felder;
		} else {
			fehler.setFehler(0);
			throw fehler;
		}
	}

	public void setFelder(ArrayList<String> felder) {
		this.felder = felder;
	}

	public ArrayList<String> getFeldInhalt() throws CustomUnchecked {
		if (this.checkFeldInhalt()) {
			return this.feldInhalt;
		} else {
			fehler.setFehler(0);
			throw fehler;
		}
	}

	public void setFeldInhalt(ArrayList<String> feldInhalt) {
		this.feldInhalt = feldInhalt;
	}

	public int getGueltigKeitMonat() {
		return this.gueltigKeitMonat;
	}

	public void setGueltigKeitMonat(int gueltigKeitMonat) {
		this.gueltigKeitMonat = gueltigKeitMonat;
	}

	public int getGueltigKeitJahr() {
		return this.gueltigKeitJahr;
	}

	public void setGueltigKeitJahr(int gueltigKeitJahr) {
		this.gueltigKeitJahr = gueltigKeitJahr;
	}

	public void setkartennrlaenge(int kartennrlaenge) {
		this.kartennrlaenge = kartennrlaenge;
	}

	public int getkartennrlaenge() {
		return this.kartennrlaenge;
	}

	// Methoden

	// Pr�fen ob FeldInhalt Eintr�ge hat.
	private boolean checkFeldInhalt() {
		boolean isSet;
		int arraysize = this.feldInhalt.size();
		if (arraysize == 0) {
			isSet = false;
		} else {
			isSet = true;
		}
		return isSet;
	}

	// Pr�fen ob Felder Eintr�ge hat.
	private boolean checkFelder() {
		boolean isSet;

		int arraysize = this.felder.size();
		if (arraysize == 0) {
			isSet = false;
		} else {
			isSet = true;
		}
		return isSet;
	}

	/**
	 * Description: Pr�ft ob die L�nge der Kartennummer mit der hinterlegten
	 * Kartennummerl�nge �bereinstimmt.
	 * 
	 * @author crunchie
	 * @return Boolean, true wird zur�ckgegeben, wenn beide L�ngen �bereinstimmen,
	 *         false falls nicht.
	 */
	public boolean checklength() {
		boolean laengeok;
		int nrlaenge = this.kartenNummer.length();
		if (nrlaenge > this.kartennrlaenge) {
			laengeok = false;
		} else if (nrlaenge < this.kartennrlaenge) {
			laengeok = false;
		} else {
			laengeok = true;
		}
		return laengeok;
	}

	/**
	 * Description: Determine whether a Character is a number or not and return
	 * value of number as an Integer.
	 * 
	 * @author crunchie
	 * @return Integer, gibt den Wert numerischen Wert des Characters der mitgegeben
	 *         wurde wieder. Falls der Parameter keine Zahl ist, wird -2
	 *         zur�ckgegeben.
	 */
	public int setwertcc(char u) throws CustomUnchecked {
		int a;
		boolean isDigit = Character.isDigit(u);
		if (isDigit) {
			a = Character.getNumericValue(u);
			return a;
		} else {
			fehler.setFehler(1);
			throw fehler;
		}
	}

	/**
	 * Description: Die Methode pr�ft, ob die L�nge der Kartennummer korrekt ist und
	 * nur positive Zahlen beinhaltet.
	 * 
	 * @author crunchie
	 * @return Boolean, Wert true wird zur�ckgegeben, wenn die Kartennummer die
	 *         richtige L�nge hat und nur positive Zahlen beinhaltet. Der Wert false
	 *         wird zur�ckgegeben, wenn die Kartennummer zu kurz oder zu lang ist
	 *         oder nicht nur Zahlen beinhaltet. Der Grund wird als Popup
	 *         ausgegeben.
	 */
	public boolean checkLaenge(int length) {
		boolean pruefung;
		int summe = 0;
		boolean laenge = this.checklength();
		try {
			if (laenge) {

				for (int i = 0; i < length; i++) {
					int wert = setwertcc(this.getKartenNummer().charAt(i));
					summe = summe + wert;
				}
				pruefung = true;
			} else if (!laenge && this.getKartenNummer().length() < length) {
				fehler.setFehler(3);
				throw fehler;
			} else {
				fehler.setFehler(2);
				throw fehler;
			}
		} catch (CustomUnchecked e) {
			JOptionPane.showMessageDialog(null, e.getFehler());
			pruefung = false;
		}
		return pruefung;
	}
}
