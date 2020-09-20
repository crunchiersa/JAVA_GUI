package kranchie.java.kommunikation.woerter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public abstract class Word {
    /* Attributes */
    private String inhalt, sprache;
    private int	   laenge;
    private CustomUnchecked fehler = new CustomUnchecked("woerter");
    
    /*Constructor*/
    public Word() {
		this.inhalt = "";
		this.laenge = 0;
		this.sprache = "unbekannt";
    }

    public Word(boolean initialise) throws CustomUnchecked {

    	if (initialise) {
    	String wort = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein: ");
    	if (wort.length() > 0) {
    		this.inhalt = wort;
    	} else {
    		fehler.setFehler(10);
    		throw fehler;
    	}
    	String sprache = JOptionPane.showInputDialog("Bitte geben Sie die Sprache des Wortes ein: ");
    	if (sprache.length() > 0) {
    		this.sprache = sprache;
    	} else {
    		throw new CustomUnchecked("woerter", 6);
    	}
    	this.deflaenge();
    	} else {
    		this.inhalt = "";
    		this.laenge = 0;
    		this.sprache = "unbekannt";
    	}
    }
    
    public Word(String inhalt) {
    	this.inhalt = inhalt;
    	this.deflaenge();
    	this.sprache = "unbekannt";
    }
    
    public Word(String inhalt, String sprache) {
    	this.inhalt = inhalt;
    	this.deflaenge();
    	this.sprache = sprache;
    }
    
    public Word(String inhalt, int laenge) throws CustomUnchecked {
    	if (inhalt.length() != laenge) {
    		//throw new CustomUnchecked("woerter", 7);
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
    		//throw new CustomUnchecked("woerter", 7);
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
    public void inhalteingeben() {
	String eingabe;
	eingabe = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein:");
		if (eingabe.length() > 0) {
		    this.inhalt = eingabe;
		    this.deflaenge();
		} else {
		    JOptionPane.showMessageDialog(null, "Das Eingabefeld beinhaltet keinen Text.");
		    this.inhalteingeben();
		}
    }

    // Overloaded eingeben() mit der Möglichkeit das Wort bereits mitzugeben.
    public void inhalteingeben(String eingabe) {
	this.inhalt = eingabe;
	this.deflaenge();
    }
    
    public void spracheeingeben(String sprache) {
	this.sprache = sprache;
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
    		//throw new CustomUnchecked("woerter", 9);
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
    		//throw new CustomUnchecked("woerter", 8);
    		fehler.setFehler(8);
    		throw fehler;
    	}
	
    }

    public static void clearClipboard() {
	StringSelection	stringSelection	= new StringSelection("");
	Clipboard	clpbrd		= Toolkit.getDefaultToolkit().getSystemClipboard();
	clpbrd.setContents(stringSelection, null);
    }

    public static void setClipboard(String content) {
	StringSelection	stringSelection	= new StringSelection(content);
	Clipboard	clpbrd		= Toolkit.getDefaultToolkit().getSystemClipboard();
	clpbrd.setContents(stringSelection, null);
    }

}
