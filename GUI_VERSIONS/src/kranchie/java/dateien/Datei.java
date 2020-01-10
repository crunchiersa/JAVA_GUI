package kranchie.java.dateien;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import kranchie.java.customExceptions.CustomUnchecked;

public class Datei {

    /*-----------*/
    /* Attribute */
    /*-----------*/
    private String	      dateiName;
    private String	      speicherPfad;
    private String	      zeilenInhalt;
    private ArrayList<String> inhaltLesen   = new ArrayList<String>(), inhaltSchreiben = new ArrayList<String>();
    private String	      dateiEndung;
    private String	      absolutePath;
    private boolean	      nooverwrite;
    private boolean	      validpath	    = false;
    private boolean	      validname	    = false;
    private boolean	      validending   = false;
    private boolean	      validfullname = false;
    private boolean	      validfullpath = false;

    /* Constructor */
    public Datei() {
	this.setDateiName(JOptionPane.showInputDialog(null, "Bitte geben Sie den Dateinamen ein:"));
	this.setSpeicherPfad(JOptionPane.showInputDialog(null, "Bitte geben Sie den Speicherpfad an:"));
	this.nooverwrite = true;
    }

    public Datei(String dateiName, String speicherPfad) {
	super();
	this.setDateiName(dateiName);
	this.setSpeicherPfad(speicherPfad);
	this.nooverwrite = true;
    }

    public Datei(String dateiName, String speicherPfad, boolean nooverwrite) {
	super();
	this.setDateiName(dateiName);
	this.setSpeicherPfad(speicherPfad);
	this.nooverwrite = nooverwrite;
    }

    /*-------------------*/
    /* Getters + Setters */
    /*-------------------*/

    public String getDateiName() {
	return this.dateiName;
    }

    public String getdateieEndung() {
	return this.dateiEndung;
    }

    public String getabsolutePath() {
	return this.absolutePath;
    }

    private void setDateiName(String dateiName) {
	if (this.validending || (this.validending && this.validpath)) {
	    this.dateiName     = dateiName + "." + this.dateiEndung;
	    this.validname     = true;
	    this.validfullname = true;
	    this.setabsolutePath();
	} else {
	    this.dateiName     = dateiName;
	    this.validname     = true;
	    this.validfullname = false;
	}
	
    }

    public void setdateiEndung(String endung) {
	this.dateiEndung = endung;
	this.validending = true;
	if (validname || (validname && validpath)) {
	    this.setDateiName(this.dateiName);
	} else if (validfullname) {
	    String error = "Es gibt bereits eine Dateiendung (" + this.dateiEndung
		    + ") es kann daher keine Dateiendung gesetzt werden.";
	    throw new CustomUnchecked(error);
	}
    }

    public void setSpeicherPfad(String speicherPfad) {
	validpath = this.checkname_validpath(speicherPfad);
	try {
	    if (validpath) {
		boolean writable = this.checkname_canwrite(speicherPfad);
		if (writable) {
		    this.speicherPfad = speicherPfad;
		    this.validpath    = true;
		} else {
		    throw new CustomUnchecked("1");
		}
	    } else {
		throw new CustomUnchecked("2");
	    }
	} catch (CustomUnchecked e) {
	    String fehler = e.getMessage(), error;
	    switch (fehler) {
		case "1":
		    error = " Sie haben unter dem angegebenen Pfad (" + speicherPfad + ") keine Schreibberechtigung.";
		    break;
		case "2":
		    error = "Der angegebene Pfad (" + speicherPfad + ") existiert nicht.";
		    break;
		default:
		    error = "Es ist ein unerwarteter Fehler aufgetreten. Die Fehlernachricht lautet: " + fehler;
	    }
	    throw new CustomUnchecked(error);
	}
    }

