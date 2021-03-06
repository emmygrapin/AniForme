package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Personnel;

/**
 * 
 * @author egrapin2017
 *
 */
public interface PersonnelDAO {
public Personnel selectById(int codePerso) throws DALException;
	
	public List<Personnel> selectAll() throws DALException;
	
	public List<Personnel> selectVeto() throws DALException;
	
	public void insert(Personnel data) throws DALException;
	
	public void delete(int codePerso) throws DALException;
	
	public void updateMotDePasse(Personnel data) throws DALException;
	
	public void updateIsArchive(Personnel data) throws DALException;
	
}
