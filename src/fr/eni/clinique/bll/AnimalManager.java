package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
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
	
	public List<Animal> getAnimaux() {
			List<Animal> listeAnimaux = null;
		try {
			
			listeAnimaux = daoAnimal.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return listeAnimaux ;
	}
	
	public Animal getAnimal(int codeAnimal) throws DALException {
		return daoAnimal.selectById(codeAnimal);	
	}
	
	public void addAnimal(Animal animal) throws DALException {
		 daoAnimal.insert(animal);	
	}
	
	public void update(Animal animal) throws DALException {
		daoAnimal.update(animal);
	}
	
	public void updateIsArchive(Animal animal) throws DALException{
		animal.setArchive(true);
		daoAnimal.updateIsArchive(animal);
	}
	
	public void updateIsArchiveByClient(Client client) throws DALException {
		daoAnimal.updateIsArchiveByClient(client);
	}
	
	public List<Animal> getEspeceByAnimal(Animal animal) throws DALException {
		String espece = animal.getRace().getEspece();
		return daoAnimal.selectByEspece(espece);
	}
	
	public List<Animal> getAnimauxByClient(Client client) throws DALException {	
		return daoAnimal.selectByClient(client.getCodeClient());
	}
}
