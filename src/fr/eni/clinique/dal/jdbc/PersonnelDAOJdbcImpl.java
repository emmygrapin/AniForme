package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.PersonnelDAO;
import fr.eni.clinique.JdbcTools;

/**
 * 
 * @author egrapin2017
 *
 */
public class PersonnelDAOJdbcImpl implements PersonnelDAO {
	private static final String sqlSelectById = "select CodePers, Nom, MotPasse, Role, Archive"
			+ " from Personnels where CodePers = ?";
	private static final String sqlSelectAll = "select CodePers, Nom, MotPasse, Role, Archive" + " from Personnels";
	private static final String sqlInsert = "insert into Personnels(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
	private static final String sqlDelete = "delete from client where CodePers = ?";
	private static final String sqlUpdate = "update Personnels set Nom =?, MotPasse=?,Role=?,Archive=? where CodePers =?";
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

	@Override
	public Personnel selectById(int codePerso) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Personnel personnel = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, codePerso);
			rs = rqt.executeQuery();
			if (rs.next()) {
				personnel = new Personnel(rs.getInt("CodePers"), rs.getString("Nom"), rs.getString("MotPasse"),
						rs.getString("Role"), rs.getBoolean("Archive"));
			}
		} catch (SQLException e) {
			throw new DALException("selectById failed - codePersonnel = " + codePerso, e);
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

	@Override
	public List<Personnel> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Personnel> personnels = new ArrayList<Personnel>();
		try {
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Personnel personnel = null;
			while (rs.next()) {
				personnel = new Personnel(rs.getInt("CodePers"), rs.getString("Nom"), rs.getString("MotPasse"),
						rs.getString("Role"), rs.getBoolean("Archive"));

				personnels.add(personnel);
			}
		} catch (SQLException e) {
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
		return personnels;
	}

	@Override
	public void insert(Personnel data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getMotDePasse());
			rqt.setString(3, data.getRole());
			rqt.setBoolean(4, data.isArchive());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodePerso(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("insert failed ", e);
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
	public void delete(int codePerso) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, codePerso);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + codePerso, e);
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
	public void update(Personnel data) throws DALException {

		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getMotDePasse());
			rqt.setString(3, data.getRole());
			rqt.setBoolean(4, data.isArchive());
			rqt.setInt(5, data.getCodePerso());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setCodePerso(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("update failed ", e);
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

}
