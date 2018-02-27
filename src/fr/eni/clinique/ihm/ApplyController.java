package fr.eni.clinique.ihm;

import java.awt.Container;
import java.util.List;

import javax.swing.JScrollPane;

import fr.eni.clinique.dal.DALException;

public class ApplyController {
		
	public static ApplyController instance;
	
	private clinique ecr;
	private int panel = 0;
	
	
	private ApplyController() throws DALException
	{
	
		ClientController clientController = ClientController.getinstance();
		//ReservationController reservationController = ReservationController.getinstance();
		//SpectacleController spectacleController = SpectacleController.getInstance();
		
		ecr = new clinique();
		ecr.setContentPane(clientController.viewClient());
		//ecr.setContentPane(spectacleController.viewSpectacle(new ArrayList()));
		
		Container contain = ecr.getContentPane();
		
		JScrollPane scroll = new JScrollPane(contain);
		ecr.setContentPane(scroll);	
		
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
		
		//ClientController clientController = ClientController.getinstance();
		//ReservationController reservationController = ReservationController.getinstance();
		//SpectacleController spectacleController = SpectacleController.getInstance();
		

		switch(menu)

		{
		 	case "listSpec":
		 		//ecr.setContentPane(spectacleController.viewSpectacle(liste));
			break;
			case "listResa":
				//ecr.setContentPane(reservationController.viewReservations());
			break;
			case "listClient":
				//ecr.setContentPane(clientController.viewClient());
			break;
			case "newResa":
				//ecr.setContentPane(reservationController.NewReservation((Spectacle) liste.get(0)));
			break;
		}

		Container contain = ecr.getContentPane();
		
		JScrollPane scroll = new JScrollPane(contain);
		ecr.setContentPane(scroll);	
		ecr.validate();
		ecr.repaint();
	}
	
}
