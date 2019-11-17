package kranchie.java.woerter;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DiceWordPasswort extends Wort {

    private String	 url;
    private String	 ip;
    private String	 port;
    private String	 database;
    private final String drivercomp = "jdbc:mysql:";
    private final String driver	    = "com.mysql.cj.jdbc.Driver";
    private final String timezone   = "?serverTimezone=UTC";	 // If Timeformat on server differs from UTC timestamps
								 // will be read incorrectly!
    private String	 userName;
    private String	 password;
    private String	 queryresult;
    private String	 connectionerror;
    private int		 iteration;
    private SecureRandom secrnd	    = new SecureRandom();

    /* Constructor */
    public DiceWordPasswort(String ip, String port, String database, String userName, String password) {
	super();
	this.ip	      = ip;
	this.port     = port;
	this.database = database;
	this.userName = userName;
	this.password = password;
	this.setUrl();
    }

    public DiceWordPasswort(String username, String password) {
	super();
	this.ip	      = "10.10.37.58"; // Should be adjusted to your local Settings.
	this.port     = "3306";	       // Should be adjusted to your local Settings.
	this.database = "diceword";    // Should be adjusted to your local Settings.
	this.userName = username;
	this.password = password;
	this.setUrl();
    }

    public String getIp() {
	return this.ip;
    }

    public void setIp(String ip) {
	this.ip = ip;
	this.setUrl();
    }

    public String getPort() {
	return this.port;
    }

    public void setPort(String port) {
	this.port = port;
	this.setUrl();
    }

    public String getDatabase() {
	return this.database;
    }

    public void setDatabase(String database) {
	this.database = database;
	this.setUrl();
    }

    public String getDrivercomp() {
	return this.drivercomp;
    }

    private void setUrl(String drivercomp, String ip, String port, String database) {
	this.url = drivercomp + "//" + ip + ":" + port + "/" + database + this.timezone;
    }

    private void setUrl() {
	this.url = this.drivercomp + "//" + this.ip + ":" + this.port + "/" + this.database + this.timezone;
    }

    public void setUserName(String userName) {
	this.userName = userName;
	this.setUrl();
    }

    public void setPassword(String password) {
	this.password = password;
	this.setUrl();
    }

    public String getQueryresult() {
	return this.queryresult;
    }

    public String getConnectionerror() {
	return this.connectionerror;
    }

    // Erstellt eine Zahl für die Datenbankabfrage für das DiceWord.
    private String diceWordNumber() {
	String complete_number;
	complete_number = "";
	for (int i = 1; i <= 5; i++) {
	    iteration	    = secrnd.nextInt(7 - 1) + 1;
	    complete_number = complete_number + iteration;
	}
	return complete_number;
    }

    // Erstellt ein DiceWord-Passwort in der gewünschten Sprache mit der Anzahl
    // von Wörtern.
    // Eingabemöglichkeiten Sprache: german, english,french,spanish,eff.
    public String genDWpasswd(String sprache, int anzahlwoerter) {
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
	    JOptionPane.showMessageDialog(null, "Die Sprache " + sprache + " ist nicht verfügbar.");
	}
	DiceWordPasswort.setClipboard(this.auslesen());
	return this.auslesen();
    }
    
    // Overloaded Method possibility to choose language and number of words to
    // use.
    public String genDWpasswd(int anzahl) throws IOException {
	String sprache;
	int    language	     = DiceWordPasswort.getInput(1);
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
		JOptionPane.showMessageDialog(null, "Die Sprache " + language + " ist nicht verfügbar.");
		sprache = "";
		break;
	}
	this.genDWpasswd(sprache, anzahl);
	DiceWordPasswort.setClipboard(this.auslesen());
	return this.auslesen();
    }
    
    // Overloaded Method possibility to choose language and number of words to
    // use.
    public String genDWpasswd() throws IOException {
	String sprache;
	int    language	     = DiceWordPasswort.getInput(1);
	int    anzahlwoerter = DiceWordPasswort.getInput(2);
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
		JOptionPane.showMessageDialog(null, "Die Sprache " + language + " ist nicht verfügbar.");
		sprache = "";
		break;
	}
	this.genDWpasswd(sprache, anzahlwoerter);
	DiceWordPasswort.setClipboard(this.auslesen());
	return this.auslesen();
    }

    // Abfrage welche Art von Passwort erstellt werden soll.
    public static int getInput(int auswahl) throws IOException {
	int	 choice	 = 0;
	boolean	 more	 = true;
	String	 eingabe = "";
	String[] options = { "Deutsch", "Englisch", "Französisch", "Spanisch", "Secure" };
	do {
	    while (more == true) {
		switch (auswahl) {
		    case (1):
			int sprache = JOptionPane.showOptionDialog(null, "Welche Sprache soll das Kennwort haben? ",
				"Sprachwahl", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
			eingabe = String.valueOf(sprache);// Bei der vorherigen Abfrage werden die Ergebnisse als int 0
							  // - 4 zurückgegeben, abhängig von der Stelle im Array
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
		choice = DiceWordPasswort.isInteger(eingabe);
		if (choice == 99) {
		    JOptionPane.showMessageDialog(null, "Keine gültige Zahl!");
		    DiceWordPasswort.getInput(2);
		} else {
		    if (auswahl == 2 && choice >= 10) {
			String optionen[] = { "Ja", "Nein" };
			int    response	  = JOptionPane.showOptionDialog(null,
				"Sicher, dass das Passwort soviele Worte beinhalten soll?", "Sicher?",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionen,
				optionen[0]);
			if (response == 1) {
			    int answer = DiceWordPasswort.getInput(2);
			    choice = answer;
			}
		    }
		    more = false;
		}
	    }
	} while (more == true);
	return choice;
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

    // Variables SELECT
    public String retrieveDB(String select, String table, String column, String searchpattern, String ip, String port,
	    String database, String username, String password) {
	try {
	    Class.forName(this.driver);
	    this.setUrl(this.drivercomp, ip, port, database);
	    Connection conn  = DriverManager.getConnection(this.url, username, password);
	    Statement  st    = conn.createStatement();
	    String     query = "Select " + select + " FROM " + table + " Where " + column + " = " + searchpattern;
	    ResultSet  res   = st.executeQuery(query);
	    while (res.next()) {
		String msg = res.getString(select);
		this.queryresult = "" + msg;
	    }
	    conn.close();
	} catch (Exception e) {
	    connectionerror = e.getMessage();
	    return connectionerror;
	}
	return queryresult;
    }

    // Diceword
    public String retrieveDiceWordfromDatabase(String zuordnung, String column) {
	try {
	    Class.forName(driver);
	    this.setUrl();
	    Connection conn  = DriverManager.getConnection(url, userName, password);
	    Statement  st    = conn.createStatement();
	    String     query = "SELECT " + column + " FROM diceWord WHERE Wurf = " + zuordnung;
	    ResultSet  res   = st.executeQuery(query);
	    while (res.next()) {
		String msg = res.getString(column);
		this.queryresult = "" + msg;
	    }
	    conn.close();
	} catch (Exception e) {
	    connectionerror = e.getMessage();
	    return connectionerror;
	}
	return queryresult;
    }
}
