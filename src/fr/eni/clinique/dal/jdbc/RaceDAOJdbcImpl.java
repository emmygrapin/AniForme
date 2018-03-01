package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.JdbcTools;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.RaceDAO;

public class RaceDAOJdbcImpl implements RaceDAO {

	private static final String sqlSelectById = "select Race, Espece from Races where Race like ? and Espece like ?";
	private static final String sqlSelectAll = "select Race, Espece from Races";
	private static final String sqlInsert = "insert into Races(Race, Espece) values(?,?)";
	private static final String sqlUpdate = "update Races set Espece=? where Race=? and Espece=?";
	private static final String sqlDelete = "delete from Races where Race=? and Espece=?";
	private static final String sqlSelectByEspece = "select Race, Espece from Races where Espece like ?";

	private Connection connection;

	public Connection getConnection() throws SQLException {
		// test la connexion si null
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

	@Override
	public Race selectById(String codeRace, String codeEspece) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Race raceAnimal = null;
		
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setString(1, "%" + codeRace + "%" );
			rqt.setString(2, "%" + codeEspece + "%");
			System.out.println("race : " + codeRace + " espece : " + codeEspece);
			rs = rqt.executeQuery();
			if (rs.next()) {
				
				raceAnimal = new Race(rs.getString("Race"),  	   // String Race
										 rs.getString("Espece"));  // String Espece
				System.out.println("raceAnimal : " + raceAnimal);		 
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + codeRace + " - " + codeEspece, e);
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
		return raceAnimal;
	}

	@Override
	public List<Race> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Race> liste = new ArrayList<Race>();
		try {
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Race raceAnimal = null;
			
			while (rs.next()) {
				
				raceAnimal = new Race(rs.getString("Race"),    // String Race    
						rs.getString("Espece"));               // String Espece
				
				liste.add(raceAnimal);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {
				
				if (rqt != null) {
					rqt.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		return liste;
	}

	@Override
	public void insert(Race data) throws DALException {
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setString(1, data.getRace());  			  // Race
			rqt.setString(2, data.getEspece());           // Espece

			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + data, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
				
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();

		}
		
	}

	@Override
	public void update(Race data) throws DALException {
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getEspece());
			rqt.setString(2, data.getRace());
			rqt.setString(3, data.getEspece());
		
			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + data, e);
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

		
	}

	@Override
	public void delete(String codeRace, String codeEspece) throws DALException {

		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setString(1, codeRace);
			rqt.setString(2, codeEspece);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + codeRace + " - " + codeEspece, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			closeConnection();

		}
		
	}

}
