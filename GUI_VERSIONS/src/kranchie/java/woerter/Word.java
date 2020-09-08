package kranchie.java.woerter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

public abstract class Word {
    /* Attributes */
    private String inhalt, sprache;
    private int	   laenge;
    
    /*Constructor*/
    public Word() {
    	this.inhalt = "";
    	this.deflaenge();
    	this.sprache = "";
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
    
    public Word(String inhalt, int laenge) {
    	if (inhalt.length() != laenge) {
    		String error = "Die tatsächliche Länge " + inhalt.length() + " stimmt nicht mit der angegebenen Länge " + laenge + " überein!";
    		throw new CustomUnchecked(error);
    	} else {
    		this.inhalt = inhalt;
    		this.laenge = laenge;
    		this.sprache = "unbekannt";
    	}
    }

    public Word(String inhalt, int laenge, String sprache) {
    	if (inhalt.length() != laenge) {
    		String error = "Die tatsächliche Länge " + inhalt.length() + " stimmt nicht mit der angegebenen Länge " + laenge + " überein!";
    		throw new CustomUnchecked(error);
    	} else {
    		this.inhalt = inhalt;
    		this.laenge = laenge;
    		this.sprache = sprache;
    	}
    }
    
    /* Methoden */
    // Möglichkeit ein Wort einzugeben.
    public void eingeben() {
	String eingabe;
	eingabe = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein:");
		if (eingabe.length() > 0) {
		    this.inhalt = eingabe;
		    this.deflaenge();
		} else {
		    JOptionPane.showMessageDialog(null, "Das Eingabefeld beinhaltet keinen Text.");
		    this.eingeben();
		}
    }

    // Overloaded eingeben() mit der Möglichkeit das Wort bereits mitzugeben.
    public void eingeben(String eingabe) {
	this.inhalt = eingabe;
	deflaenge();
    }

    // Auslesen des Wortes.
    public String inhaltauslesen() {
    	if (this.inhalt.length() > 0) {
    		String ausgabe = this.inhalt;
    		return ausgabe;
    	} else {
    		String error = "Es konnte kein Inhalt gefunden werden.";
    		throw new CustomUnchecked(error);
    	}
    }
    
    public String spracheauslesen() {
    	if (this.sprache.length() > 0) {
    		String ausgabe = this.sprache;
    		return ausgabe;
    	} else {
    		String error = "Es wurde keine Sprache hinterlegt.";
    		throw new CustomUnchecked(error);
    	}
    }
    

    // Laenge des Wortes bestimmen.
    private void deflaenge() {
	this.laenge = this.inhalt.length();
    }

    // Laenge des Wortes auslesen.
    public int laengeauslesen() {
    	if (this.laenge > 0) {
    		return this.laenge;
    	} else {
    		String error = "Es konnte kein Inhalt gefunden werden.";
    		throw new CustomUnchecked(error);
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
