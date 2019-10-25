package dicepassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DiceWordRetrievalMySQL {

    private String url;
    private String ip;
    private String port;
    private String database;
    private String drivercomp = "jdbc:mysql:";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String userName; 
    private String password;
    private String queryresult;
    private String connectionerror;
	
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDrivercomp() {
		return drivercomp;
	}

	public void setDrivercomp(String drivercomp) {
		this.drivercomp = drivercomp;
	}

	public String getPassword() {
		return password;
	}

	public void setUrl(String drivercomp, String ip, String port, String database) {
	this.url = drivercomp + "//" + ip + ":" + port + "/" + database;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getQueryresult() {
	return queryresult;
    }

    public String getConnectionerror() {
	return connectionerror;
    }

    public String retrieveDiceWordfromDatabase(String zuordnung){
	try {
	    Class.forName(driver);
	    this.setUrl(drivercomp, ip, port, database);
	    Connection conn = DriverManager.getConnection(url,userName,password);
	    Statement st = conn.createStatement();
	    String query = "SELECT Word FROM diceware_list_english WHERE Wurf = " + zuordnung ;
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
    
    public String retrieveGermanDiceWordfromDatabase(String zuordnung){
	try {
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url,userName,password);
	    Statement st = conn.createStatement();
	    String query = "SELECT Word FROM diceware_list_german WHERE Wurf = " + zuordnung ;
	    ResultSet res = st.executeQuery(query);
	    while (res.next()) {
		String msg = res.getString("Word");
		queryresult = "" +msg;
	    }
	    conn.close();
	} catch (Exception e) {    
	    connectionerror = e.getMessage();
	    return connectionerror;  
	}
	return queryresult;
    }
}

