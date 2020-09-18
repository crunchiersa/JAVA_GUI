package kranchie.java.kommunikation.woerter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import kranchie.java.customExceptions.CustomUnchecked;
import kranchie.java.dateien.Datei;

public class DiceWord_DB extends DiceWord {

	private String path, fullpath, dbfile, url, dbtype, ip, port, database, userName, password, drivercomp;
	// If Timeformat on server differs from UTC timestamps will be read incorrectly!
	private final String timezone = "?serverTimezone=UTC"; 
															
	public DiceWord_DB() {
		super();
	}
	
	public DiceWord_DB(String dbtype) {
		super();
		this.dbtype = dbtype;
	}

	public DiceWord_DB(String ip, String port, String database, String userName, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.database = database;
		this.userName = userName;
		this.password = password;
		this.dbtype = "mysql";
		this.drivercomp = "jdbc:mysql:";
		this.setUrl();
	}

	public DiceWord_DB(String username, String password) {
		super();
		this.ip = "10.10.60.7"; // Should be adjusted to your local Settings.
		this.port = "3306"; // Should be adjusted to your local Settings.
		this.database = "diceword"; // Should be adjusted to your local Settings.
		this.userName = username;
		this.password = password;
		this.dbtype = "mysql";
		this.drivercomp = "jdbc:mysql:";
		this.setUrl();
	}

	public DiceWord_DB(String path, String filename, String dateiendung) {
		super();
		this.dbtype = "sqlite";
		this.drivercomp = "jdbc:sqlite:";
		Datei file = new Datei(filename, path, true);
		file.setdateiEndung(dateiendung, true);
		this.setfullpath(file);
	}

	public DiceWord_DB(Datei datei) {
		super();
		this.dbtype = "sqlite";
		this.drivercomp = "jdbc:sqlite:";
		Datei file = datei;
		this.setfullpath(file);
	}

	public String getfullpath() {
		return this.fullpath;
	}

	private void setfullpath(Datei file) {
		String fehler, error;
		boolean validpath = file.checkname_validpath(file.getSpeicherPfad());
		boolean validfile = file.checkname_fileexist(file.getSpeicherPfad(), file.getDateiName());
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

	private void setUrl() {
		this.url = this.drivercomp + "//" + this.ip + ":" + this.port + "/" + this.database + this.timezone;
	}

	public void setDatabase(String database) {
		this.database = database;
		if (this.dbtype == "mysql") {
			this.setUrl();
		}
	}

	public String getDatabase() {
		return this.database;
	}

	
	public void setDrivercomp(String dbtype) {
		if (dbtype == "mysql") {
			this.drivercomp = "jdbc:mysql:";
		}else if (dbtype == "sqlite") {
			this.drivercomp = "jdbc:sqlite:"; 
		} else {
			throw new CustomUnchecked("Der genannte Datenbanktyp ist unzulässig");
		}
	}
	
	public String getDrivercomp() {
		return this.drivercomp;
	}
	
	public void setuserName(String userName) {
		this.userName = userName;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}
	

	@Override
	public String retrieveDiceWordfromDatabase(String zuordnung, String column) {
		try {
			Connection conn = null;
			if (this.dbtype == "sqlite") {
				conn = DriverManager.getConnection(this.fullpath);
			} else if (this.dbtype == "mysql") {
				conn = DriverManager.getConnection(this.url, this.userName, this.password);
			} else {
				this.setConnectionerror("Kein Datenbanktyp definiert!");
				return this.getConnectionerror();
			}
			Statement st = conn.createStatement();
			String query = "SELECT " + column + " FROM diceWord WHERE Wurf = " + zuordnung;
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				String msg = res.getString(column);
				this.setQueryresult("" + msg);
			}
			conn.close();
		} catch (SQLException e) {
			this.setConnectionerror(e.getMessage());
			return this.getConnectionerror();
		}
		return this.getQueryresult();
	}

	@Override
	public String retrievefromDB(String select, String table, String column, String searchpattern) {
		try {
			Connection conn = null;
			if (this.dbtype == "sqlite") {
				conn = DriverManager.getConnection(this.fullpath);
			} else if (this.dbtype == "mysql") {
				conn = DriverManager.getConnection(this.url, this.userName, this.password);
			} else {
				this.setConnectionerror("Kein Datenbanktyp definiert!");
				return this.getConnectionerror();
			}
			Statement st = conn.createStatement();
			String query = "Select " + select + " FROM " + table + " Where " + column + " = " + searchpattern;
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				String msg = res.getString(select);
				this.setQueryresult("" + msg);
			}
			conn.close();

		} catch (SQLException e) {
			this.setConnectionerror(e.getMessage());
			return this.getConnectionerror();
		}
		return this.getQueryresult();
	}

}
