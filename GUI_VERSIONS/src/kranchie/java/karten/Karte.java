package kranchie.java.karten;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public class Karte {

    // Attribute
    private String	      kartenNummer;
    private String	      kontrollNummer;
    private String	      kartenArt;
    private int		      kartennrlaenge;
    private ArrayList<String> felder	 = new ArrayList<String>();
    private ArrayList<String> feldInhalt = new ArrayList<String>();
    private int		      gueltigKeitMonat;
    private int		      gueltigKeitJahr;

    // Constructors
    public Karte(String kartenNummer, String kontrollNummer, String kartenart, ArrayList<String> felder,
	    ArrayList<String> feldInhalt, int gueltigKeitMonat, int gueltigKeitJahr, int nummerlaenge) {
	super();
	this.kartenNummer     = kartenNummer;
	this.kontrollNummer   = kontrollNummer;
	this.kartenArt	      = kartenart;
	this.felder	      = felder;
	this.feldInhalt	      = feldInhalt;
	this.gueltigKeitMonat = gueltigKeitMonat;
	this.gueltigKeitJahr  = gueltigKeitJahr;
	this.setkartennrlaenge(nummerlaenge);
    }

    public Karte(String kartenNummer, String controllnr, String kartenart, int monatgueltig, int jahrgueltig) {
	super();
	this.kartenNummer     = kartenNummer;
	this.kartenArt	      = kartenart;
	this.kontrollNummer   = controllnr;
	this.gueltigKeitMonat = monatgueltig;
	this.gueltigKeitJahr  = jahrgueltig;
    }

    public Karte(String kartenNummer, int nummerlaenge) {
	super();
	this.kartenNummer   = kartenNummer;
	this.kartennrlaenge = nummerlaenge;
    }

    public Karte(String kartenNummer, String kartenart) {
	super();
	this.kartenNummer     = kartenNummer;
	this.kartenArt	      = kartenart;
	this.kontrollNummer   = "";
	this.gueltigKeitMonat = 00;
	this.gueltigKeitJahr  = 00;
	this.setkartennrlaenge(00);
    }

    public Karte() {
	super();
	this.kartenNummer     = "";
	this.kontrollNummer   = "";
	this.kartenArt	      = "";
	this.gueltigKeitMonat = 00;
	this.gueltigKeitJahr  = 00;
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

    public ArrayList<String> getFelder() {
	CustomUnchecked error = new CustomUnchecked("Es wurden keine Felder definiert.");
	if (this.checkFelder()) {
	    return this.felder;
	} else {
	    throw error;
	}
    }

    public void setFelder(ArrayList<String> felder) {
	this.felder = felder;
    }

    public ArrayList<String> getFeldInhalt() {
	CustomUnchecked error = new CustomUnchecked("Es wurden keine Feldinhalte definiert.");
	if (this.checkFeldInhalt()) {
	    return this.feldInhalt;
	} else {
	    throw error;
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

    // Prüfen ob FeldInhalt Einträge hat.
    private boolean checkFeldInhalt() {
	boolean	isSet;

	int	arraysize = this.feldInhalt.size();
	if (arraysize == 0) {
	    isSet = false;
	} else {
	    isSet = true;
	}
	return isSet;
    }

    // Prüfen ob Felder Einträge hat.
    private boolean checkFelder() {
	boolean	isSet;

	int	arraysize = this.felder.size();
	if (arraysize == 0) {
	    isSet = false;
	} else {
	    isSet = true;
	}
	return isSet;
    }

    /**
     * Description: Prüft ob die Länge der Kartennummer mit der hinterlegten
     * Kartennummerlänge übereinstimmt.
     * 
     * @author crunchie
     * @return Boolean, true wird zurückgegeben, wenn beide Längen
     *         übereinstimmen, false falls nicht.
     */
    public boolean checklength() {
	boolean	laengeok;
	int	nrlaenge = this.kartenNummer.length();
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
     * @return Integer, gibt den Wert numerischen Wert des Characters der
     *         mitgegeben wurde wieder. Falls der Parameter keine Zahl ist,
     *         wird -2 zurückgegeben.
     */
    public int setwertcc(char u) {
	int	a;
	boolean	isDigit	= Character.isDigit(u);
	if (isDigit) {
	    a = Character.getNumericValue(u);
	} else {
	    a = -2;
	}
	return a;
    }

    /**
     * Description: Die Methode prüft, ob die Länge der Kartennummer korrekt
     * ist und nur positive Zahlen beinhaltet.
     * 
     * @author crunchie
     * @return Boolean, Wert true wird zurückgegeben, wenn die Kartennummer die
     *         richtige Länge hat und nur positive Zahlen beinhaltet. Der Wert
     *         false wird zurückgegeben, wenn die Kartennummer zu kurz oder zu
     *         lang ist oder nicht nur Zahlen beinhaltet. Der Grund wird als
     *         Popup ausgegeben.
     */
    public boolean checkLaenge(int length, String kartenart) {
	boolean	pruefung;
	int	summe  = 0;
	boolean	laenge = this.checklength();
	try {
	    if (laenge) {
		for (int i = 0; i < length; i++) {
		    int wert = setwertcc(this.getKartenNummer().charAt(i));
		    if (wert >= 0) {
			summe = 0;
		    } else {
			summe = summe + wert;
		    }
		}
		if (summe < 0) {
		    // JOptionPane.showMessageDialog(null,);
		    String	    inhalt = "Die " + kartenart + "nummer enthält nicht nur zahlen!";
		    CustomUnchecked error  = new CustomUnchecked(inhalt);
		    JOptionPane.showMessageDialog(null, error.getMessage());
		    throw error;
		} else {
		    pruefung = true;
		}
	    } else if (!laenge && this.getKartenNummer().length() < length) {
		String inhalt = "Die " + kartenart + "nummer ist zu kurz!";
		JOptionPane.showMessageDialog(null, inhalt);
		throw new CustomUnchecked(inhalt);
	    } else {
		String inhalt = "Die " + kartenart + "nummer ist zu lang!";
		JOptionPane.showMessageDialog(null, inhalt);
		throw new CustomUnchecked(inhalt);
	    }
	} catch (CustomUnchecked e) {
	    pruefung = false;
	}
	return pruefung;
    }
}
