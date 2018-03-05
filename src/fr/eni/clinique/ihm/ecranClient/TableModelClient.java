package fr.eni.clinique.ihm.ecranClient;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

/**
 * méthode override permettent de gérer méthodes affichées
 * modèle de données
 * @author mdeoliveira2017
 *
 */
public class TableModelClient extends AbstractTableModel{
	
	
	private List<Client> listeClients;
	private String[] listeHeader = {"Nom", "Prenom", "Code Postal", "Ville"};
	
	
	public TableModelClient(List<Client> listeClient ) {
		this.listeClients  = listeClient ;
	}
	
	public void setClientList(List<Client> listeClient ) {
		this.listeClients  = listeClient ;
	}
	
	public List<Client> getClientList() {
		return this.listeClients ;
	}
	
	
	/**
	 * Combien de colonnes vont etre affichées dans le tableau
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	/**
	 * le nombre de lignes du tableau
	 * @return taille
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeClients.size();
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
			ret = listeClients.get(rowIndex).getNomClient();
			break;
		case 1:
			ret = listeClients.get(rowIndex).getPrenomClient();
			break;
		case 2:
			ret = listeClients.get(rowIndex).getCodePostal();
			break;
		case 3:
			ret = listeClients.get(rowIndex).getVille();
			break;
			
		default:
			break;
		}
		return  ret;
	}
	
	public String getColumnName(int column) {
		return listeHeader[column];
	}
	
	
	
	

}
