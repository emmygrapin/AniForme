package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Personnel;
/**
 * 
 * @author egrapin2017
 *
 */
public class TestPersonnelDal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
		
		//INSERTION DE DONNEES DANS PERSONNEL

//		Personnel p2 = new Personnel("GRAPIN","password","VET",false);
//		Personnel p3 = new Personnel("DEOLIVEIRA","password","VET",false);
		Personnel p5 = new Personnel("LE MERCIER","password","VET", false);
		try{
			personnelDAO.insert(p5);
		}
		catch(DALException e){
			e.printStackTrace();
		}
		//SELECTION DE DONNEES DE PERSONNELS
		try{
			//AFFICHAGE TABLEAU DE PERSONNELS
			List<Personnel> personnels = personnelDAO.selectAll();
			System.out.println(personnels.toString());
			
			//AFFICHAGE D'UN PERSONNEL
//			Personnel personnel = personnelDAO.selectById(1);
//			System.out.println(personnel.toString());
		}
		catch(DALException e){
			e.printStackTrace();	
		}
		//UPDATE DE PERSONNEL
//		try{
//			Personnel personnel = personnelDAO.selectById(1);
//			personnel.setNom("GAILLARD");
//			personnelDAO.update(personnel);
//			System.out.println(personnel);
//			
//		}
//		catch(DALException e){
//			e.printStackTrace();	
//		}
	}

}
