package kranchie.java.dateien;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class HCMDatei extends Datei {

    private String	      dateiEndung  = ".dat";
    private ArrayList<String> mesgStruktur = new ArrayList<String>();
    private ArrayList<String> mesg	   = new ArrayList<String>();
    private String	      nachricht;

    public HCMDatei() {
	super();
    }

    public HCMDatei(String dateiName, String speicherPfad) {
	super(dateiName, speicherPfad);
    }

    public String getDateiEndung() {
	return dateiEndung;
    }

    public void setDateiEndung(String dateiEndung) {
	this.dateiEndung = dateiEndung;
    }

    public String getSegmentcontent(int segmentnr) {
	String segmentcontent = this.mesg.get(segmentnr);
	return segmentcontent;
    }

    public int getSegmentnr(String segment) {
	int	segmentnr = 0;
	String	segname;
	boolean	same;
	int	currsize  = this.mesgStruktur.size();
	for (int i = 0; i <= currsize; i++) {
	    segmentnr = i;
	    segname   = this.mesgStruktur.get(i);
	    same      = segname.equals(segment);
	    if (same) {
		break;
	    }
	}
	return segmentnr;
    }

    public String getNachricht() {
	return nachricht;
    }

    /*----------------*/
    /* AufbauMethoden */
    /*----------------*/

    private String leerfuellen(int anzahl) {
	String leerzeichen = "";
	for (int i = 0; i <= anzahl; i++) {
	    leerzeichen = leerzeichen + " ";
	}
	return leerzeichen;
    }

    // Set segment name passed as String into specified place
    // mesgStruktur-Array.
    private void setMesgSegment(String nachricht, int reihenfolge) {
	ArrayList<String> mesgStruktur = new ArrayList<String>();
	int		  order	       = reihenfolge;
	// Determine number of items in Array. And fetch first and last segment.
	int		  currsize     = mesgStruktur.size();
	String		  firstseg     = this.mesgStruktur.get(0);
	String		  lastseg      = this.mesgStruktur.get(currsize);
	// Erstes Segment muss immer HEA sein.
	if (this.mesgStruktur.isEmpty() || firstseg != "HEA") {
	    this.mesgStruktur.add(0, "HEA");
	    order = order + 1;
	}
	// Letztes Segment ist immer END
	if (lastseg != "END") {
	    currsize = currsize + 1;
	    this.mesgStruktur.add(currsize, "END");
	}
	// Prüft ob Stelle an der Segment eingefügt wird größer als die maximal
	// Anzahl in Struktur ist oder an erster Stelle stehen soll.
	if (reihenfolge > currsize) {
	    this.priv_meth_newsegnumber(
		    "So viele Segmente gibt es in der Struktur nicht. Bitte geben Sie eine Zahl kleiner " + currsize
			    + " ein.");
	} else if (reihenfolge == 0) {
	    reihenfolge = reihenfolge + 1;
	}
	// Segment in die Struktur hinzufügen und Struktur returnen.
	this.mesgStruktur.add(reihenfolge, nachricht);
    }

    private void genSegment(String seg, String content) {
	String segment = "";
	int    segnr;
	segnr = this.getSegmentnr(seg);
	this.mesg.add(segnr, content);
    }

    private String buildmesg() {
	String mesg = "";
	int    size = this.mesg.size();
	for (int i = 0; i <= size; i++) {
	    mesg = mesg + this.mesg.get(i);
	}
	return mesg;
    }

    // true => Neuer Eintrag wurde gesetzt. false => Kein neuer Name wurde
    // vergeben, nicht möglich.
    @SuppressWarnings("unused")
    private int priv_meth_newsegnumber(String nachricht) {
	int    neueZahl	= 99;
	String antwort;
	do {
	    antwort = JOptionPane.showInputDialog(null, nachricht);
	    if (antwort.length() == 0) {
		JOptionPane.showMessageDialog(null, "Das Textfeld war leer!");
	    }
	} while (antwort.length() == 0);
	try {
	    neueZahl = Integer.parseInt(antwort);
	} catch (NumberFormatException e) {
	    this.priv_meth_newsegnumber("Der eingegebene Wert ist keine Zahl");
	}
	return neueZahl;
    }
    /*---------------*/
    /* PruefMethoden */
    /*---------------*/

    private int getsegLaenge(String seg) {
	int segLaenge = 0;
	/*
	 * Kopf, Patientenstamm, Fall, Anforderung, Anamnese, medizinische
	 * Fragestellung, medizinische Leistung, ergänzender Text, Risikofaktor,
	 * Risikofaktor-Bemerkung, Ende
	 */
	int healaenge = 97;
	int patlaenge = 743;
	int fallaenge = 2900;
	int anflaenge = 313;
	int analaenge = 37;
	int mfrlaenge = 137;
	int mlelaenge = 356;
	int egtlaenge = 150;
	int rsflaenge = 125;
	int rsblaenge = 160;
	int endlaenge = 3;

	switch (seg) {
	    case "hea":
		segLaenge = healaenge;
		break;
	    case "pat":
		segLaenge = patlaenge;
		break;
	    case "fal":
		segLaenge = fallaenge;
		break;
	    case "ana":
		segLaenge = analaenge;
		break;
	    case "mfr":
		segLaenge = mfrlaenge;
		break;
	    case "mle":
		segLaenge = mlelaenge;
		break;
	    case "egt":
		segLaenge = egtlaenge;
		break;
	    case "rsf":
		segLaenge = rsflaenge;
		break;
	    case "rsb":
		segLaenge = rsblaenge;
		break;
	    case "end":
		segLaenge = endlaenge;
		break;
	    default:
		segLaenge = 0;
		break;
	}
	return segLaenge;
    }

    private int getgesLaenge() {
	int gesLaenge  = 0, seglaenge;
	int anzahlsegm = this.mesgStruktur.size();

	for (int i = 0; i < anzahlsegm; i++) {
	    seglaenge = getsegLaenge(this.mesgStruktur.get(i));
	    gesLaenge = gesLaenge + seglaenge;
	}
	return gesLaenge;
    }

    // true --> message has the correct lenght, false --> message is too long
    // or too short.
    private boolean checklenght() {
	boolean	lenght;
	int	laenge, gesamlaenge;

	laenge	    = this.nachricht.length();
	gesamlaenge = this.getgesLaenge();
	if (laenge < gesamlaenge || laenge > gesamlaenge) {
	    lenght = false;
	} else {
	    lenght = true;
	}
	return lenght;
    }

}