    private boolean setabsolutePath() {
	String error  = "Es wurden die folgenden Variablen noch nicht befüllt: ";
	String abpath = this.speicherPfad + File.separator + this.dateiName;
	try {
	    if (this.validfullname && this.validpath) {
		boolean	validpath = this.checkname_validpath(this.speicherPfad);
		boolean	canwrite  = this.checkname_canwrite(this.speicherPfad);
		boolean	fileexist = this.checkname_fileexist(this.speicherPfad, this.dateiName);
		try {
		    if (!canwrite) {
			throw new CustomUnchecked("1");
		    } else if (!validpath) {
			throw new CustomUnchecked("2");
		    } else if (fileexist) {
			this.setnooverwrite(this.priv_meth_ueberschreib());
			if (nooverwrite) {
			    throw new CustomUnchecked("3");
			} else {
			    this.absolutePath = abpath;
			    return true;
			}
		    } else {
			this.absolutePath = abpath;
			return true;
		    }
		} catch (CustomUnchecked e) {
		    String fehler = e.getMessage();
		    switch (fehler) {
			case "1":
			    error = "Sie haben keine Schreibrechte unter " + this.speicherPfad;
			    break;
			case "2":
			    error = "Der angegebene Pfad (" + this.speicherPfad + ") ist nicht gültig.";
			    break;
			case "3":
			    error = "Die angegebene Datei existiert bereits unter dem Pfad.";
			    break;
			default:
			    error = "Es ist ein unerwarteter Fehler aufgetreten.";
			    break;
		    }
		    JOptionPane.showMessageDialog(null, error);
		    return false;
		}
	    } else if (this.validpath && this.validname && !this.validfullname) {
		throw new CustomUnchecked(error + "Dateiendung.");
	    } else if (this.validname && !this.validpath && !this.validfullname) {
		throw new CustomUnchecked(error + "Dateiendung, Speicherpfad.");
	    } else if (this.validending && this.validfullname && !this.validpath) {
		throw new CustomUnchecked(error + "Speicherpfad.");
	    } else {
		throw new CustomUnchecked("Es ist ein unerwarteter Fehler aufgetreten.");
	    }
	} catch (CustomUnchecked e) {
	    String fehler = e.getMessage();
	    JOptionPane.showMessageDialog(null, fehler);
	    return false;
	}
    }

    private void setnooverwrite(boolean a) {
	this.nooverwrite = a;
    }

    public String getSpeicherPfad() {
	return speicherPfad;
    }

    public String getZeilenInhalt(int zeile) {
	this.zeilenInhalt = inhaltLesen.get(zeile - 1);
	return this.zeilenInhalt;
    }

    public ArrayList<String> getInhaltLesen() {
	return inhaltLesen;
    }

    public void setInhaltSchreiben(ArrayList<String> inhaltSchreiben) {
	this.inhaltSchreiben = inhaltSchreiben;
    }

    @SuppressWarnings("unused")
    private static void clearClipboard() {
	StringSelection	stringSelection	= new StringSelection("");
	Clipboard	clpbrd		= Toolkit.getDefaultToolkit().getSystemClipboard();
	clpbrd.setContents(stringSelection, null);
    }

    @SuppressWarnings("unused")
    private static void setClipboard(String content) {
	StringSelection	stringSelection	= new StringSelection(content);
	Clipboard	clpbrd		= Toolkit.getDefaultToolkit().getSystemClipboard();
	clpbrd.setContents(stringSelection, null);
    }

    /*-----------------*/
    /* EinleseMethoden */
    /*-----------------*/

    /*
     * Methode zum Einlesen der Datei. Falls die ganze Datei eingelesen werden
     * soll muss die Zeilenanzahl 0 sein.
     */

    public ArrayList<String> pub_einlesen(int zeile) throws IOException {
	try {
	    Scanner s = new Scanner(new File(this.absolutePath));
	    this.inhaltLesen = new ArrayList<String>();
	    if (zeile > 0) {
		for (int i = 0; i < zeile; i++) {
		    this.inhaltLesen.add(s.nextLine());
		}
	    } else {
		while (s.hasNextLine()) {
		    this.inhaltLesen.add(s.nextLine());
		}
	    }
	    s.close();
	    return this.inhaltLesen;
	} catch (IndexOutOfBoundsException e) {
	    ArrayList<String> error	= new ArrayList<String>();
	    String	      errormesg	= e.getClass() + " " + e.getMessage();
	    error.add(errormesg);
	    return error;
	}
    }// End of Method

    /*
     * Overloading Methode, falls die gesamte Datei gelesen werden soll, wird
     * die Anzahl der Zeilen automatisch auf 0 gesetzt und muss nicht
     * mitgegeben werden.
     */
    public ArrayList<String> pub_einlesen() throws IOException {
	ArrayList<String> liste = pub_einlesen(0);
	return liste;
    }// End of Method

    /*-----------------*/
    /* SchreibMethoden */
    /*-----------------*/

