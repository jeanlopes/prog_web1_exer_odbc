package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.jdbc.driver.OracleDriver;


public class ConnectionManager {
	
	private static ConnectionManager connectionManager;
	private Context env;
	private final String DRIVER = "driver";
	private final String HOST = "host";
	private final String USER = "user";
	private final String PASSWORD = "password";
	
	public static ConnectionManager getInstance() {
		if (connectionManager == null) 
			connectionManager = new ConnectionManager();
		
		return connectionManager;
	}
	
	public Connection getConnection() throws SQLException, NamingException {
		
		return DriverManager.getConnection((String)this.env.lookup(HOST), (String)this.env.lookup(USER), (String)this.env.lookup(PASSWORD));
	}
	
	private ConnectionManager() {
		try {
			this.env = (Context)new InitialContext().lookup("java:comp/env");
			DriverManager.registerDriver(new OracleDriver());
			Class.forName((String)this.env.lookup(DRIVER));
			
		} catch (NamingException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
