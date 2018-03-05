package fr.eni.clinique.ihm.ecranPersonnel;


import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.sun.rowset.internal.Row;

import fr.eni.clinique.bo.Personnel;


/**
 * méthode override permettent de gérer méthodes affichées
 * modèle de données
 * @author mdeoliveira2017
 *
 */
public class TableModelPersonnel extends AbstractTableModel{
	private String column_names[]= {"Nom","Rôle","Mot de passe"};
	private List<Personnel> listePersonnels;
	private Personnel personnel;
	private Row ligne;
	
	public TableModelPersonnel(){
	
	}
	
	public void setListePersonnels(List<Personnel> listePersonnels) {
		this.listePersonnels = listePersonnels;
		fireTableDataChanged();
	}
	public TableModelPersonnel(List<Personnel> listePersonnels ) {
		this.listePersonnels = listePersonnels;
	}
	public Personnel selectPersonnel(int rowIndex){
		personnel = listePersonnels.get(rowIndex);
		return personnel;
		
	}
	
	 public String getColumnName(int column) {
	        return column_names[column];
	    }
	/**
	 * Combien de colonnes vont etre affichées dans le tableau
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	/**
	 * le nombre de lignes du tableau
	 * @return taille
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listePersonnels.size();
	}
	
	
	/**
	 * Appelé une fois pour l'affichage des cellules
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object ret = null;
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			ret = listePersonnels.get(rowIndex).getNom();
			break;
		case 1:
			ret = listePersonnels.get(rowIndex).getRole();
			break;
		case 2:
			String motDePasse = "**********";
			ret = motDePasse;
			break;
			
		default:
			break;
		}
		return  ret;
	}
	


}
