package kranchie.java.dateien;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import kranchie.java.customExceptions.CustomUnchecked;

public class Datei {

	/*-----------*/
	/* Attribute */
	/*-----------*/
	private String dateiName, speicherPfad, zeilenInhalt, dateiEndung, absolutePath;
	private ArrayList<String> inhaltLesen = new ArrayList<String>(), inhaltSchreiben = new ArrayList<String>();
	private boolean nooverwrite, validpath = false, validname = false, validending = false, validfullname = false,
			validfullpath = false;
	private CustomUnchecked fehler = new CustomUnchecked("dateien");

	/*-------------*/
	/* Constructor */
	/*-------------*/

	public Datei() throws CustomUnchecked {
		boolean exist;
		int dialogButton = JOptionPane.YES_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Sollte die Datei bereits existieren?",
				"Datei existiert?", dialogButton);
		if (dialogResult == dialogButton) {
			exist = true;
		} else {
			exist = false;
		}
		this.setSpeicherPfad(this.getPath());
		this.setDateiName(this.getFile(), exist);
		this.nooverwrite = true;
	}

	public Datei(boolean exist) throws CustomUnchecked {
		this.setSpeicherPfad(this.getPath());
		this.setDateiName(this.getFile(), exist);
		this.nooverwrite = true;
	}

	public Datei(String dateiName, String speicherPfad, boolean exist) throws CustomUnchecked {
		super();
		this.setDateiName(dateiName, exist);
		this.setSpeicherPfad(speicherPfad);
		this.nooverwrite = true;
	}

	public Datei(String dateiName, String speicherPfad, boolean nooverwrite, boolean exist) throws CustomUnchecked {
		super();
		this.setDateiName(dateiName, exist);
		this.setSpeicherPfad(speicherPfad);
		this.nooverwrite = nooverwrite;
	}

	/*-------------------*/
	/* Getters + Setters */
	/*-------------------*/

	public String getZeilenInhalt(int zeile) {
		this.zeilenInhalt = inhaltLesen.get(zeile - 1);
		return this.zeilenInhalt;
	}

	public ArrayList<String> getInhaltLesen() {
		return inhaltLesen;
	}

	public String getDateiName() {
		return this.dateiName;
	}

	public String getdateieEndung() {
		return this.dateiEndung;
	}

	public String getSpeicherPfad() {
		return speicherPfad;
	}

	public String getabsolutePath() {
		return this.absolutePath;
	}

	public void setDateiName(String dateiName, boolean exist) {
		if (this.validending || (this.validending && this.validpath)) {
			this.dateiName = dateiName + "." + this.dateiEndung;
			this.validname = true;
			this.validfullname = true;
			try {
				this.setabsolutePath(exist);
			} catch (CustomUnchecked e) {

			}
		} else {
			this.dateiName = dateiName;
			this.validname = true;
			this.validfullname = false;
		}

	}

	public void setdateiEndung(String endung, boolean exist) throws CustomUnchecked {
		this.dateiEndung = endung;
		this.validending = true;
		if (validname || (validname && validpath)) {
			this.setDateiName(this.dateiName, exist);
		} else if (validfullname) {
			throw new CustomUnchecked("Es gibt bereits eine Dateiendung (" + this.dateiEndung
					+ ") es kann daher keine Dateiendung gesetzt werden.");
		}
	}

	public void setSpeicherPfad(String speicherPfad) throws CustomUnchecked {
		validpath = this.checkname_validpath(speicherPfad);
		if (validpath) {
			boolean writable = this.checkname_canwrite(speicherPfad);
			if (writable) {
				this.speicherPfad = speicherPfad;
				this.validpath = true;
			} else {
				fehler.setFehler(4);
				throw fehler;
			}
		} else {
			fehler.setFehler(5);
			throw fehler;
		}
	}

	private boolean setabsolutePath(boolean shouldexist) throws CustomUnchecked {
		String abpath = this.speicherPfad + File.separator + this.dateiName;
		if (this.validfullname && this.validpath) {
			boolean validpath = this.checkname_validpath(this.speicherPfad);
			boolean canwrite = this.checkname_canwrite(this.speicherPfad);
			boolean fileexist = this.checkname_fileexist(this.speicherPfad, this.dateiName);
			if (!canwrite) {
				fehler.setFehler(4);
				throw fehler;
			} else if (!validpath) {
				fehler.setFehler(5);
				throw fehler;
			} else if (fileexist && !shouldexist) {
				this.setnooverwrite(this.priv_meth_ueberschreib());
				if (nooverwrite) {
					fehler.setFehler(6);
					throw fehler;
				} else {
					this.absolutePath = abpath;
					this.validfullpath = true;
					return true;
				}
			} else {
				this.absolutePath = abpath;
				this.validfullpath = true;
				return true;
			}
		} else if (this.validpath && this.validname && !this.validfullname) {
			fehler.setFehler(1);
			throw fehler;
		} else if (this.validname && !this.validpath && !this.validfullname) {
			fehler.setFehler(2);
			throw fehler;
		} else if (this.validending && this.validfullname && !this.validpath) {
			fehler.setFehler(3);
			throw fehler;
		} else {
			fehler.setFehler(0);
			throw fehler;
		}
	}

	private void setnooverwrite(boolean a) {
		this.nooverwrite = a;
	}

	public void setInhaltSchreiben(ArrayList<String> inhaltSchreiben) {
		this.inhaltSchreiben = inhaltSchreiben;
	}

	private String getPath() throws CustomUnchecked {
		String path = null;
		CustomUnchecked cu = new CustomUnchecked("dateien");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File selFile = fc.getSelectedFile();
			path = selFile.getPath();
		} else {
			cu.setFehler(7);
			throw cu;
		}
		this.setSpeicherPfad(path);
		return path;
	}

	private String getFile() throws CustomUnchecked {
		String file = null;
		CustomUnchecked cu = new CustomUnchecked("dateien");
			JFileChooser fc = new JFileChooser(this.getSpeicherPfad());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File selFile = fc.getSelectedFile();
			file = selFile.getName();
		} else {
			cu.setFehler(8);
			throw cu;
		}
		this.setDateiName(file, true);
		return file;
	}

	@SuppressWarnings("unused")
	private static void clearClipboard() {
		StringSelection stringSelection = new StringSelection("");
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	@SuppressWarnings("unused")
	private static void setClipboard(String content) {
		StringSelection stringSelection = new StringSelection(content);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	/*-----------------*/
	/* EinleseMethoden */
	/*-----------------*/

	/*
	 * Methode zum Einlesen der Datei. Falls die ganze Datei eingelesen werden soll
	 * muss die Zeilenanzahl 0 sein.
	 */
	public ArrayList<String> pub_einlesen(int zeile) throws IOException, CustomUnchecked, IndexOutOfBoundsException {
		if (validfullpath) {
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
		} else {
			throw new CustomUnchecked("Es wurde noch keine zulesende Datei festgelegt. (validfullpath missing!)");
		}
	}// End of Method

	/*
	 * Overloading Methode, falls die gesamte Datei gelesen werden soll, wird die
	 * Anzahl der Zeilen automatisch auf 0 gesetzt und muss nicht mitgegeben werden.
	 */
	public ArrayList<String> pub_einlesen() throws IOException, CustomUnchecked, IndexOutOfBoundsException {
		try {
			ArrayList<String> liste = pub_einlesen(0);
			return liste;
		} catch (IndexOutOfBoundsException ob) {
			throw new IndexOutOfBoundsException(ob.getMessage());
		} catch (CustomUnchecked cu) {
			throw new CustomUnchecked(cu.getMessage());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}

	}// End of Method

	/*-----------------*/
	/* SchreibMethoden */
	/*-----------------*/

	public boolean pub_pruefSchreib() throws IOException {
		boolean nameexist, newfilename, uebschreib, written;
		int zaehler = 0;
		nameexist = this.checkname_fileexist(); // Prüfen ob
		// Datei unter dem Pfad bereits existiert.
		if (nameexist) {
			uebschreib = this.priv_meth_ueberschreib();
			while (nameexist && !uebschreib) {
				if (zaehler > 0) {
					JOptionPane.showMessageDialog(null, "Die Datei (" + this.dateiName + ") existiert bereits!");
				}
				zaehler = zaehler + 1;
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
			 * Der zweite Parameter von Filewriter bestimmt, ob bei einer bestehenden Datei,
			 * die Datei überschrieben wird (false), oder der Inhalt appended wird (true).
			 */
			FileWriter writer = new FileWriter(this.absolutePath, this.nooverwrite);
			int size = this.inhaltSchreiben.size();
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
		File datei = new File(this.absolutePath);
		boolean fexists = datei.exists();
		if (fexists) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkname_fileexist(String path, String filename) {
		File datei = new File(path + File.separator + filename);
		boolean fexists = datei.exists();
		if (fexists) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkname_validpath(String path) {
		File datei = new File(path);
		boolean fexists = datei.exists();
		if (fexists) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkname_canwrite(String path) {
		File datei = new File(path);
		boolean fwrite = datei.canWrite();
		if (fwrite) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkname_canwrite(String path, String filename) {
		File datei = new File(path + File.pathSeparator + filename);
		boolean fwrite = datei.canWrite();
		if (fwrite) {
			return true;
		} else {
			return false;
		}
	}

	// true => Neuer Name wurde vergeben. false => Kein neuer Name wurde
	// vergeben, nicht möglich.

	private boolean priv_meth_newname() {
		boolean namechanged;
		String antwort;
		do {
			antwort = JOptionPane.showInputDialog(null, "Bitte geben Sie einen neuen Dateinamen an:");
			if (antwort.length() == 0) {
				JOptionPane.showMessageDialog(null, "Das Textfeld war leer!");
			}
		} while (antwort.length() == 0);
		this.setDateiName(antwort, false);
		namechanged = true;
		return namechanged;
	}

	// true => die existierende Datei soll überschrieben werden.

	private boolean priv_meth_ueberschreib() {
		boolean ueberschreib = false;
		int dialogButton = JOptionPane.YES_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null,
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