package kranchie.java.woerter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DiceWord_mysql extends DiceWord {

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

    /* Constructor */
    public DiceWord_mysql(String ip, String port, String database, String userName, String password) {
	super();
	this.ip	      = ip;
	this.port     = port;
	this.database = database;
	this.userName = userName;
	this.password = password;
	this.setUrl();
    }

    public DiceWord_mysql(String username, String password) {
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

    // Variables SELECT
    public String retrieveDB(String select, String table, String column, String searchpattern, String ip, String port,
	    String database, String username, String password) {
	try {
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
