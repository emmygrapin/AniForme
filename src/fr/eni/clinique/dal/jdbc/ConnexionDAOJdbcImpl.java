package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique.JdbcTools;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.ConnexionDAO;
import fr.eni.clinique.dal.DALException;
/**
 * 
 * @author egrapin2017
 * 
 */
public class ConnexionDAOJdbcImpl implements ConnexionDAO
{
	private static final String sqlSelectPersonnelByNom = "select CodePers, Nom, MotPasse, Role, Archive"
			+ " from Personnels where Nom = ?";
	private Connection connection;
	
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = JdbcTools.jdbcConnexion();
		}
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
	}
	
	/**
	 * Récupère le personnel correspondant au nom du personnel saisi lors de la connexion
	 */
	@Override
	public Personnel selectPersonnelByNom(String Nom) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnel personnel = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectPersonnelByNom);
			rqt.setString(1, Nom);
			rs = rqt.executeQuery();
			if (rs.next()) {
				personnel = new Personnel(rs.getInt("CodePers"), rs.getString("Nom"), rs.getString("MotPasse"),
						rs.getString("Role"), rs.getBoolean("Archive"));
			}
		} catch (SQLException e) {
			throw new DALException("selectByNom failed - Nom = " + Nom, e);
		} finally {
			try {

				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();

		}
		return personnel;
		
	}

}
