package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnel;

/**
 * 
 * @author egrapin2017
 *
 */
public interface ConnexionDAO {
	public Personnel selectPersonnelByNom(String Nom) throws DALException;
}
