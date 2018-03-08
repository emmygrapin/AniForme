package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.JdbcTools;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.dal.AgendaDAO;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelDAO;

public class AgendaDAOJdbcImpl implements AgendaDAO {
	private static final String sqlSelectByVeterinaire = "select CodeAnimal, DateRdv, CodeVeto "
			+ "from Agendas where CodeVeto = ?";

	private static final String sqlSelectByVeterinaireByDate = "select CodeAnimal, DateRdv, CodeVeto "
			+ "from Agendas where CodeVeto = ? and DateRdv > ? and DateRdv < ?";

	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values(?,?,?)";
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
	 * requête qui permet de récupérer une liste de rdv par vétérinaire
	 * (personnel)
	 */
	@Override
	public List<Agenda> selectByVeterinaire(int codeVeto) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Agenda> agendas = new ArrayList<Agenda>();
		

		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectByVeterinaire);
			Agenda agenda = null;
			PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
			AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
			
			rqt.setInt(1, codeVeto);
			rs = rqt.executeQuery();
			while (rs.next()) {
				Timestamp stamp = new Timestamp(rs.getTimestamp("DateRdv").getTime());
				Date date = new Date(stamp.getTime());
				agenda = new Agenda(personnelDAO.selectById(rs.getInt("CodeVeto")), date,
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
	 * Requête qui permet de récupérer une liste de RDV par vétérinaire et par date
	 */
	@Override
	public List<Agenda> selectByVeterinaireByDate(int codeVeto, Date dateRdv) throws DALException {
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
			
			
			SimpleDateFormat date = new SimpleDateFormat("yyyy-dd-MM");
			String newDate = date.format(dateRdv);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(newDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 1);
			String newDate2 = sdf.format(c.getTime());  
			
			rqt.setString(2, newDate);
			rqt.setString(3, newDate2);
			rs = rqt.executeQuery();
			Agenda agenda = null;
			while (rs.next()) {
				agenda = new Agenda(personnelDAO.selectById(rs.getInt("CodeVeto")), rs.getTimestamp("DateRdv"),
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
			SimpleDateFormat date = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			String newDate = date.format(data.getDateRdv());
			rqt.setString(2,newDate);
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
		int codeAnimal = data.getAnimal().getCodeAnimal();
		
		try {
			cnx = getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, codeVeto);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			String newDate = date.format(data.getDateRdv());
			rqt.setInt(2, codeAnimal);
			rqt.setString(3, newDate);
			System.out.println(newDate);
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
