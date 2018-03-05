package fr.eni.clinique.ihm.ecranClient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.exempleMDI.EcranPrincipalClinique;

public class TableClient extends JTable {

	
	private String nom = "";
	private TableModelClient tableModelClient;
	
	public TableClient() {
		
		// récupère la liste de personnels depuis la base
		ClientManager clientManager = ClientManager.getInstance();
		List<Client> listeClients = null;
		try {
			listeClients = clientManager.getClientsByNom(nom);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tableModelClient = new TableModelClient(listeClients);
		
		tableModelClient.getColumnName(0);
		tableModelClient.getColumnName(1);
		tableModelClient.getColumnName(2);
		tableModelClient.getColumnName(3);
		
		this.addMouseListener(new MouseAdapter()
		{
		     public void mouseClicked(MouseEvent e)
		     {
			      if (e.getClickCount() == 2)
			      {
			    	 int indice_ligne = TableClient.this.getSelectedRow();
			    	 List<Client> clients = TableClient.this.getTableModelClient().getClientList();
			         try {
						RechercheClientDialog.getInstance().setVisible(false);
					} catch (DALException e1) {
						e1.printStackTrace();
					}
			         
			         EcranPrincipalClinique ecr = EcranPrincipalClinique.getInstance();
					ecr.setClientActif(clients.get(indice_ligne));
					ecr.refreshClientInfos();
			      }
		      }
		 } );
		
		
		 //affecte dans le modèle
		setModel(tableModelClient);
	}

	/**
	 * Accessible depuis l'instance TableModelPersonnel
	 * @return
	 */
	public TableModelClient getTableModelClient() {
		
		return tableModelClient;
	}
	
	public void setNomClient(String nom) {
		this.nom = nom;
		ClientManager clientManager = ClientManager.getInstance();

		try {
			tableModelClient.setClientList(clientManager.getClientsByNom(nom));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
