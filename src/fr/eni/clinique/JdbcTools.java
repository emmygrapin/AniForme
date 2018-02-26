package fr.eni.clinique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.clinique.Settings;

public class JdbcTools {
private static Connection connection;
	
	public static Connection jdbcConnexion() throws SQLException {
		connection = null;
		try {
			//démarrer le driver
			Class.forName(Settings.getProperty("driverDB"));
			//driver se connecte à la bdd
			connection = DriverManager.getConnection(Settings.getProperty("urldb"),Settings.getProperty("userdb"), Settings.getProperty("passworddb"));
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

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
