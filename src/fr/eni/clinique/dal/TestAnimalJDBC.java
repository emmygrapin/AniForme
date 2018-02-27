package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;

public class TestAnimalJDBC {

	public static void main(String[] args) throws DALException {
		
		
	// Création du client Marion
		Client marion = new Client(1, "Vasseur", "Marion", "une adresse à Angoulême", "adresse complément", "16015", "Angoulême", "0212451203", "Patoune assurance", "marion@mail.com", "des remarques ", false);
		//ClientDAO daoCli = DAOFactory.getClientDAO();
		//daoCli.insert(marion);
		
		System.out.println("Creation d'un animal -----------------");
		
	//Création du chat Amaran
		Race raceAmaran = new Race("Chat de Sybérie", "Chat");
		RaceDAO daoRace = DAOFactory.getRaceDAO();
		//daoRace.insert(raceAmaran);
		Animal amaran = new Animal(0,"Amaran","M","brun et blanc",raceAmaran,marion,"non", "douleurs gencives",false);
		AnimalDAO daoAnim = DAOFactory.getAnimalDAO();	
		//daoAnim.insert(amaran);
		//System.out.println(amaran.toString());
		
	//Création du chat Saiph	
		Race raceSaiph = new Race("Européen tigré", "Chat" );
		//daoRace.insert(raceSaiph);
		Animal saiph = new Animal(12,"Saiph","F","noir et brun", raceSaiph, marion, "oui", "pas d'antécédents", false );
		//daoAnim.insert(saiph);
		
	//Création du chien Miro
		Race raceMiro = new Race("Golden Retriever", "Chien");
//		daoRace.insert(raceMiro);
//		System.out.println(raceMiro.toString());
		Animal miro = new Animal(13, "Miro", "M","doré",raceMiro,marion,"oui","pas d'antécédents", false);
//		daoAnim.insert(miro);
//		System.out.println(miro.toString());
		
		
		System.out.println(saiph.toString());
		
		
		
		System.out.println("Modification d'un animal -----------------");
	
		String saiphAnciCouleur = saiph.getCouleur();
		saiph.setCouleur("tigré brun");
		
		daoAnim.update(saiph);
		
		String saiphNouvCouleur = saiph.getCouleur();

		System.out.println("Ancienne couleur de Saiph : " + saiphAnciCouleur 
							+ " \nNouvelle couleur de Saiph : " + saiphNouvCouleur);
		
		System.out.println("Séléction par race -----------------");
		List<Animal> raceChats = daoAnim.selectByRace("tigré");
		System.out.println(raceChats);
		
		
		System.out.println("Sélection par espèce -----------------");
		List<Animal> especeChats = daoAnim.selectByEspece("Chat");
		System.out.println(especeChats);
		
		
	//Création du poisson Manet
		Race raceManet = new Race("combattant", "poisson");
		//daoRace.insert(raceManet);
		Animal manet = new Animal(14, "Manet", "F", "Rouge", raceManet, marion, "non", "pas d'antécédents", false);
		//daoAnim.insert(manet);
		Animal lePoisson = daoAnim.selectById(manet.getCodeAnimal());
		System.out.println("Sélection d'un animal -----------------");
		System.out.println(lePoisson);
		

		
		System.out.println("Suppression d'un animal -----------------");
	//Suppression du poisson Manet
		daoAnim.delete(manet.getCodeAnimal());
		
		
		
		System.out.println("\nSelect All : ");
		
		
		List<Animal> listeAnimaux = daoAnim.selectAll();
		
		for (Animal unAnimal : listeAnimaux) {
			
			System.out.println(unAnimal.toString());
		}
		
		
		
		
	}
}
