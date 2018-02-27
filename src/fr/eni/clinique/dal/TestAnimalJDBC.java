package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;

public class TestAnimalJDBC {

	public static void main(String[] args) throws DALException {
		
		
		Client marion = new Client(0, "Vasseur", "Marion", "une adresse à Angoulême", "adresse complément", "16015", "Angoulême", "0212451203", "Patoune assurance", "marion@mail.com", "des remarques ", false);
		ClientDAO daoCli = DAOFactory.getClientDAO();
		
		//daoCli.insert(marion);
		
		Race raceAmaran = new Race("Chat de Sybérie", "Chat");
		RaceDAO daoRace = DAOFactory.getRaceDAO();
		
		//daoRace.insert(raceAmaran);
		
		//Animal amaran = new Animal(0,"Amaran","M","brun et blanc",raceAmaran,marion,"non", "douleurs gencives",false);
		AnimalDAO daoAnim = DAOFactory.getAnimalDAO();
		
		//daoAnim.insert(amaran);
		//System.out.println(amaran.toString());
		
		Race raceSaiph = new Race("Européen tigré", "Chat" );
		//daoRace.insert(raceSaiph);
		
		Animal saiph = new Animal(1,"Saiph","F","noir et brun", raceSaiph, marion, "oui", "pas d'antécédents", false );
		//daoAnim.insert(saiph);
		
		System.out.println(saiph);
		System.out.println(saiph.toString());
		
	}
}
