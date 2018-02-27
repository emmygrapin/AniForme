package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public class AnimalManager {

	private AnimalDAO daoAnimal;
	private static AnimalManager _instance;
	
	private AnimalManager() {
		daoAnimal = DAOFactory.getAnimalDAO();
	}
	
	public static AnimalManager getInstance() {
		
		if (_instance == null) {
			_instance = new AnimalManager();
		}
		return _instance;
		
	}
	
	public List<Animal> getAnimaux() throws DALException {
			
		return daoAnimal.selectAll();	
	}
	
	public Animal getAnimal(int codeAnimal) throws DALException {
	
		return daoAnimal.selectById(codeAnimal);	
	}
	
	public void addAnimal(Animal animal) throws DALException {
		
		 daoAnimal.insert(animal);
		
	}
	
	public void removeAnimal(Animal animal) throws DALException {
		
		 daoAnimal.delete(animal.getCodeAnimal());
	}
}
