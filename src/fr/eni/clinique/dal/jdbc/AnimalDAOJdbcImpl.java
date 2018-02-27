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
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.RaceDAO;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public class AnimalDAOJdbcImpl implements AnimalDAO{
													
	private static final String sqlSelectById = "select CodeAnimal, NomAnimal, Couleur, Races.Race,Races.Espece, CodeClient, Tatouage, Antecedents, Archive  "
			+ " from Animaux join Races on Animaux.Race = Races.Race where CodeAnimal like ?";
	private static final String sqlSelectAll = "select CodeAnimal, NomAnimal, Sexe, Couleur, Races.Race, Races.Espece, CodeClient, Tatouage, Antecedents, Archive from Animaux join Races on Races.Race = Animaux.Race";
	
	/*INSERT INTO MyTable  (PriKey, Description)
    SELECT ForeignKey, Description
    FROM SomeView;*/
	private static final String sqlInsert = "insert into Animaux(NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) values(?,?,?,?,?,?,?,?,?)";
	private static final String sqlUpdate = "update Animaux set CodeAnimal=?, NomAnimal=?, Sexe=?, Couleur=?,Race=?,Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=? where CodeAnimal=?";
	private static final String sqlDelete = "delete from Animaux where CodeAnimal=?";
	private static final String sqlSelectByRace = "select CodeAnimal, NomAnimal, Couleur, Races.Race,Races.Espece, CodeClient, Tatouage, Antecedents, Archive "
			+ "from Animaux join Races on Races.Race = Animaux.Race where Race like ?";
	
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
										 raceDAO.selectById(rs.getString("Race"), rs.getString("Espece")), // Race race, Race espece 
										 clientDAO.selectById(rs.getInt("CodeClient")), // int CodeClient
										 rs.getString("Tatouage"),                      // String Tatouage
										 rs.getString("Antecedents"),                   // String Antecedents
										 rs.getBoolean("Archive"));                     // Boolean archive
										 
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
			RaceDAO raceDAO = DAOFactory.getRaceDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();

			while (rs.next()) {
				
				animal = new Animal(rs.getInt("CodeAnimal"),            // int CodeAnimal    
						rs.getString("NomAnimal"),   					// String nomAnimal
						 rs.getString("Sexe"),  						// String sexe                    
						 rs.getString("Couleur"),         // String couleur       
						 raceDAO.selectById(rs.getString("Race"), rs.getString("Espece")), //Race race, Race Espece
						 clientDAO.selectById(rs.getInt("CodeClient")), // Client CodeClient
						 rs.getString("Tatouage"),                      // String Tatouage
						 rs.getString("Antecedents"),                   // String Antecedents
						 rs.getBoolean("Archive"));                     // Boolean archive
				
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
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomAnimal());  			  // NomAnimal
			rqt.setString(2, data.getSexe());                     // Sexe
			rqt.setString(3, data.getCouleur());                  // Couleur
			rqt.setString(4, data.getRace().getRace());           // Race
			rqt.setString(5, data.getRace().getEspece());           // Espece
			rqt.setInt(6, data.getClient().getCodeClient());        // CodeClient
			rqt.setString(7, data.getTatouage());  			  // Antecedents
			rqt.setString(8, data.getAntecedents());  			  // Antecedents
			rqt.setBoolean(9, data.isArchive());		      	  // Archive
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodeAnimal(rs.getInt(1));
				}
			}

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
			rqt = cnx.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomAnimal());
			rqt.setString(2, data.getSexe());
			rqt.setString(3, data.getCouleur());
			rqt.setString(4, data.getRace().getRace());
			rqt.setString(5, data.getRace().getEspece());
			rqt.setInt(6, data.getClient().getCodeClient());
			rqt.setString(7, data.getTatouage());
			rqt.setString(8, data.getAntecedents());
			rqt.setBoolean(9, data.isArchive());

			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodeAnimal(rs.getInt(1));
				}
			}

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
	public Animal selectByRace(String race) throws DALException {
			
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Animal animal = null;
			RaceDAO raceDAO = DAOFactory.getRaceDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			
			try {
				cnx = getConnection();
				rqt = cnx.prepareStatement(sqlSelectByRace);
				rqt.setString(1, race);

				rs = rqt.executeQuery();
				if (rs.next()) {
					
					animal = new Animal(rs.getInt("CodeAnimal"),  	       		            // int codeAnimal
											 rs.getString("NomAnimal"),   					// String nomAnimal
											 rs.getString("Sexe"),  						// String sexe                    
											 rs.getString("Couleur"),         			   	// String couleur       
											 raceDAO.selectById(rs.getString("Race"),rs.getString("Espece")), // Race race, Race espece
											 clientDAO.selectById(rs.getInt("CodeClient")), // int CodeClient
											 rs.getString("Tatouage"),                      // String Tatouage
											 rs.getString("Antecedents"),                   // String Antecedents
											 rs.getBoolean("Archive"));                     // Bit archive
											 
				}

			} catch (SQLException e) {
				throw new DALException("selectByRace failed - id = " + race, e);
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
