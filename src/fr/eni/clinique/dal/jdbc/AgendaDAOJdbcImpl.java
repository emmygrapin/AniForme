package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.JdbcTools;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.dal.AgendaDAO;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelDAO;

public class AgendaDAOJdbcImpl implements AgendaDAO {
	private static final String sqlSelectByVeterinaire = "select CodeAnimal, DateRdv, CodeVeto"
			+ "from Agendas where CodeVeto = ?";

	private static final String sqlSelectByVeterinaireByDate = "select CodeAnimal, DateRdv, CodeVeto"
			+ "from Agendas where CodeVeto = ? and Date = ?";

	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values(?;?;?)";
	private static final String sqlDelete = "delete from Agendas where CodeVeto = ? and CodeAnimal = ? and DateRdv = ? ";
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
	 * requ�te qui permet de r�cup�rer une liste de rdv par v�t�rinaire
	 * (personnel)
	 */
	@Override
	public List<Agenda> selectByVeterinaire(int codeVeto) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Agenda> agendas = new ArrayList<Agenda>();
		PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();

		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectByVeterinaire);
			rqt.setInt(1, codeVeto);
			rs = rqt.executeQuery();
			Agenda agenda = null;
			while (rs.next()) {
				agenda = new Agenda(personnelDAO.selectById(rs.getInt("CodeVeto")), rs.getDate("DateRdv"),
						animalDAO.selectById(rs.getInt("CodeAnimal")));
				agendas.add(agenda);
			}
		} catch (SQLException e) {
			throw new DALException("selectByVeterinaire failed - id = " + codeVeto, e);
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
		return agendas;
	}

	/**
	 * Requ�te qui permet de r�cup�rer une liste de RDV par v�t�rinaire et par date
	 */
	@Override
	public List<Agenda> selectByVeterinaireByDate(int codeVeto, String dateRdv) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Agenda> agendas = new ArrayList<Agenda>();
		PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();

		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectByVeterinaireByDate);
			rqt.setInt(1, codeVeto);
			rqt.setString(2, dateRdv);
			rs = rqt.executeQuery();
			Agenda agenda = null;
			while (rs.next()) {
				agenda = new Agenda(personnelDAO.selectById(rs.getInt("CodeVeto")), rs.getDate("DateRdv"),
						animalDAO.selectById(rs.getInt("CodeAnimal")));
				agendas.add(agenda);
			}
		} catch (SQLException e) {
			throw new DALException("selectByVeterinaireByDate failed - id = " + codeVeto, e);
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
		return agendas;
	}

	@Override
	public void insert(Agenda data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setInt(1, data.getPersonnel().getCodePerso());
			rqt.setDate(2, (Date) data.getDateRdv());
			rqt.setInt(3, data.getAnimal().getCodeAnimal());
	
			rqt.executeUpdate();
			
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
	public void delete(Agenda data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		int codeVeto = data.getPersonnel().getCodePerso();
		Date dateRdv = (Date) data.getDateRdv();
		int codeAnimal = data.getAnimal().getCodeAnimal();
		
		try {
			cnx = getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, codeVeto);
			rqt.setDate(2, dateRdv);
			rqt.setInt(3, codeAnimal);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete rdv failed - id=" + codeVeto + "pour l'animal" +codeAnimal, e);
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
