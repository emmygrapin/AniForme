package fr.eni.clinique.ihm.ecranAnimal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Animal;

/**
 * m�thode override permettent de g�rer m�thodes affich�es
 * mod�le de donn�es
 * @author mdeoliveira2017
 *
 */
public class TableModelAnimal extends AbstractTableModel{

	private List<Animal> listeAnimaux ;
	private Animal animal;
	
	private String[] column_Names = {"Num�ro", "Nom", "Sexe", "Couleur", "Race", "Esp�ce", "Tatouage"};

	public void setListeAnimaux(List<Animal> listeAnimaux) {
		this.listeAnimaux = listeAnimaux;
		// lorsqu'un �l�ment du tableau change
		fireTableDataChanged();
	}
	
	public TableModelAnimal(List<Animal> listeAnimaux ) {
		this.listeAnimaux = listeAnimaux;
	}
	/**
	 * Combien de colonnes vont �tre affich�es dans le tableau
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	/**
	 * le nombre de lignes du tableau
	 * @return taille
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeAnimaux.size();
	}

	/**
	 * Appel� une fois pour l'affichage des cellules
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object ret = null;
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			ret = listeAnimaux.get(rowIndex).getCodeAnimal();
			break;
		case 1:
			ret = listeAnimaux.get(rowIndex).getNomAnimal();
			break;
		case 2:
			ret = listeAnimaux.get(rowIndex).getSexe();
			break;
		case 3:
			ret = listeAnimaux.get(rowIndex).getCouleur();
			break;
		case 4:
			ret = listeAnimaux.get(rowIndex).getRace().getRace();
			break;
		case 5:
			ret = listeAnimaux.get(rowIndex).getRace().getEspece();
			break;
		case 6:
			ret = listeAnimaux.get(rowIndex).getTatouage();
			break;
		default:
			break;
		}
		return  ret;
	}
	
	public Animal selectAnimal(int rowIndex) {
		animal = listeAnimaux.get(rowIndex);
		
		return animal;
	}
	
	
	public String getColumnName(int column) {
		return column_Names[column];
	}
	
	

}
