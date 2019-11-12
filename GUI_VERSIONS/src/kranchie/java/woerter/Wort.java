package kranchie.java.woerter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

public class Wort {
    /* Attributes */
    private String inhalt;
    private int	   laenge;

    /* Constructors */
    public Wort(String inhalt, int laenge) {
	this.inhalt = inhalt;
	this.laenge = laenge;
    }

    public Wort(int laenge) {
	this.inhalt = "";
	this.laenge = laenge;
    }

    public Wort() {
	this.inhalt = "";
	this.laenge = 0;
    }

    /* Methoden */
    // Möglichkeit ein Passwort einzugeben.
    public void eingeben() {
	String eingabe;
	eingabe = JOptionPane.showInputDialog("Bitte geben Sie ein Wort ein:");
	if (this.laenge > 0 && eingabe.length() <= this.laenge) {
	    this.inhalt = eingabe;
	    deflaenge();
	} else {
	    JOptionPane.showMessageDialog(null, "Das eingegebene Wort ist länger als die definierte Länge des Wortes");
	    this.eingeben();
	}
    }

    // Overloaded eingeben() mit der Möglichkeit das Wort bereits mitzugeben.
    public void eingeben(String eingabe) {
	this.inhalt = eingabe;
	deflaenge();
    }

    // Auslesen des Wortes.
    public String auslesen() {
	String ausgabe = this.inhalt;
	return ausgabe;
    }

    // Laenge des Wortes bestimmen.
    private void deflaenge() {
	this.laenge = inhalt.length();
    }

    // Laenge des Wortes auslesen.
    public int laengeauslesen() {
	return this.laenge;
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
