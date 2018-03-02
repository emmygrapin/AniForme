package fr.eni.clinique.ihm.ecranPersonnel;

import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Personnel;

public class TablePersonnel extends JTable {

	
	private TableModelPersonnel tableModPersonnel;
	
	public TablePersonnel() {
		// récupère la liste de personnels depuis la base
		PersonnelMger personnelManager = PersonnelMger.getInstance();
		List<Personnel> listePersonnels = personnelManager.getPersonnels();
		
		 tableModPersonnel = new TableModelPersonnel(listePersonnels);
		 
		 //affecte dans le modèle
		setModel(tableModPersonnel);
	}

	/**
	 * Accessible depuis l'instance TableModelPersonnel
	 * @return
	 */
	public TableModelPersonnel getTableModPersonnel() {
		
		return tableModPersonnel;
	}
	
	
	
}
