package kranchie.java.dateien;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kranchie.java.customExceptions.CustomUnchecked;

@SuppressWarnings("unused")
public class HCMDatei extends Datei {
    
    /*----------------------*/
    /* Constructor Methoden */
    /*----------------------*/   

    private String	      dateiEndung  = ".dat";
    private ArrayList<String> mesgStruktur = new ArrayList<String>();
    private ArrayList<String> mesg	   = new ArrayList<String>();
    private String	      nachricht;

    public HCMDatei() throws CustomUnchecked {
	super();
    }

    public HCMDatei(String dateiName, String speicherPfad, boolean exist) throws CustomUnchecked {
	super(dateiName, speicherPfad, exist);
    }
    
    public HCMDatei(String dateiName, String speicherPfad, ArrayList<String> struktur, ArrayList<String> mesgContent, boolean exist) throws CustomUnchecked {
	super(dateiName,speicherPfad, exist);
	this.mesgStruktur = struktur;
	this.mesg = mesgContent;
    }

    /*-----------------*/
    /* Public Methoden */
    /*-----------------*/
    
    public String getDateiEndung() {
	return dateiEndung;
    }

    public void setDateiEndung(String dateiEndung) {
	this.dateiEndung = dateiEndung;
    }
    
    public String getNachricht() {
	return nachricht;
    }
    
    public void setstructandmesg(String segname, int reihenfolge, String seginhalt) {
	this.setMesgStruktSegment(segname, reihenfolge);
	this.setMesgSegmentContent(segname, seginhalt);
    }
    
    /*------------------*/
    /* Private Methoden */
    /*------------------*/
    
    //Get content of segment for specific segment given by name.
    private String getSegmentcontent(String segment) {
	int segnr = this.getSegmentnr(segment);
	String segmentcontent = this.mesg.get(segnr);	
	return segmentcontent;
    }

    //Get segment number of specified segment. If segmentnr 666 is returned --> segment could not be found.
    private int getSegmentnr(String segment) {
	int	segmentnr = 0;
	String	segname;
	boolean	same, found = false;
	int	currsize  = this.mesgStruktur.size();
	for (int i = 0; i <= currsize; i++) {
	    segmentnr = i;
	    segname   = this.mesgStruktur.get(i);
	    same      = segname.equals(segment);
	    if (same) {
		found = true;
		break;
	    }
	}
	if (found) {
	    return segmentnr;
	} else {
	    segmentnr = 666;
	    return segmentnr;
	}
    }

    //Set Anzahl an Leerzeichen
    private String leerfuellen(int anzahl) {
	String leerzeichen = "";
	for (int i = 0; i <= anzahl; i++) {
	    leerzeichen = leerzeichen + " ";
	}
	return leerzeichen;
    }
    
    // Set segment name passed as String into specified place
    // mesgStruktur-Array.
    private void setMesgStruktSegment(String segname, int reihenfolge) {
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
	   int neuereihenfolge =  this.priv_meth_newsegnumber(
		    "So viele Segmente gibt es in der Struktur nicht. Bitte geben Sie eine Zahl kleiner " + currsize
			    + " ein.");
	    this.setMesgStruktSegment(segname, neuereihenfolge);
	} else if (reihenfolge == 0) {
	    reihenfolge = reihenfolge + 1;
	}
	
	//Prüfen ob Segment bereits enthalten ist.
	int segnr = this.getSegmentnr(segname);
	if(segnr == 666) {
	    // Segment in die Struktur hinzufügen und Struktur returnen.
	    this.mesgStruktur.add(reihenfolge, segname);
	} else {
	    //Segment ist bereits in der Struktur.
	    JOptionPane.showMessageDialog(null, "Dieses Segment gibt es bereits. Das Segment ist an Stelle " + segnr + "." );
	}
    }
    
    //Set content into mesg ArrayList at correct location. If segment is not yet in structure it is added.
    private void setMesgSegmentContent(String segname, String segcontent) {
	    int segnr = this.getSegmentnr(segname);
	    if (segnr == 666) {
		this.setMesgStruktSegment(segname, segnr);
		this.setMesgSegmentContent(segname, segcontent);
	    }
	    this.mesg.add(segnr, segcontent);	
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

    //Gibt die Länge des Segments an
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
		JOptionPane.showMessageDialog(null, "Dieses Segment gibt es nicht.");
		break;
	}
	return segLaenge;
    }

    //Gibt die Gesamtlänge aller Segmente in der Nachricht wieder.
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
