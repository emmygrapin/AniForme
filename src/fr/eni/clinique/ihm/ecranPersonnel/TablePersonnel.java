package fr.eni.clinique.ihm.ecranPersonnel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Personnel;

public class TablePersonnel extends JTable implements MouseListener{

	
	private TableModelPersonnel tableModPersonnel;
	private Personnel personnelSelect;
	
	public TablePersonnel() {
		// récupère la liste de personnels depuis la base
		PersonnelMger personnelManager = PersonnelMger.getInstance();
		List<Personnel> listePersonnels = personnelManager.getPersonnels();
		
		 tableModPersonnel = new TableModelPersonnel(listePersonnels);
		 tableModPersonnel.getColumnName(0);
		 tableModPersonnel.getColumnName(1);
		 tableModPersonnel.getColumnName(2);
		 //affecte dans le modèle
		setModel(tableModPersonnel);
		this.addMouseListener(this);
	}

	/**
	 * Accessible depuis l'instance TableModelPersonnel
	 * @return
	 */
	public TableModelPersonnel getTableModPersonnel() {
		
		return tableModPersonnel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		personnelSelect = tableModPersonnel.selectPersonnel(getSelectedRow());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Personnel getPersonnelSelect() {
		return personnelSelect;
	}

	public void setPersonnelSelect(Personnel personnelSelect) {
		this.personnelSelect = personnelSelect;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
