package kranchie.java.woerter;

import java.io.IOException;
import java.security.SecureRandom;
import javax.swing.JOptionPane;

public abstract class DiceWord extends Word {
    
    private int		 iteration;
    private SecureRandom secrnd	    = new SecureRandom();
    
    // Erstellt eine Zahl f�r die Datenbankabfrage f�r das DiceWord.
    private String diceWordNumber() {
	String complete_number;
	complete_number = "";
	for (int i = 1; i <= 5; i++) {
	    iteration	    = secrnd.nextInt(7 - 1) + 1;
	    complete_number = complete_number + iteration;
	}
	return complete_number;
    }
    
    // If choice is 99 --> String cannot be parsed into Integer.
    public static int isInteger(String eingabe) {
	int choice;
	try {
	    choice = Integer.parseInt(eingabe);
	} catch (NumberFormatException nf) {
	    choice = 99;
	}
	return choice;
    }
    
    // Erstellt ein DiceWord-Passwort in der gew�nschten Sprache mit der Anzahl
    // von W�rtern.
    // Eingabem�glichkeiten Sprache: german, english,french,spanish,eff.
    public String genDWpasswd(String sprache, int anzahlwoerter) throws IOException {
	if (anzahlwoerter >= 10) {
	    String optionen[] = { "Ja", "Nein" };
	    int	   response   = JOptionPane.showOptionDialog(null,
		    "Sicher, dass das Passwort soviele Worte beinhalten soll?", "Sicher?", JOptionPane.DEFAULT_OPTION,
		    JOptionPane.INFORMATION_MESSAGE, null, optionen, optionen[0]);
	    if (response == 1) {
		int answer = DiceWord_mysql.getInput(2);
		anzahlwoerter = answer;
	    }
	}
	String password	= "", reply = "", output = "";
	int    b	= 1;
	if (sprache.equals("german") || sprache.equals("english") || sprache.equals("french")
		|| sprache.equals("spanish") || sprache.equals("eff")) {
	    for (int i = 0; i < anzahlwoerter; i++) {
		String wurfnummer = this.diceWordNumber();
		switch (sprache) {
		    case ("german"):
			reply = this.retrieveDiceWordfromDatabase(wurfnummer, "Wort");
			break;
		    case ("english"):
			reply = this.retrieveDiceWordfromDatabase(wurfnummer, "Word");
			break;
		    case ("french"):
			reply = this.retrieveDiceWordfromDatabase(wurfnummer, "Mot");
			break;
		    case ("spanish"):
			reply = this.retrieveDiceWordfromDatabase(wurfnummer, "Palabra");
			break;
		    case ("eff"):
			reply = this.retrieveDiceWordfromDatabase(wurfnummer, "EFF");
			break;
		    default:
			break;
		}
		if (b > 1) {
		    output = reply + "-" + output;
		} else {
		    output = reply;
		}
		b++;
		password = output;
		this.eingeben(password);
	    }
	    while (b <= anzahlwoerter)
		;
	} else {
	    JOptionPane.showMessageDialog(null, "Die Sprache " + sprache + " ist nicht verf�gbar.");
	}
	DiceWord_mysql.setClipboard(this.auslesen());
	return this.auslesen();
    }
    
    // Overloaded Method possibility to choose language and number of words to
    // use.
    public String genDWpasswd(int anzahl) throws IOException {
	String sprache;
	if (anzahl >= 10) {
	    String optionen[] = { "Ja", "Nein" };
	    int	   response   = JOptionPane.showOptionDialog(null,
		    "Sicher, dass das Passwort soviele Worte beinhalten soll?", "Sicher?", JOptionPane.DEFAULT_OPTION,
		    JOptionPane.INFORMATION_MESSAGE, null, optionen, optionen[0]);
	    if (response == 1) {
		int answer = DiceWord_mysql.getInput(2);
		anzahl = answer;
	    }
	}
	int    language	= DiceWord_mysql.getInput(1);
	switch (language) {
	    case (0):
		sprache = "german";
		break;
	    case (1):
		sprache = "english";
		break;
	    case (2):
		sprache = "french";
		break;
	    case (3):
		sprache = "spanish";
		break;
	    case (4):
		sprache = "eff";
		break;
	    default:
		JOptionPane.showMessageDialog(null, "Die Sprache " + language + " ist nicht verf�gbar.");
		sprache = "";
		break;
	}
	this.genDWpasswd(sprache, anzahl);
	DiceWord_mysql.setClipboard(this.auslesen());
	return this.auslesen();
    }
    
 // Overloaded Method possibility to choose language and number of words to
    // use.
    public String genDWpasswd() throws IOException {
	String sprache;
	int    language	     = DiceWord_mysql.getInput(1);
	int    anzahlwoerter = DiceWord_mysql.getInput(2);
	switch (language) {
	    case (0):
		sprache = "german";
		break;
	    case (1):
		sprache = "english";
		break;
	    case (2):
		sprache = "french";
		break;
	    case (3):
		sprache = "spanish";
		break;
	    case (4):
		sprache = "eff";
		break;
	    default:
		JOptionPane.showMessageDialog(null, "Die Sprache " + language + " ist nicht verf�gbar.");
		sprache = "";
		break;
	}
	this.genDWpasswd(sprache, anzahlwoerter);
	DiceWord_mysql.setClipboard(this.auslesen());
	return this.auslesen();
    }
    
    // Abfrage welche Art von Passwort erstellt werden soll.
    public static int getInput(int auswahl) throws IOException {
	int	 choice	 = 0;
	boolean	 more	 = true;
	String	 eingabe = "";
	String[] options = { "Deutsch", "Englisch", "Franz�sisch", "Spanisch", "Secure" };
	do {
	    while (more == true) {
		switch (auswahl) {
		    case (1):
			int sprache = JOptionPane.showOptionDialog(null, "Welche Sprache soll das Kennwort haben? ",
				"Sprachwahl", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
			eingabe = String.valueOf(sprache);// Bei der vorherigen Abfrage werden die Ergebnisse als int 0
							  // - 4 zur�ckgegeben, abh�ngig von der Stelle im Array
							  // options[].
			break;
		    case (2):
			eingabe = JOptionPane.showInputDialog("Wie viele Dice-Worte soll Ihr neues Passwort haben?");
			break;
		    case (3):
			eingabe = JOptionPane.showInputDialog("Wie viele Zeichen soll das Passwort haben? ");
			break;
		}
		// eingabe is parsed to Integer. Handling if eingabe is not a number.
		choice = DiceWord_mysql.isInteger(eingabe);
		if (choice == 99) {
		    JOptionPane.showMessageDialog(null, "Keine g�ltige Zahl!");
		    DiceWord_mysql.getInput(2);
		} else {
		    if (auswahl == 2 && choice >= 10) {
			String optionen[] = { "Ja", "Nein" };
			int    response	  = JOptionPane.showOptionDialog(null,
				"Sicher, dass das Passwort soviele Worte beinhalten soll?", "Sicher?",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionen,
				optionen[0]);
			if (response == 1) {
			    int answer = DiceWord_mysql.getInput(2);
			    choice = answer;
			}
		    }
		    more = false;
		}
	    }
	} while (more == true);
	return choice;
    }
    
    public abstract String retrieveDiceWordfromDatabase(String zuordnung, String column);
}
