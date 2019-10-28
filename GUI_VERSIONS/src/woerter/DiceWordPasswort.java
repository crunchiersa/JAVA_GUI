package woerter;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DiceWordPasswort extends Wort {

	    private String url;
	    private String ip;
	    private String port;
	    private String database;
	    private final String drivercomp = "jdbc:mysql:";
	    private final String driver = "com.mysql.cj.jdbc.Driver";
	    private final String timezone = "?serverTimezone=UTC";
	    private String userName; 
	    private String password;
	    private String queryresult;
	    private String connectionerror;
		private int iteration;
		private SecureRandom secrnd = new SecureRandom();
		
	    /*Constructor*/
	    public DiceWordPasswort(String ip, String port, String database, String userName,
				String password) {
			super();
			this.ip = ip;
			this.port = port;
			this.database = database;
			this.userName = userName;
			this.password = password;
			this.setUrl();
		}
	    
	    public DiceWordPasswort(String username, String password) {
	    	super();
			this.ip = "10.10.37.58"; //Should be adjusted to your local Settings.
			this.port = "3306"; //Should be adjusted to your local Settings.
			this.database = "diceword"; //Should be adjusted to your local Settings.
			this.userName = username;
			this.password = password;
			this.setUrl();
	    }
	    
	    public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
			this.setUrl();
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
			this.setUrl();
		}

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
			this.setUrl();
		}

		public String getDrivercomp() {
			return drivercomp;
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
		return queryresult;
	    }

	    public String getConnectionerror() {
		return connectionerror;
	    }
	    
		//Erstellt eine Zahl f�r die Datenbankabfrage f�r das DiceWord.
		private String diceWordNumber(){
			String complete_number;
			complete_number = "";
			for ( int i = 1; i <= 5; i++){
			    iteration = secrnd.nextInt(7-1)+1;
			    complete_number = complete_number + iteration;
			}
			return complete_number;
		    }
		
		//Erstellt ein DiceWord-Passwort in der gew�nschten Sprache mit der Anzahl von W�rtern.
		//Eingabem�glichkeiten Sprache: german, english,french,spanish,eff.
		public String genDWpasswd(String sprache, int anzahlwoerter) {
			String password ="", reply = "", output = "";
			int b = 1;
			if (sprache.equals("german") || sprache.equals("english") || sprache.equals("french") || sprache.equals("spanish") || sprache.equals("eff")) {
				for (int i = 0; i < anzahlwoerter; i++) {
					String wurfnummer = this.diceWordNumber();
					switch(sprache) {
					case ("german"):	reply = this.retrieveGermanDiceWordfromDatabase(wurfnummer);
					break;
					case ("english"):	reply = this.retrieveDiceWordfromDatabase(wurfnummer);
					break; 
					case ("french"):	reply = this.retrieveFrenchDiceWordfromDatabase(wurfnummer);
					break; 
					case ("spanish"):	reply = this.retrieveGermanDiceWordfromDatabase(wurfnummer);
					break; 
					case ("eff"):		reply = this.retrieveGermanDiceWordfromDatabase(wurfnummer);
					break; 
					default:			
					break;
					}
					if (b > 1){
						output = reply + "-" + output;
					}else{
						output = reply;
					}
					b++;
					password = output;
					this.eingeben(password);
				}while(b <= anzahlwoerter);
			} else { 
				JOptionPane.showMessageDialog(null, "Die Sprache " + sprache + " ist nicht verf�gbar.");
			}
			DiceWordPasswort.setClipboard(this.auslesen());
			return this.auslesen();
		}
		
		//Overloaded Method possibility to choose language and number of words to use.
		public String genDWpasswd() throws IOException{
			String sprache;
			int b = 1;
			int language = DiceWordPasswort.getInput(2);
			int anzahlwoerter = DiceWordPasswort.getInput(3);
			switch (language) {
			case (0): 	sprache = "german";
			break;
			case (1): 	sprache = "english";
			break;
			case (2): 	sprache = "french";
			break;
			case (3): 	sprache = "spanish";
			break;
			case (4): 	sprache = "eff";
			break;
			default:	JOptionPane.showMessageDialog(null, "Die Sprache " + language + " ist nicht verf�gbar.");
						sprache = "";
			break;
			}
			this.genDWpasswd(sprache, anzahlwoerter);
			DiceWordPasswort.setClipboard(this.auslesen());
			return this.auslesen();
		}
		
		//Abfrage welche Art von Passwort erstellt werden soll.
	    public static int getInput(int auswahl) throws IOException { 
		int choice = 0;
		boolean more = true;
		String eingabe = "";
		String[] options = {"Deutsch", "Englisch", "Franz�sisch", "Spanisch", "Secure"};
		do {
		    while (more == true){
			switch(auswahl){
			case (1):	eingabe = JOptionPane.showInputDialog("M�chten Sie ein englisches Dice-Password(1), deutsches Dice-Password(2) oder ein zuf�llig erstelltes Passwort(3) generieren?");
			break;
			case (2):	int sprache  = JOptionPane.showOptionDialog(null, "Welche Sprache soll das Kennwort haben? ", "Sprachwahl", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); 
						eingabe = String.valueOf(sprache);//Bei der vorherigen Abfrage werden die Ergebnisse als int 0 - 4 zur�ckgegeben, abh�ngig von der Stelle im Array options[].
			break;
			case (3):	eingabe = JOptionPane.showInputDialog("Wie viele Dice-Worte soll Ihr neues Passwort haben?");
			break;
			case (4):	eingabe = JOptionPane.showInputDialog("Wie viele Zeichen soll das Passwort haben? ");
			break;
			case (5):	eingabe = JOptionPane.showInputDialog("Wie viele Zahlen sollen generiert werden? ");
			break;
			}
			try {
			    choice = Integer.parseInt(eingabe);
			    more = false;
			}catch (NumberFormatException nf){
			    JOptionPane.showMessageDialog(null, "Keine g�ltige Zahl!");
			}
		   }
		}while (more == true);
		return choice;
	    }
	    
		//Variables SELECT
	    public String retrieveDB(String select, String table, String column, String searchpattern, String ip, String port, String database, String username, String password) {
	    	try{
	    		Class.forName(this.driver);
	    		this.setUrl(this.drivercomp, ip, port, database);
	    		Connection conn = DriverManager.getConnection(this.url, username, password);
	    		Statement st = conn.createStatement();
	    		String query = "Select " + select + " FROM " + table + " Where " + column + " = " + searchpattern;
	    		ResultSet res = st.executeQuery(query);
	    		while(res.next()) {
	    			String msg = res.getString(select);
	    			this.queryresult = "" + msg;	
	    		}
	    		conn.close();
	    	}catch (Exception e) {    
	    		connectionerror = e.getMessage();
	    		return connectionerror;  
	    	}
	    	return queryresult;
	    }
	    
	    
	    //English Diceword
	    public String retrieveDiceWordfromDatabase(String zuordnung){
		try {
		    Class.forName(driver);
		    this.setUrl();
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Statement st = conn.createStatement();
		    String query = "SELECT Word FROM diceWord WHERE Wurf = " + zuordnung ;
		    ResultSet res = st.executeQuery(query);
		    while (res.next()) {
			String msg = res.getString("Word");
			this.queryresult = "" + msg;
		    }
		    conn.close();
		} catch (Exception e) {    
		    connectionerror = e.getMessage();
		    return connectionerror;  
		}
		return queryresult;
	    }
	    
	    //German Diceword
		public String retrieveGermanDiceWordfromDatabase(String zuordnung){
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Statement st = conn.createStatement();
		    String query = "SELECT Wort FROM diceWord WHERE Wurf = " + zuordnung ;
		    ResultSet res = st.executeQuery(query);
		    while (res.next()) {
			String msg = res.getString("Wort");
			queryresult = "" + msg;
		    }
		    conn.close();
		} catch (Exception e) {    
		    connectionerror = e.getMessage();
		    return connectionerror;  
		}
		return queryresult;
	    }
		
	    //French Diceword
		public String retrieveFrenchDiceWordfromDatabase(String zuordnung){
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Statement st = conn.createStatement();
		    String query = "SELECT Mot FROM diceWord WHERE Wurf = " + zuordnung ;
		    ResultSet res = st.executeQuery(query);
		    while (res.next()) {
			String msg = res.getString("Mot");
			queryresult = "" + msg;
		    }
		    conn.close();
		} catch (Exception e) {    
		    connectionerror = e.getMessage();
		    return connectionerror;  
		}
		return queryresult;
	    }
		
	    //Spanish Diceword
		public String retrieveSpanishDiceWordfromDatabase(String zuordnung){
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Statement st = conn.createStatement();
		    String query = "SELECT Palabra FROM diceWord WHERE Wurf = " + zuordnung ;
		    ResultSet res = st.executeQuery(query);
		    while (res.next()) {
			String msg = res.getString("Palabra");
			queryresult = "" + msg;
		    }
		    conn.close();
		} catch (Exception e) {    
		    connectionerror = e.getMessage();
		    return connectionerror;  
		}
		return queryresult;
	    }
		
	    //EFF Diceword
		public String retrieveEFFDiceWordfromDatabase(String zuordnung){
		try {
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url,userName,password);
		    Statement st = conn.createStatement();
		    String query = "SELECT EFF FROM diceWord WHERE Wurf = " + zuordnung ;
		    ResultSet res = st.executeQuery(query);
		    while (res.next()) {
			String msg = res.getString("EFF");
			queryresult = "" + msg;
		    }
		    conn.close();
		} catch (Exception e) {    
		    connectionerror = e.getMessage();
		    return connectionerror;  
		}
		return queryresult;
	    }
}