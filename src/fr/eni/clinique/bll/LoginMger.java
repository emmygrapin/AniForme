package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.ConnexionDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

/**
 * 
 * @author egrapin2017
 *
 */
public class LoginMger {
	private ConnexionDAO daoConnexion;
	private static LoginMger _instance;

	private LoginMger() {
		daoConnexion = DAOFactory.getConnexionDAO();
	}

	public static LoginMger getInstance() {
		if (_instance == null) {
			_instance = new LoginMger();
		}
		return _instance;
	}

	public Personnel getConnexion(String identifiant, String motDePasse) throws BLLException {
		Personnel personnel = null;

		try {
			personnel = daoConnexion.selectPersonnelByNom(identifiant);
			if (personnel != null) {
				if (personnel.isArchive() == false) {
					if (!personnel.getMotDePasse().equals(motDePasse)) {
						throw new BLLException("votre mot de passe est incorrect");
					}
				} else {
					throw new BLLException("Votre compte a été désactivé.");
				}
			} else {
				throw new BLLException("Votre compte n'existe pas.");
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personnel;
	}

}
