package kranchie.java.woerter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import kranchie.java.customExceptions.CustomUnchecked;
import kranchie.java.dateien.Datei;

public class DiceWord_sqlite extends DiceWord {

    private String	 path;
    private String	 fullpath;
    private String	 dbfile;
    private final String drivercomp = "jdbc:sqlite:";
    private String	 queryresult;
    private String	 connectionerror;

    /* Constructor */
    public DiceWord_sqlite(String path, String filename, String dateiendung) {
	super();
	Datei file = new Datei(filename, path, true);
	file.setdateiEndung(dateiendung, true);
	this.setfullpath(file);
    }

    public DiceWord_sqlite() {
	super();
    }
    
    public DiceWord_sqlite(Datei datei) {
	super();
	Datei file = datei;
	this.setfullpath(file);
    }

    public String getfullpath() {
	return this.fullpath;
    }

    private void setfullpath(Datei file) {
	String	fehler, error;
	boolean	validpath = file.checkname_validpath(file.getSpeicherPfad());
	boolean	validfile = file.checkname_fileexist(file.getSpeicherPfad(), file.getDateiName());
	try {
	    if (validpath && validfile) {
		this.fullpath = this.drivercomp + file.getabsolutePath();
	    } else if (!validpath) {
		throw new CustomUnchecked("1");
	    } else if (!validfile) {
		throw new CustomUnchecked("2");
	    } else {
		throw new CustomUnchecked("Unerwartet");
	    }
	} catch (CustomUnchecked e) {
	    fehler = e.getMessage();
	    switch (fehler) {
		case "1":
		    error = "Der gelieferte Pfad " + path + " existiert nicht.";
		    break;
		case "2":
		    error = "Die Datei " + this.dbfile + " ist unter dem Pfad " + this.path + " nicht vorhanden.";
		    break;
		default:
		    error = "Es ist ein unerwarteter Fehler aufgetreten.";
		    break;
	    }
	    throw new IllegalArgumentException(error);
	}
    }

    public String getDrivercomp() {
	return this.drivercomp;
    }

    public String getQueryresult() {
	return this.queryresult;
    }

    public String getConnectionerror() {
	return this.connectionerror;
    }

    // Variables SELECT
    public String retrieveDB(String select, String table, String column, String searchpattern) {
	try {
	    Connection conn  = DriverManager.getConnection(this.fullpath);
	    Statement  st    = conn.createStatement();
	    String     query = "Select " + select + " FROM " + table + " Where " + column + " = " + searchpattern;
	    ResultSet  res   = st.executeQuery(query);
	    while (res.next()) {
		String msg = res.getString(select);
		this.queryresult = "" + msg;
	    }
	    conn.close();
	} catch (SQLException e) {
	    connectionerror = e.getMessage();
	    return connectionerror;
	}
	return queryresult;
    }

    // Diceword
    public String retrieveDiceWordfromDatabase(String zuordnung, String column) {
	try {
	    Connection conn  = DriverManager.getConnection(this.fullpath);
	    Statement  st    = conn.createStatement();
	    String     query = "SELECT " + column + " FROM diceWord WHERE Wurf = " + zuordnung;
	    ResultSet  res   = st.executeQuery(query);
	    while (res.next()) {
		String msg = res.getString(column);
		this.queryresult = "" + msg;
	    }
	    conn.close();
	} catch (SQLException e) {
	    connectionerror = e.getMessage();
	    return connectionerror;
	}
	return queryresult;
    }
}
