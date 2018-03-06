package fr.eni.clinique.ihm.ecranAnimal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;

public class TableAnimal extends JTable implements MouseListener {

	private Animal animalSelect;
	

	private TableModelAnimal tableModAnimal;
	
	public TableAnimal(Client client) {
		// récupère la liste de personnels depuis la base
		AnimalManager animalManager = AnimalManager.getInstance();
		List<Animal> listeAnimaux = null;
		try {
			listeAnimaux = animalManager.getAnimauxByClient(client);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		tableModAnimal = new TableModelAnimal(listeAnimaux);
		tableModAnimal.getColumnName(0);
		tableModAnimal.getColumnName(1);
		tableModAnimal.getColumnName(2);
		tableModAnimal.getColumnName(3);
		tableModAnimal.getColumnName(4);
		tableModAnimal.getColumnName(5);
		tableModAnimal.getColumnName(6);
		 
		 //affecte dans le modèle
		setModel(tableModAnimal);
		this.addMouseListener(this);
	}

	/**
	 * Accessible depuis l'instance TableModelPersonnel
	 * @return
	 */
	public TableModelAnimal getTableModAnimal() {
		
		return tableModAnimal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		animalSelect = tableModAnimal.selectAnimal(getSelectedRow());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	public Animal getAnimalSelect() {
		return animalSelect;
	}
	
	public void setAnimalSelect(Animal animalSelect) {
		this.animalSelect = animalSelect;
	}
	
	
}
