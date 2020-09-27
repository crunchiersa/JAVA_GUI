package kranchie.java.kommunikation.woerter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public abstract class Word {
	/* Attributes */
	private String inhalt, sprache;
	private int laenge;
	CustomUnchecked fehler = new CustomUnchecked("woerter");

	/* Constructor */
	public Word() throws CustomUnchecked {
		this.inhalteingeben("");
		this.laenge = 0;
		this.sprache = "unbekannt";
	}

	public Word(boolean initialise) throws CustomUnchecked {
		if (initialise) {
			this.inhalteingeben();
			this.spracheeingeben();
			this.deflaenge();
		} else {
			this.inhalteingeben("");
			this.spracheeingeben("unbekannt");
			this.laenge = 0;
		}
	}

	public Word(String inhalt) throws CustomUnchecked {
		this.inhalt = inhalt;
		this.deflaenge();
		this.sprache = "unbekannt";
	}

	public Word(String inhalt, String sprache) throws CustomUnchecked {
		this.inhalteingeben(inhalt);
		this.spracheeingeben(sprache);
		this.deflaenge();
	}

	public Word(String inhalt, int laenge) throws CustomUnchecked {
		if (inhalt.length() != laenge) {
			fehler.setFehler(7);
			throw fehler;
		} else {
			this.inhalt = inhalt;
			this.laenge = laenge;
			this.sprache = "unbekannt";
		}
	}

	public Word(String inhalt, int laenge, String sprache) throws CustomUnchecked {
		if (inhalt.length() != laenge) {
			fehler.setFehler(7);
			throw fehler;
		} else {
			this.inhalt = inhalt;
			this.laenge = laenge;
			this.sprache = sprache;
		}
	}

	/* Methoden */
	// Möglichkeit ein Wort einzugeben.
	public void inhalteingeben() throws CustomUnchecked {
		String eingabe;
		eingabe = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein:");
		if (eingabe.length() >0 || eingabe == "") {
			this.inhalt = eingabe;
		} else {
			fehler.setFehler(10);
			throw fehler;
		}
	}

	// Overloaded eingeben() mit der Möglichkeit das Wort bereits mitzugeben.
	public void inhalteingeben(String eingabe) throws CustomUnchecked {
		if (eingabe.length() > 0 || eingabe == "") {
			this.inhalt = eingabe;
		} else {
			fehler.setFehler(8);
			throw fehler;
		}
		this.deflaenge();
	}

	public void spracheeingeben() throws CustomUnchecked {
		String eingabe;
		eingabe = JOptionPane.showInputDialog("Bitte geben Sie die Sprache des Wortes ein: ");
		if (eingabe.length() > 0 || sprache == "") {
			this.sprache = eingabe;
		} else {
			fehler.setFehler(6);
			throw fehler;
		}
	}

	public void spracheeingeben(String sprache) throws CustomUnchecked {
		if (sprache.length() > 0 || sprache == "") {
			this.sprache = sprache;
		} else {
			fehler.setFehler(6);
			throw fehler;
		}

	}

	// Auslesen des Wortes.
	public String inhaltauslesen() throws CustomUnchecked {
		if (this.inhalt.length() > 0) {
			String ausgabe = this.inhalt;
			return ausgabe;
		} else {
			fehler.setFehler(8);
			throw fehler;
		}
	}

	public String spracheauslesen() throws CustomUnchecked {
		if (this.sprache.length() > 0) {
			String ausgabe = this.sprache;
			return ausgabe;
		} else {
			fehler.setFehler(9);
			throw fehler;
		}
	}

	// Laenge des Wortes bestimmen.
	private void deflaenge() {
		this.laenge = this.inhalt.length();
	}

	// Laenge des Wortes auslesen.
	public int laengeauslesen() throws CustomUnchecked {
		if (this.laenge > 0) {
			return this.laenge;
		} else {
			fehler.setFehler(8);
			throw fehler;
		}

	}

	public static void clearClipboard() {
		StringSelection stringSelection = new StringSelection("");
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	public static void setClipboard(String content) {
		StringSelection stringSelection = new StringSelection(content);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

}
