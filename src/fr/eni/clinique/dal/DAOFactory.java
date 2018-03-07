package fr.eni.clinique.dal;

import fr.eni.clinique.dal.jdbc.AgendaDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.AnimalDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ConnexionDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.RaceDAOJdbcImpl;
/**
 * 
 * @author egrapin2017
 *
 */
public class DAOFactory {

	public static PersonnelDAO getPersonnelDAO() {
		PersonnelDAO personnelDAO = new PersonnelDAOJdbcImpl();
		return personnelDAO;
	}

	public static AnimalDAO getAnimalDAO() {
		AnimalDAO animalDAO = new AnimalDAOJdbcImpl();
		return animalDAO;
	}
	
	public static ConnexionDAO getConnexionDAO() {
		ConnexionDAO connexionDAO = new ConnexionDAOJdbcImpl();
		return connexionDAO;
	}

	public static ClientDAO getClientDAO() {
		ClientDAO clientDAO = new ClientDAOJdbcImpl();
		return clientDAO;
	}

	public static RaceDAO getRaceDAO() {
		RaceDAO raceDAO = new RaceDAOJdbcImpl();
		return raceDAO;
	}
	
	public static AgendaDAO getAgendaDAO() {
		AgendaDAO agendaDAO = new AgendaDAOJdbcImpl();
		return agendaDAO;
	}
}
