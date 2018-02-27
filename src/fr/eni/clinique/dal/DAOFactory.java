package fr.eni.clinique.dal;

import fr.eni.clinique.dal.jdbc.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelDAOJdbcImpl;
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

	public static ClientDAO getClientDAO() {
		ClientDAO clientDAO = new ClientDAOJdbcImpl();
		return clientDAO;
	}

	public static RaceDAO getRaceDAO() {
		RaceDAO raceDAO = new RaceDAOJdbcImpl();
		return raceDAO;
	}
}
