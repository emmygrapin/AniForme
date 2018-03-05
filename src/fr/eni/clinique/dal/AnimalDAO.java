package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public interface AnimalDAO {
	
	public Animal selectById(int codeAnimal) throws DALException;
	
	public List<Animal> selectAll()throws DALException;
	
	public Animal insert(Animal data)throws DALException;
	
	public void update(Animal data) throws DALException;
	
	public void updateIsArchive(Animal data) throws DALException;
	
	public List<Animal> selectByRace(String race) throws DALException;
	
	public List<Animal> selectByEspece(String espece) throws DALException;
	
	public List<Animal> selectByClient(int codeClient) throws DALException;
	

}
