package fr.eni.clinique.ihm;

import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.ecranAnimal.AnimalController;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelGestion;

/**
 * 
 * @author egrapin2017
 *
 */
public class ApplyController {
		
	public static ApplyController instance;
	
	private login ecr;
	private clinique cli;
	private int panel = 0;
	
	
	
	private ApplyController() throws DALException
	{	
		AnimalController animalController = AnimalController.getInstance();
		ClientController clientController = ClientController.getinstance();
		//ecr = new login();
		
		cli = new clinique();
		//cli.setContentPane(animalController.viewGestionAddAnimal());
		PersonnelGestion ecranPersonnel = new PersonnelGestion();
		ecranPersonnel.setVisible(true);
		
		//cli.setContentPane(clientController.globalClient(null));
		
	}
	
	public static ApplyController getInstance() throws DALException
	{
		if( ApplyController.instance == null)
		{
			ApplyController.instance = new ApplyController();
		}
		return ApplyController.instance;
	}
	
	public void move(String menu, List liste) throws DALException
	{
		
		ClientController clientController = ClientController.getinstance();
		AnimalController animalController = AnimalController.getInstance();

		switch(menu)

		{
			case "globalClient":
				cli.setContentPane(clientController.globalClient((Client)liste.get(0)));
			break;
			
			case "listAnim":
				cli.setContentPane(animalController.viewAnimaux());
			break;
			case "newAnim":
				cli.setContentPane(animalController.viewGestionAddAnimal());
			break;
			case "listePersonnels":
				PersonnelGestion ecranPersonnel = new PersonnelGestion();
				ecranPersonnel.setVisible(true);
				break;
				
		}
		
		cli.validate();
		cli.repaint();
		
//		ecr.validate();
//		ecr.repaint();
	}

}