    public boolean pub_pruefSchreib() throws IOException {
	boolean	nameexist, newfilename, uebschreib, written;
	int	zaehler	= 0;
	nameexist = this.checkname_fileexist(); // Prüfen ob
	// Datei unter dem Pfad bereits existiert.
	if (nameexist) {
	    uebschreib = this.priv_meth_ueberschreib();
	    while (nameexist && !uebschreib) {
		if (zaehler > 0) {
		    JOptionPane.showMessageDialog(null, "Die Datei (" + this.dateiName + ") existiert bereits!");
		}
		zaehler	    = zaehler + 1;
		newfilename = this.priv_meth_newname();
		if (newfilename) {
		    nameexist = this.checkname_fileexist();
		}
	    }
	}
	return written = this.priv_meth_schreiben();
    }

    /*
     * Write a passed ArrayList<String> into a file at location passed in
     * parameters. status == true --> Der Schreibvorgang ist erfolgt. status ==
     * false --> Es ist ein Fehler aufgetreten.
     */

    private boolean priv_meth_schreiben() throws IOException {
	boolean status;
	// write the content in file
	try {
	    /*
	     * Der zweite Parameter von Filewriter bestimmt, ob bei einer bestehenden
	     * Datei, die Datei überschrieben wird (false), oder der Inhalt appended
	     * wird (true).
	     */
	    FileWriter writer = new FileWriter(this.absolutePath, this.nooverwrite);
	    int	       size   = this.inhaltSchreiben.size();
	    for (int i = 0; i < size; i++) {
		writer.write(this.inhaltSchreiben.get(i));
		if (i < size) {
		    writer.write("\n");
		}
	    }
	    writer.close();
	    JOptionPane.showMessageDialog(null, "Die Datei (" + this.dateiName + ") wurde geschrieben!");
	    status = true;
	} catch (IOException e) {
	    System.out.println(e.getClass() + " " + e.getMessage());
	    status = false;
	}
	return status;
    }// End of Method

    /*---------------*/
    /* PruefMethoden */
    /*---------------*/

    // true => Datei existiert unter diesem Pfad schon. false => Datei
    // existiert unter diesem Pfad nicht.

    public boolean checkname_fileexist() {
	File	datei	= new File(this.absolutePath);
	boolean	fexists	= datei.exists();
	if (fexists) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean checkname_fileexist(String path, String filename) {
	File	datei	= new File(path + File.pathSeparator + filename);
	boolean	fexists	= datei.exists();
	if (fexists) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean checkname_validpath(String path) {
	File	datei	= new File(path);
	boolean	fexists	= datei.exists();
	if (fexists) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean checkname_canwrite(String path) {
	File	datei  = new File(path);
	boolean	fwrite = datei.canWrite();
	if (fwrite) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean checkname_canwrite(String path, String filename) {
	File	datei  = new File(path + File.pathSeparator + filename);
	boolean	fwrite = datei.canWrite();
	if (fwrite) {
	    return true;
	} else {
	    return false;
	}
    }

    // true => Neuer Name wurde vergeben. false => Kein neuer Name wurde
    // vergeben, nicht möglich.

    private boolean priv_meth_newname() {
	boolean	namechanged;
	String	antwort;
	do {
	    antwort = JOptionPane.showInputDialog(null, "Bitte geben Sie einen neuen Dateinamen an:");
	    if (antwort.length() == 0) {
		JOptionPane.showMessageDialog(null, "Das Textfeld war leer!");
	    }
	} while (antwort.length() == 0);
	this.setDateiName(antwort);
	namechanged = true;
	return namechanged;
    }

    // true => die existierende Datei soll überschrieben werden.

    private boolean priv_meth_ueberschreib() {
	boolean	ueberschreib = false;
	int	dialogButton = JOptionPane.YES_OPTION;
	int	dialogResult = JOptionPane.showConfirmDialog(null,
		"Der Dateiname existiert bereits. Möchten Sie die Datei überschreiben? ACHTUNG: Wenn Sie mit Ja antworten wird die bestehende Datei überschrieben! Es kann zu Datenverlust kommen!",
		"Überschreiben?", dialogButton);
	if (dialogResult == dialogButton) {
	    setnooverwrite(false);
	    ueberschreib = true;
	} else {
	    ueberschreib = false;
	}
	return ueberschreib;
    }
}