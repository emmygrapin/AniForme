package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Race;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public interface RaceDAO {

	public Race selectById(String codeRace, String codeEspece) throws DALException;
	
	public List<Race> selectAll()throws DALException;
	
	public void insert(Race data)throws DALException;
	
	public void update(Race data) throws DALException;
	
	public void delete(String codeRace, String codeEspece)throws DALException;

}
