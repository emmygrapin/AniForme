package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.JdbcTools;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.RaceDAO;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.SpectacleDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DAOFactory;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public class AnimalDAOJdbcImpl implements AnimalDAO{
													//CodeAnimal NomAnimal Couleur Race  Espece  CodeClient Tatouage Antecedents Archive
	private static final String sqlSelectById = "select CodeAnimal, NomAnimal, Couleur, Race, CodeClient, Tatouage, Antecedents, Archive  "
			+ " from Animaux where CodeAnimal like ?";
	private static final String sqlSelectAll = "select CodeAnimal, NomAnimal, Couleur, Race, CodeClient, Tatouage, Antecedents, Archive from Animaux";
	private static final String sqlInsert = "insert into Animaux(NomAnimal, Couleur, Race, CodeClient, Tatouage, Antecedents, Archive) values(?,?,?,?,?,?,?)";
	private static final String sqlUpdate = "update Animaux set NomAnimal=?, Couleur=?,Race=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=? where CodeAnimal=?";
	private static final String sqlDelete = "delete from Animaux where CodeAnimal=?";
	private static final String sqlSelectByRace = "select CodeAnimal, NomAnimal, Couleur, Race, CodeClient, Tatouage, Antecedents, Archive "
			+ "from Animaux where Race like ?";
	
	private Connection connection;
	
	public Connection getConnection() throws SQLException 
	{
		//test la connexion si null
		if(connection == null) {
			connection = JdbcTools.jdbcConnexion();
		}
			return connection;
	}
	
	public void closeConnection(){
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
	
	@Override
	public Animal selectById(int codeAnimal) throws DALException {
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Animal animal = null;
		RaceDAO raceDAO = DAOFactory.getRaceDAO();
		ClientDAO clientDAO = DAOFactory.getClientDAO();
		
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, codeAnimal);

			rs = rqt.executeQuery();
			if (rs.next()) {
				
				animal = new Animal(rs.getInt("CodeAnimal"),  	       		            // int codeAnimal
										 rs.getString("NomAnimal"),   					// String nomAnimal
										 rs.getString("Sexe"),  						// String sexe                    
										 rs.getString("Couleur"),         			   	// String couleur       
										 raceDAO.selectById(rs.getString("Race")), 		// Race race 
										 clientDAO.selectById(rs.getInt("CodeClient")), // int CodeClient
										 rs.getString("Tatouage"),                      // String Tatouage
										 rs.getString("Antecedents"),                   // String Antecedents
										 rs.getBoolean("Archive"));                     // Bit archive
										 
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + codeAnimal, e);
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
		return animal;
		
		return null;
	}

	@Override
	public List<Animal> selectAll() throws DALException {
		
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Animal> liste = new ArrayList<Animal>();
		try {
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Animal animal = null;
			RaceDAO raceDAO = DAOFactory.getSpectacleDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();

			while (rs.next()) {
				
				animal = new Animal(rs.getInt("CodeAnimal"),            // int CodeAnimal    
						rs.getString("NomAnimal"),   					// String nomAnimal
						 rs.getString("Sexe"),  						// String sexe                    
						 rs.getString("Couleur"),         			   	// String couleur       
						 raceDAO.selectById(rs.getString("Race")), 		// Race race 
						 clientDAO.selectById(rs.getInt("CodeClient")), // Client CodeClient
						 rs.getString("Tatouage"),                      // String Tatouage
						 rs.getString("Antecedents"),                   // String Antecedents
						 rs.getBoolean("Archive"));                     // Bit archive
				
				liste.add(animal);
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
	public void insert(Animal data) throws DALException {
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setString(1, data.getNomAnimal());  			  // NomAnimal
			rqt.setString(2, data.getSexe());                     // Sexe
			rqt.setString(3, data.getCouleur());                  // Couleur
			rqt.setString(4, data.getRace().getRace());           // Race
			rqt.setInt(5, data.getClient().getCodeClient());        // CodeClient
			rqt.setString(6, data.getTatouage());  			  // Antecedents
			rqt.setString(7, data.getAntecedents());  			  // Antecedents
			rqt.setBoolean(8, data.isArchive());		      	  // Archive
			

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
	public void update(Animal data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getNomAnimal());
			rqt.setString(2, data.getCouleur());
			rqt.setString(3, data.getRace().getRace());
			rqt.setInt(4, data.getClient().getCodeClient());
			rqt.setString(5, data.getTatouage());
			rqt.setString(6, data.getAntecedents());
			rqt.setBoolean(7, data.isArchive());

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
	public void delete(int codeAnimal) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, codeAnimal);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + codeAnimal, e);
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

	@Override
	public Animal selectByRace(int race) throws DALException {
			
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Animal animal = null;
			RaceDAO raceDAO = DAOFactory.getRaceDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			
			try {
				cnx = getConnection();
				rqt = cnx.prepareStatement(sqlSelectById);
				rqt.setInt(1, race);

				rs = rqt.executeQuery();
				if (rs.next()) {
					
					animal = new Animal(rs.getInt("CodeAnimal"),  	       		            // int codeAnimal
											 rs.getString("NomAnimal"),   					// String nomAnimal
											 rs.getString("Sexe"),  						// String sexe                    
											 rs.getString("Couleur"),         			   	// String couleur       
											 raceDAO.selectById(rs.getString("Race")), 		// Race race 
											 clientDAO.selectById(rs.getInt("CodeClient")), // int CodeClient
											 rs.getString("Tatouage"),                      // String Tatouage
											 rs.getString("Antecedents"),                   // String Antecedents
											 rs.getBoolean("Archive"));                     // Bit archive
											 
				}

			} catch (SQLException e) {
				throw new DALException("selectById failed - id = " + race, e);
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
			return animal;
			
	}

	
}
