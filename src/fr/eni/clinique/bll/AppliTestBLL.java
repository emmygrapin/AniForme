package fr.eni.clinique.bll;

import java.util.Date;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;

public class AppliTestBLL {

	public static void main(String[] args) throws BLLException, DALException {
		
		Race siamois = new Race("Siamois","Chat");
		Client thomas = new Client(0, "TeuFarsi","Thomas","2 rue du Chemin","","44000","Nantes","0256987436","animal assurance","thomas@mail.com","remarques", false); 
		Personnel emmy = new Personnel("admin","admin","ADM",false);
		Animal trufChat = new Animal(0,"Truffe","M", "blanc", siamois, thomas, "jiu4-tm", "", false );
		Date dateRdvF = new Date();
		
		Agenda agenda = new Agenda(emmy,dateRdvF,trufChat);
		AgendaManager testInsertionAgenda = AgendaManager.getInstance();
		testInsertionAgenda.addAgenda(agenda);
	}
}
