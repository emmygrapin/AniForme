package fr.eni.clinique.dal;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;

public class Test_JeuDonnees_ClientsRacesAnimaux {

	public static void main(String[] args) {
		
		// Récupération des DAO
		ClientDAO daoClient = DAOFactory.getClientDAO();
		RaceDAO daoRace = DAOFactory.getRaceDAO();
		AnimalDAO daoAnimal = DAOFactory.getAnimalDAO();
		
	try {
		
		System.out.println("Les clients ***********************************************");
	
		//instanciation d'Erika
		Client erika = new Client(0,"Kahuette","Erika","12 avenue de la République","","44300","Nantes","0236547895","Assurance anim","erika@mail.com","aucunes remarques", false);
		daoClient.insert(erika);
		
	// instanciation de Sarah	
		Client sarah = new Client(0, "Chide","Sarah","54 boulevard Jean Rostand","Bâtiment C","44000","Nantes","0658963274","Compagnie des animaux","sarah@mail.com","remarques", false);
		daoClient.insert(sarah);
		
	// instanciation de Thomas	
		Client thomas = new Client(0, "TeuFarsi","Thomas","2 rue du Chemin","","44000","Nantes","0256987436","animal assurance","thomas@mail.com","remarques", false);
		daoClient.insert(thomas);
		
	// Affichage de tous les clients	
		Client[] listeClients = {erika, sarah, thomas};
		
		for (Client unClient : listeClients) {
			System.out.println(daoClient.selectByNom(unClient.getNomClient()));
		}
		
		
	// instanciation d'espèces 
	
		System.out.println("Les races de Chat ***********************************************");
	
		// instanciation de races de chat
		Race siamois = new Race("Siamois","Chat");
		daoRace.insert(siamois);
		
		Race ragdoll = new Race("Ragdoll","Chat");
		daoRace.insert(ragdoll);
		
		Race maineCoon = new Race("Maine Coon","Chat");
		daoRace.insert(maineCoon);
		
		Race persan = new Race("Persan","Chat");
		daoRace.insert(persan);
		
		Race siberien = new Race("Sibérien","Chat");
		daoRace.insert(siberien);
		
		Race europeen = new Race("Européen","Chat");
		daoRace.insert(europeen);
		
		Race chatGouttiere = new Race("Chat de gouttière","Chat");
		daoRace.insert(chatGouttiere);
		
	// Affichage de toutes les races de Chat
		Race[] racesDeChat = {siamois, ragdoll, maineCoon, persan, siberien, europeen, chatGouttiere};
		
		for (Race uneRace : racesDeChat) {
			String espece = uneRace.getEspece();
			if (espece.equals("Chat")){
				System.out.println(daoRace.selectRacesByEspece(espece));
			}
		}
		
		System.out.println("Les races de Chien ***********************************************");
	
		// instanciation de races de chien
		Race goldenRetriever = new Race("Golden Retriever","Chien");
		daoRace.insert(goldenRetriever);
		
		Race rottweiler = new Race("Rottweiler","Chien");
		daoRace.insert(rottweiler);
		
		Race dalmatien = new Race("Dalmatien","Chien");
		daoRace.insert(dalmatien);
		
		Race caniche = new Race("Caniche","Chien");
		//daoRace.insert(caniche);
		
		Race bouledogueFr = new Race("Bouledogue Français","Chien");
		//daoRace.insert(bouledogueFr);
		
	// Affichage de toutes les races de Chien		
		Race[] racesDeChien = {goldenRetriever, rottweiler, dalmatien, caniche, bouledogueFr};
		
		for (Race uneRace : racesDeChien) {
			String espece = uneRace.getEspece();
			if (espece.equals("Chien")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		System.out.println("Les races de Rat ***********************************************");
		
		// instanciation de races de hamster
		Race noirRat = new Race("Rat noir","Rat");
		daoRace.insert(noirRat);
		
		Race dumboRat = new Race("Rat Dumbo","Rat");
		daoRace.insert(dumboRat);
		
		Race bleuRusseRat = new Race("Rat bleu Russe","Rat");
		daoRace.insert(bleuRusseRat);
		
	// Affichage de toutes les races de Chien		
		Race[] racesDeRat = {noirRat, dumboRat, bleuRusseRat};
		
		for (Race uneRace : racesDeRat) {
			String espece = uneRace.getEspece();
			if (espece.equals("Rat")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		System.out.println("Les races de Hamster ***********************************************");
	
		// instanciation de races de hamster
		Race nainHamster = new Race("Nain","Hamster");
		daoRace.insert(nainHamster);
		
		Race doreHamster = new Race("Doré","Hamster");
		daoRace.insert(doreHamster);
		
	// Affichage de toutes les races de Hamster
		Race[] racesDeHamster = {nainHamster, doreHamster};
		
		for (Race uneRace : racesDeHamster) {
			String espece = uneRace.getEspece();
			if (espece.equals("Hamster")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		System.out.println("Les races de Poisson ***********************************************");
	
		// instanciation de races de poisson
		Race poissonRouge = new Race("Poisson Rouge","Poisson");
		daoRace.insert(poissonRouge);
		
		Race combattant = new Race("Combattant","Poisson");
		daoRace.insert(combattant);
		
		Race poissonClown = new Race("Poisson Clown","Poisson");
		daoRace.insert(poissonClown);
		
		Race poissonChir = new Race("Poisson Chirurgien","Poisson");
		daoRace.insert(poissonChir);
		
	// Affichage de toutes les races de Poisson
		Race[] racesDePoisson = {poissonRouge, doreHamster, poissonClown, poissonChir};
		
		for (Race uneRace : racesDePoisson) {
			String espece = uneRace.getEspece();
			if (espece.equals("Poisson")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		System.out.println("Les races de Cheval ***********************************************");
	
		// instanciation de races de cheval
		Race shire = new Race("Shire","Cheval");
		daoRace.insert(shire);
		
		Race clydesdale = new Race("Clydesdale","Cheval");
		daoRace.insert(clydesdale);
		
		Race purSang = new Race("Pur sang","Cheval");
		daoRace.insert(purSang);
		
		Race shetland = new Race("Shetland","Cheval");
		daoRace.insert(shetland);
		
	// Affichage de toutes les races de Cheval
		Race[] racesDeCheval = {shire, clydesdale, purSang, shetland};
		
		for (Race uneRace : racesDeCheval) {
			String espece = uneRace.getEspece();
			if (espece.equals("Cheval")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		
		System.out.println("Les races de Perroquet ***********************************************");
	
		// instanciation de races de perroquet
		Race aras = new Race("Aras","Perroquet");
		daoRace.insert(aras);
		
		Race perruche = new Race("Perruche","Perroquet");
		daoRace.insert(perruche);
		
		Race grisGabon = new Race("Gris du Gabon","Perroquet");
		daoRace.insert(grisGabon);
		
	// Affichage de toutes les races de Perroquet
		Race[] racesDePerroquet = {aras, perruche, grisGabon};
		
		for (Race uneRace : racesDePerroquet) {
			String espece = uneRace.getEspece();
			if (espece.equals("Perroquet")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
		System.out.println("Les races de Mesange ***********************************************");
	
		// instanciation de races de mésange
		Race mesangeChar = new Race("Mésange charbonnière","Mésange");
		daoRace.insert(mesangeChar);
		
		Race mesangeBleue = new Race("Mésange bleue","Mésange");
		daoRace.insert(mesangeBleue);
		
	// Affichage de toutes les races de Mésange
		Race[] racesDeMesange = {mesangeChar, mesangeBleue};
		
		for (Race uneRace : racesDeMesange) {
			String espece = uneRace.getEspece();
			if (espece.equals("Mésange")){
				System.out.println("Espèce : " + uneRace.getEspece() + " - Race : " + uneRace.getRace());
			}
		}
		
	// instanciation d'animaux
		System.out.println("Liste de tous les animaux ***********************************************");
		
		Animal flufCheval = new Animal(0,"Fluffy","F", "brune", shetland, sarah, "Klas-gt12", "", false );
		daoAnimal.insert(flufCheval);
		
		Animal batCheval = new Animal(0,"Batman","M", "noire", purSang, sarah, "Blis-478", "", false );
		daoAnimal.insert(batCheval);
		
		Animal oranPoisson = new Animal(0,"Orangina","H", "jaune", combattant, thomas, "aucun", "", false );
		daoAnimal.insert(oranPoisson);
		
		Animal samChien = new Animal(0,"Sam","F", "noire et dorée", rottweiler, erika, "PRA852-lp", "", false );
		daoAnimal.insert(samChien);
		
		Animal trufChat = new Animal(0,"Truffe","M", "blanc", siamois, thomas, "jiu4-tm", "", false );
		daoAnimal.insert(trufChat);
		
		Animal takiusRat = new Animal(0,"Takius","F", "bleu", bleuRusseRat, thomas, "frzty", "", false );
		daoAnimal.insert(takiusRat);
		
	// Affichage de toutes les races de Mésange
		Animal[] racesDAnimaux = {flufCheval, batCheval, oranPoisson, samChien, trufChat, takiusRat};
		
		for (Animal unAnimal : racesDAnimaux) {
			int idAnimal = unAnimal.getCodeAnimal();
			
				System.out.println(daoAnimal.selectById(idAnimal));
		}
		
		
	} catch (DALException e) {
		e.printStackTrace();
	}
	
	}
}
