package fr.eni.clinique.ihm;

import javax.swing.JFrame;

import fr.eni.clinique.dal.DALException;

public class PersonnelGestion extends JFrame{

	public PersonnelGestion() throws DALException{
		this.setTitle("Gestion du personnel");
		PersonnelController personnelController = PersonnelController.getinstance();
		// R�glage de la taille du conteneur
		this.setSize(900, 800);
		
		// R�glage de la position du conteneur
		this.setLocationRelativeTo(null);
	
		this.setContentPane(personnelController.viewGestionPersonnels());
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// J'affiche la fen�tre
		this.setVisible(true);
	}
}
