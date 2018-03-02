package fr.eni.clinique.ihm.ecranPersonnel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Personnel;

/**
 * m�thode override permettent de g�rer m�thodes affich�es
 * mod�le de donn�es
 * @author mdeoliveira2017
 *
 */
public class TableModelPersonnel extends AbstractTableModel{

	private List<Personnel> listePersonnels ;
	
	public TableModelPersonnel(List<Personnel> listePersonnels ) {
		this.listePersonnels = listePersonnels;
	}
	/**
	 * Combien de colonnes vont etre affich�es dans le tableau
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
	 * Appel� une fois pour l'affichage des cellules
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
			ret = listePersonnels.get(rowIndex).getMotDePasse();
			break;
			
		default:
			break;
		}
		return  ret;
	}
	
	

}
