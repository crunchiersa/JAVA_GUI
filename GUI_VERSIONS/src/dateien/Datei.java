package dateien;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class Datei {
	
/*--------------------------------------*/
/*				Attribute				*/ 
/*--------------------------------------*/
		private String dateiName;
		private String speicherPfad;
		private String zeilenInhalt;
		private ArrayList<String> inhaltLesen, inhaltSchreiben;
		private String dateiEndung;
		private String absolutePath;
		private boolean nooverwrite; // Ob Datei überschrieben oder appended werden soll.

/*--------------------------------------*/
/*			Getters + Setters			*/ 
/*--------------------------------------*/
		
		public String getDateiName() {
			return dateiName;
		}

		public void setDateiName(String dateiName) {
			this.dateiName = dateiName + this.dateiEndung;
			this.setabsolutePath();
		}
		
		public void setdateiEndung(String endung) {
			this.dateiEndung = endung;
			this.dateiName = this.dateiName + this.dateiEndung;
			this.setabsolutePath();
		}
		
		public String getdateieEndung() {
			return dateiEndung;
		}
		
		private void setabsolutePath() {
			this.absolutePath = this.speicherPfad + File.separator + this.dateiName;
		}
		
		private void setnooverwrite(boolean a) {
			this.nooverwrite = a;
		}
		public String getSpeicherPfad() {
			return speicherPfad;
		}

		public void setSpeicherPfad(String speicherPfad) {
			this.speicherPfad = speicherPfad;
			this.setabsolutePath();
		}

		public String getZeilenInhalt(int zeile) {
			this.zeilenInhalt = inhaltLesen.get(zeile -1);
			return this.zeilenInhalt;
		}

		public ArrayList<String> getInhaltLesen() {
			return inhaltLesen;
		}

		public void setInhaltSchreiben(ArrayList<String> inhaltSchreiben) {
			this.inhaltSchreiben = inhaltSchreiben;
		}
		
		private static void clearClipboard (){
			StringSelection stringSelection = new StringSelection("");
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}

		private static void setClipboard ( String content ){
			StringSelection stringSelection = new StringSelection(content);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}

		/* Constructor*/
		public Datei () {
			this.dateiName = JOptionPane.showInputDialog(null, "Bitte geben Sie den Dateinamen ein:");
			this.speicherPfad = JOptionPane.showInputDialog(null, "Bitte geben Sie den Speicherpfad an:");
			this.nooverwrite = true;
			this.setabsolutePath();
		}
		public Datei(String dateiName, String speicherPfad) {
			super();
			this.dateiName = dateiName;
			this.speicherPfad = speicherPfad;
			this.nooverwrite = true;
			this.setabsolutePath();
		}
	
/*--------------------------------------*/
/*			EinleseMethoden				*/ 
/*--------------------------------------*/	
		
/* Methode zum Einlesen der Datei. Falls die ganze Datei eingelesen werden soll muss die Zeilenanzahl 0 sein.*/
		@SuppressWarnings("unused")
		public ArrayList<String> pub_einlesen (int zeile) throws IOException {
			try {
				Scanner s = new Scanner(new File(this.absolutePath));
				this.inhaltLesen = new ArrayList<String>();
				if (zeile > 0) {
					for (int i=0;i<zeile;i++) {
						this.inhaltLesen.add(s.nextLine());
					}
				} else { 
					while (s.hasNextLine()){
						this.inhaltLesen.add(s.nextLine());
						}
					}
				s.close(); 		
				return this.inhaltLesen;
				} catch (IndexOutOfBoundsException e) {
					ArrayList<String> error = new ArrayList<String>();
					String errormesg = e.getClass() + " " + e.getMessage();
					error.add(errormesg);
					return error;
				}
			}//End of Method
		
		
/* Overloading Methode, falls die gesamte Datei gelesen werden soll, wird die Anzahl der Zeilen
* automatisch auf 0 gesetzt und muss nicht mitgegeben werden.*/
		public ArrayList<String> pub_einlesen () throws IOException {
			ArrayList<String> liste = pub_einlesen (0);
			return liste;	
		}//End of Method

/*--------------------------------------*/
/*			SchreibMethoden				*/ 
/*--------------------------------------*/	
		
		@SuppressWarnings("unused")
		public void pub_pruefSchreib() throws IOException {
			boolean nameexist, newfilename, uebschreib, written;
			int zaehler = 0;
			nameexist = this.priv_meth_checkname(); //Prüfen ob Datei unter dem Pfad bereits existiert.
			if (nameexist) {
				uebschreib = this.priv_meth_ueberschreib();
				while (nameexist && !uebschreib) {
					if (zaehler > 0) {
						JOptionPane.showMessageDialog(null, "Die Datei (" + this.dateiName + ") existiert bereits!");
					}
					zaehler = zaehler + 1;
					newfilename = this.priv_meth_newname();
					if (newfilename) {
						nameexist = this.priv_meth_checkname();						
					}
				}
			}
			written = this.priv_meth_schreiben();
		}
		
/*Write a passed ArrayList<String> into a file at location passed in parameters.
 * status == true --> Der Schreibvorgang ist erfolgt.
 * status == false --> Es ist ein Fehler aufgetreten.*/
		@SuppressWarnings("unused")
		private boolean priv_meth_schreiben () throws IOException {
			boolean status;
			// write the content in file 
			try {
				/*Der zweite Parameter von Filewriter bestimmt, ob bei einer bestehenden Datei,
				 *  die Datei überschrieben wird (false), oder der Inhalt appended wird (true). */
				FileWriter writer = new FileWriter(this.absolutePath, this.nooverwrite); 
				int size = this.inhaltSchreiben.size();
				for (int i=0;i<size;i++) {
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
		}//End of Method
		

/*--------------------------------------*/
/*			PruefMethoden				*/ 
/*--------------------------------------*/

		// true => Datei existiert unter diesem Pfad schon. false => Datei existiert unter diesem Pfad nicht.
		@SuppressWarnings("unused")
		private boolean priv_meth_checkname() {
			File datei = new File(this.absolutePath);
			boolean fexists = datei.exists();
			boolean vorhanden;
			
			if (fexists) {
				vorhanden = true;
			} else {
				vorhanden = false;
			}
			return vorhanden;
		}
		
		// true => Neuer Name wurde vergeben. false => Kein neuer Name wurde vergeben, nicht möglich.
		@SuppressWarnings("unused")
		private boolean priv_meth_newname() {
			boolean namechanged, eingegeben;
			String antwort;
			do {
				antwort = JOptionPane.showInputDialog(null, "Bitte geben Sie einen neuen Dateinamen an:");
				if (antwort.length() == 0) {
					JOptionPane.showMessageDialog(null, "Das Textfeld war leer!");
				}
			}while (antwort.length() == 0);
			this.setDateiName(antwort);
			namechanged = true;
			return namechanged;
		}
		
		// true => die existierende Datei soll überschrieben werden.
		@SuppressWarnings("unused")
		private boolean priv_meth_ueberschreib() {
			boolean ueberschreib = false;
			int dialogButton = JOptionPane.YES_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Der Dateiname existiert bereits. Möchten Sie die Datei überschreiben? ACHTUNG: Wenn Sie mit Ja antworten wird die bestehende Datei überschrieben! Es kann zu Datenverlust kommen!", "Überschreiben?", dialogButton);
			if (dialogResult == dialogButton) {
				setnooverwrite(false);
				ueberschreib = true;
			} else {
				ueberschreib = false;
			}
			return ueberschreib;
		}

}
