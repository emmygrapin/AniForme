package fr.eni.clinique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.clinique.Settings;

public class JdbcTools {
private static Connection connection;
	
	//ex�cut� la premi�re fois que la classe est appel�e 
	static {
		//d�marrer le driver
		try {
			Class.forName(Settings.getProperty("driverDB"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection jdbcConnexion() throws SQLException {
		connection = null;
		//driver se connecte � la bdd
		connection = DriverManager.getConnection(Settings.getProperty("urldb"),Settings.getProperty("userdb"), Settings.getProperty("passworddb"));

		return connection;
	}
	
	public static void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection=null;
		}
	}
}
