package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnel;

public class AppliTestDal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnexionDAO connexion = DAOFactory.getConnexionDAO();
		try {
			Personnel personnel = connexion.selectPersonnelByNom("GRAPIN");
			System.out.println(personnel.toString());
		} catch (DALException e) {
			
			e.printStackTrace();
		}
		
	}

}
