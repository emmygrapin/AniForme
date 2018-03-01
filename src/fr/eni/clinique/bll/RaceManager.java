package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.RaceDAO;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public class RaceManager {

	private RaceDAO daoRace;
	private static RaceManager _instance;

	private RaceManager() {
		daoRace = DAOFactory.getRaceDAO();
	}

	public static RaceManager getInstance() {

		if (_instance == null) {
			_instance = new RaceManager();
		}
		return _instance;

	}

	public List<Race> getRaces() throws DALException {

		return daoRace.selectAll();
	}

	public Race getRace(String race, String espece) throws DALException {

		return daoRace.selectById(race, espece);
	}
	
	public List<Race> getEspeces() throws DALException {

		return daoRace.selectEspeces();
	}

	public Race getEspece(String race, String espece) throws DALException {

		return daoRace.selectById(race, espece);
	}
	
	public List<Race> getRacesByEspece(String espece) throws DALException {
		return daoRace.selectRacesByEspece(espece);
	}
}
