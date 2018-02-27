package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Race;

public class TestRaceJDBC {
	
	
	public static void main(String[] args) throws DALException {
		
		
			System.out.println("Creation d'une race -----------------");
		//Création d'une race de cheval
			Race cheval = new Race("Alzan", "Cheval");
			RaceDAO daoRace = DAOFactory.getRaceDAO();
			//daoRace.insert(cheval);
			
		//Création d'une race de dragon
			Race dragon = new Race("Magyar à pointes", "Dragon");
			//daoRace.insert(dragon);
			
			System.out.println("Une race de Cheval : " + cheval
								+ "\nUne race de Dragon : " + dragon);
			
			System.out.println("\nModification d'un animal -----------------");
			
			String dragonAnciRace = dragon.getRace();
			
			dragon.setRace("Suédois à museau");
			//daoRace.update(dragon);
			
			String dragonNouvRace = dragon.getRace();
			System.out.println("Ancienne race de Dragon : " + dragonAnciRace 
							+ " \nNouvelle race de Dragon : " + dragonNouvRace);
			
			System.out.println("\nSélection d'une race -----------------");
			Race raceCheval = daoRace.selectById(cheval.getRace(), cheval.getEspece());
			
			System.out.println(raceCheval.toString());
			
			System.out.println("\nSuppression d'une race -----------------");
			daoRace.delete(dragon.getRace(), dragon.getEspece());
			
			
			
			
			System.out.println("\nSelect All : ");
			List<Race> listeRaces = daoRace.selectAll();
		
			for (Race uneRace : listeRaces){
				System.out.println(uneRace.toString());
			}
			
		
	}
}
