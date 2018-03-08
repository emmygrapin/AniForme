package fr.eni.clinique.ihm.ecranAgenda;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;

public class TableAgenda extends JTable implements MouseListener {
	private Animal animalRdvSelect;

	private TableModelAgenda tableModAgenda;
	private Personnel personnel;


	public TableAgenda(Personnel perso){
		AgendaManager agendaManager = AgendaManager.getInstance();
		List<Agenda> listeAgendas = new ArrayList<>();
		try {
			listeAgendas = agendaManager.getAgendasParVeto(perso.getCodePerso());
		
		tableModAgenda = new TableModelAgenda(listeAgendas);
		tableModAgenda.getColumnName(0);
		tableModAgenda.getColumnName(1);
		tableModAgenda.getColumnName(2);
		tableModAgenda.getColumnName(3);
		
		 //affecte dans le modèle
		setModel(tableModAgenda);
		this.addMouseListener(this);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		animalRdvSelect = tableModAgenda.selectAnimal(getSelectedRow());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public TableModelAgenda getTableModAgenda() {
		return tableModAgenda;
	}
	
	public Animal getAnimalRdvSelect() {
		return animalRdvSelect;
	}

	public void setAnimalRdvSelect(Animal animalRdvSelect) {
		this.animalRdvSelect = animalRdvSelect;
	}
	
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
		List<Agenda> listeAgendas = new ArrayList<>();
		AgendaManager agendaManager = AgendaManager.getInstance();
		try {
			listeAgendas = agendaManager.getAgendasParVeto(personnel.getCodePerso());
			tableModAgenda.setListeAgendas(listeAgendas);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
