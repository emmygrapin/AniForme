package fr.eni.clinique.ihm.ecranClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.exempleMDI.EcranPrincipalClinique;

public class RechercheClientDialog extends JDialog{

	public static RechercheClientDialog instance;
	
	private TableClient tableClient;
	private JTextField txtRechercher;
	private JButton btnRechercher;

	private RechercheClientDialog() throws DALException
	{
		
		this.setTitle("Rechercher");
		this.setContentPane(chercheClient(""));
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public static RechercheClientDialog getInstance() throws DALException
	{
		if( RechercheClientDialog.instance == null)
		{
			RechercheClientDialog.instance = new RechercheClientDialog();
		}
		return RechercheClientDialog.instance;
	}
	
	/**
	 *  panel Global de Gestion du personnel contient le panel des boutons et le panel
	 *  des personnes
	 * @return
	 */
	public JPanel chercheClient(String recherche) throws DALException
	{
		
		ClientManager clientManager = ClientManager.getInstance() ;
		
		List<Client> clients = clientManager.getClientsByNom(recherche);
	
		JPanel panelClients = new JPanel();
		panelClients.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		int y = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelClients.add(new JLabel("Rechercher : "), gbc);
		gbc.gridx = 1;
		panelClients.add(addFieldRecherche(), gbc);	
		gbc.gridx = 2;
		panelClients.add(getBtnSearch(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		panelClients.add(new JScrollPane(getTableClient()), gbc);
		
				
		return panelClients;
	}



	// Getter
	public TableClient getTableClient() {
		
		if (tableClient == null) {
			
			this.tableClient = new TableClient();
			tableClient.setFillsViewportHeight(true);
			tableClient.setPreferredScrollableViewportSize(new Dimension(400, 200));
		}
		return tableClient;
	}
	
	
	
	//----------------------------------------BTN-------------------------------------------------
	

	
	public JButton getBtnSearch()
	{
		if(btnRechercher == null)
		{
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getTableClient().setNomClient(txtRechercher.getText());
					getTableClient().getTableModelClient().fireTableDataChanged();
				}
			});
		}
		return btnRechercher;
	}


	private JTextField addFieldRecherche()
	{
		if (this.txtRechercher == null) {
		this.txtRechercher = new JTextField(16);
	}
		return this.txtRechercher;
	}

}
