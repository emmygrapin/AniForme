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
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DALException;

/**
 * 
 * @author alemercier2017
 *
 */

public class ClientDAOJdbcImpl implements ClientDAO {

	private static final String sqlSelectById = "select CodeClient, NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive"
			+ " from Clients where CodeClient = ?";
	private static final String sqlSelectByNom = "select CodeClient, NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive"
			+ " from Clients where NomClient like ?";
	private static final String sqlSelectAll = "select CodeClient, NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive"
			+ " from Clients";
	private static final String sqlInsert = "insert into Clients(NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from Clients where CodeClient = ?";
	private static final String sqlUpdate = "update Clients set NomClient = ?, PrenomClient = ?, Adresse1 = ?, Adresse2 = ?, CodePostal = ?, Ville = ?, NumTel = ?, Assurance = ? , Email = ? , Remarque = ?, Archive = ?"
			+ " where CodeClient = ?";
	
	private Connection connection;
	
	// Constructeur
	public ClientDAOJdbcImpl() {

	}

	public Connection getConnection() throws SQLException {
		if (connection == null) {
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
	public Client selectById(int CodeClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Client client = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, CodeClient);
			rs = rqt.executeQuery();
			if (rs.next()) {
			
					client = new Client(rs.getInt("CodeClient"), rs.getString("NomClient"), rs.getString("PrenomClient"),
							rs.getString("Adresse1"), rs.getString("Adresse2"), rs.getString("CodePostal"),rs.getString("Ville"), 
							rs.getString("NumTel"), rs.getString("Assurance"), rs.getString("Email"), rs.getString("Remarque"), rs.getBoolean("Archive")
							);
				}
			} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + CodeClient, e);
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
		return client;
	}

	@Override
	public List<Client> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Client> clients = new ArrayList<Client>();
		try{
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Client client = null;
			while (rs.next()){
				client = new Client(rs.getInt("CodeClient"), rs.getString("NomClient"), rs.getString("PrenomClient"),
						rs.getString("Adresse1"), rs.getString("Adresse2"), rs.getString("CodePostal"),rs.getString("Ville"), 
						rs.getString("NumTel"), rs.getString("Assurance"), rs.getString("Email"), rs.getString("Remarque"), rs.getBoolean("Archive")
						);
				clients.add(client);
			}
		}
		catch(SQLException e){
			throw new DALException("selectAll failed ", e);
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
		return clients;
	}
	
	
	public List<Client> selectByNom(String nom) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Client> clients = new ArrayList<Client>();
		try{
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectByNom);
			rqt.setString(1, "%"+nom+"%");
			rs = rqt.executeQuery();
			Client client = null;
			while (rs.next()){
				client = new Client(rs.getInt("CodeClient"), rs.getString("NomClient"), rs.getString("PrenomClient"),
						rs.getString("Adresse1"), rs.getString("Adresse2"), rs.getString("CodePostal"),rs.getString("Ville"), 
						rs.getString("NumTel"), rs.getString("Assurance"), rs.getString("Email"), rs.getString("Remarque"), rs.getBoolean("Archive")
						);
				clients.add(client);
			}
		}
		catch(SQLException e){
			throw new DALException("selectAll failed ", e);
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
		return clients;
	}

	@Override
	public Client insert(Client data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomClient());
			rqt.setString(2, data.getPrenomClient());
			rqt.setString(3, data.getAdresse1());
			rqt.setString(4, data.getAdresse2());
			rqt.setString(5, data.getCodePostal());
			rqt.setString(6, data.getVille());
			rqt.setString(7, data.getNumeroTel());
			rqt.setString(8, data.getAssurance());
			rqt.setString(9, data.getEmail());
			rqt.setString(10, data.getRemarque());
			rqt.setBoolean(11, data.getArchive());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodeClient(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			throw new DALException("insert failed ", e);
		}finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();
		}
		return data;
	}
	
	
	public void update(Client data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomClient());
			rqt.setString(2, data.getPrenomClient());
			rqt.setString(3, data.getAdresse1());
			rqt.setString(4, data.getAdresse2());
			rqt.setString(5, data.getCodePostal());
			rqt.setString(6, data.getVille());
			rqt.setString(7, data.getNumeroTel());
			rqt.setString(8, data.getAssurance());
			rqt.setString(9, data.getEmail());
			rqt.setString(10, data.getRemarque());
			rqt.setBoolean(11, data.getArchive());
			rqt.setInt(12, data.getCodeClient());
		    rqt.executeUpdate();
			
		}catch(SQLException e){
			throw new DALException("update failed ", e);
		}finally {
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
	public void delete(int CodeClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, CodeClient);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete client failed - id=" + CodeClient, e);
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
