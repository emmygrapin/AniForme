package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelDAO;

public class PersonnelMger {
	private PersonnelDAO daoPersonnel;
	private static PersonnelMger _instance;
	
	// constructeur appel DAOPersonnel et ses m�thodes
	private PersonnelMger(){
		 daoPersonnel = DAOFactory.getPersonnelDAO();
	}
	
	//Instance de personnelMger si pas d�j� instanci�
	public static PersonnelMger getInstance() 
	{
		if(_instance == null)
		{
			_instance = new PersonnelMger();
		}
		return _instance;		
	}
	// M�thode pour visualiser tout le personnel
	public List<Personnel> getPersonnels() {
		List<Personnel> personnels = null;
		try {
			personnels = daoPersonnel.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personnels;
		
	}
	// M�thode pour supprimer un personnel
	public void deletePersonnel(int codePerso) {
		try {
			daoPersonnel.delete(codePerso);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// M�thode pour ajouter une personne
	public void addPersonnel(Personnel personnel){
		try {
			daoPersonnel.insert(personnel);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// M�thode pour r�initialiser le mot de passe
	public void resetMotDePasse(Personnel personnel){
		try {
			daoPersonnel.updateMotDePasse(personnel);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateIsArchive(Personnel personnel){
		try {
			personnel.setArchive(true);
			daoPersonnel.updateIsArchive(personnel);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
