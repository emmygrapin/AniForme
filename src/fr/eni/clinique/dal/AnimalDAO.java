package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.DALException;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public interface AnimalDAO {
	
	public Animal selectById(int codeAnimal) throws DALException;
	
	public List<Animal> selectAll()throws DALException;
	
	public void insert(Animal data)throws DALException;
	
	public void update(Animal data) throws DALException;
	
	public void delete(int codeAnimal)throws DALException;
	
	public Animal selectByRace(String race) throws DALException;

}
