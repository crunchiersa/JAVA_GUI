package kranchie.java.customExceptions;

import java.util.ArrayList;

public class CustomUnchecked extends Exception {
	
	/**
	 * Variablen
	 */
	private static final long serialVersionUID = 0000000001L;
	private int errnum;
	private ArrayList<String> fehlercodes = new ArrayList<>();
	private String fehler;
	
	//Constructors

	public CustomUnchecked(String paket, int index) {
		super();
		this.setErrortext(paket);
		this.setFehler(index);
		this.setErrmesg();
		this.getFehler();
	}
	
	public CustomUnchecked(String paket) {
		super();
		this.setErrortext(paket);
	}

	//Public Methoden
	public void setFehler(int fehler) {
		this.errnum = fehler;
		this.setErrmesg();
	}
	
	public String getFehler() {
		if(fehler.length() > 0) {
			return this.fehler;
		} else {
			throw new InternalError("Keine Fehlernachricht vorhanden.");
		}
		
	}
	//Private Methoden
	private void setErrmesg() {
		this.fehler = this.fehlercodes.get(errnum);
	}
	
	private void setErrortext(String paket) {
		switch(paket) {
		case "woerter":		this.fehlercodes.add(0, "Der gelieferte Pfad existiert nicht.");
							this.fehlercodes.add(1, "Die Datei ist unter dem Pfad nicht vorhanden.");
							this.fehlercodes.add(2, "Es ist ein unerwarteter Fehler aufgetreten.");
							this.fehlercodes.add(4, "Der genannte Datenbanktyp ist unzulässig");
							this.fehlercodes.add(5, "Kein Datenbanktyp definiert!");
							this.fehlercodes.add(6, "Es wurde keine Sprache eingegeben");
							this.fehlercodes.add(7, "Die tatsächliche Länge stimmt nicht mit der angegebenen Länge überein!");
							this.fehlercodes.add(8, "Es konnte kein Inhalt gefunden werden.");
							this.fehlercodes.add(9, "Es wurde keine Sprache hinterlegt.");
							this.fehlercodes.add(10, "Es wurde kein Wort eingegeben");
							break;
		case "saetze":		;
							break;
		case "dateien":		this.fehlercodes.add(0, "Es ist ein unerwarteter Fehler aufgetreten.");
							this.fehlercodes.add(1, "Es wurden die folgenden Variablen noch nicht befüllt: Dateiendung.");
							this.fehlercodes.add(2, "Es wurden die folgenden Variablen noch nicht befüllt: Dateiendung, Speicherpfad.");
							this.fehlercodes.add(3, "Es wurden die folgenden Variablen noch nicht befüllt: Speicherpfad.");
							this.fehlercodes.add(4, "Sie haben keine Schreibrechte unter dem angegebenen Pfad.");
							this.fehlercodes.add(5, "Der angegebene Pfad ist nicht gültig.");
							this.fehlercodes.add(6, "Die angegebene Datei existiert bereits unter dem Pfad.");
							break;
		case "karten":		this.fehlercodes.add(0, "Es wurden keine Feldinhalte definiert.");
							this.fehlercodes.add(1, "Der Wert beinhaltet nicht nur Zahlen.");
							this.fehlercodes.add(2, "Die Kartennummer ist zu lang!");
							this.fehlercodes.add(3, "Die Kartennummer ist zu kurz!");
							break;
		case "test":		this.fehlercodes.add(0, "Testfehler - Stelle 0");
							this.fehlercodes.add(1, "Testfehler - Stelle 1");
							break;
		default:			throw new RuntimeException("Das Paket ist nicht definiert");
		}
	}
	
}
