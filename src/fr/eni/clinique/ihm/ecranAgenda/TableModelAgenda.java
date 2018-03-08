package fr.eni.clinique.ihm.ecranAgenda;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;

public class TableModelAgenda extends AbstractTableModel {
	private List<Agenda> listeAgendas;
	private Agenda agenda;

	private String[] column_Names = { "Heure", "Nom du client", "Animal", "Race" };

	public TableModelAgenda(List<Agenda> listeAgendas) {
		this.listeAgendas = listeAgendas;
	}

	public void setListeAgendas(List<Agenda> listeAgendas) {
		this.listeAgendas = listeAgendas;
		// lorsqu'un élément du tableau change
		fireTableDataChanged();
	}

	/**
	 * Combien de colonnes vont être affichées dans le tableau
	 */

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub

		return listeAgendas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object ret = null;
		switch (columnIndex) {
		case 0:
			ret = listeAgendas.get(rowIndex).getDateRdv().getTime();
			break;
		case 1:
			ret = listeAgendas.get(rowIndex).getAnimal().getClient().getNomClient();
			break;
		case 2:
			ret = listeAgendas.get(rowIndex).getAnimal().getNomAnimal();
			break;
		case 3:
			ret = listeAgendas.get(rowIndex).getAnimal().getRace().getRace();
			break;
		default:
			break;
		}
		return ret;
	}

	public Animal selectAnimal(int rowIndex) {
		Agenda rdv = listeAgendas.get(rowIndex);
		Animal animal = rdv.getAnimal();
		return animal;
	}

	public String getColumnName(int column) {
		return column_Names[column];
	}

}
