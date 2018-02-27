package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;

public class ClientController {
	
	private JTextField txtRechercher, txtNom, txtPrenom, txtAdresse, txtAdresse2, txtVille, txtCP, txtPhone, txtEmail, txtAssurance;
	private JTextArea txtRemarque;
	private JButton btnValider, btnAnnuler, btnRechercher;
	
	private static ClientController _instance;

	private ClientController()
	{
		
	}
	
	public static ClientController getinstance()
	{
		if(_instance ==  null)
		{
			ClientController._instance = new ClientController();
		}
		
		return _instance;
	}
	
	
	public JPanel addClient()
	{
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.LINE_START;
		
		//Colonne 1
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panelClient.add(getBtnSave(), gbc);
		gbc.gridx = 1;
		panelClient.add(getBtnCancel(), gbc);	
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelClient.add(new JLabel("Nom : "), gbc);
		gbc.gridy = 2;
		panelClient.add(new JLabel("Prenom : "), gbc);
		gbc.gridy = 3;
		panelClient.add(new JLabel("Adresse : "), gbc);
		gbc.gridy = 4;
		panelClient.add(new JLabel("Adresse 2 : "), gbc);
		gbc.gridy = 5;
		panelClient.add(new JLabel("Code Postale : "), gbc);
		gbc.gridy = 6;
		panelClient.add(new JLabel("Ville : "), gbc);
		gbc.gridy = 7;
		panelClient.add(new JLabel("Téléphone : "), gbc);
		gbc.gridy = 8;
		panelClient.add(new JLabel("Assurance : "), gbc);
		gbc.gridy = 9;
		panelClient.add(new JLabel("Email : "), gbc);
		gbc.gridy = 10;
		panelClient.add(new JLabel("Remarque : "), gbc);
		
		//Colonne 2
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelClient.add(addFieldNom(), gbc);
		gbc.gridy = 2;
		panelClient.add(addFieldPrenom(), gbc);
		gbc.gridy = 3;
		panelClient.add(addFieldAdresse(), gbc);
		gbc.gridy = 4;
		panelClient.add(addFieldAdresse2(), gbc);
		gbc.gridy = 5;
		panelClient.add(addFieldCP(), gbc);
		gbc.gridy = 6;
		panelClient.add(addFieldVille(), gbc);
		gbc.gridy = 7;
		panelClient.add(addFieldTelephone(), gbc);
		gbc.gridy = 8;
		panelClient.add(addFieldAssurance(), gbc);
		gbc.gridy = 9;
		panelClient.add(addFieldEmail(), gbc);
		gbc.gridy = 10;
		panelClient.add(addFieldRemarque(), gbc);		
		
		return panelClient;
	}
	
	
	//TextField
		private JTextField addFieldNom()
		{
			if (this.txtNom == null) {
				this.txtNom = new JTextField(20);
			}
			return this.txtNom;
		}
		
		private JTextField addFieldPrenom()
		{
			if (this.txtPrenom == null) {
				this.txtPrenom = new JTextField(20);
			}
			return this.txtPrenom;
		}
		
		private JTextField addFieldAdresse()
		{
			if (this.txtAdresse == null) {
				this.txtAdresse = new JTextField(20);
			}
			return this.txtAdresse;
		}
		
		private JTextField addFieldAdresse2()
		{
			if (this.txtAdresse2 == null) {
				this.txtAdresse2 = new JTextField(20);
			}
			return this.txtAdresse2;
		}
		
		private JTextField addFieldCP()
		{
			if (this.txtCP == null) {
				this.txtCP = new JTextField(20);
			}
			return this.txtCP;
		}
		
		private JTextField addFieldVille()
		{
			if (this.txtVille == null) {
				this.txtVille = new JTextField(20);
			}
			return this.txtVille;
		}
		
		private JTextField addFieldTelephone()
		{
			if (this.txtPhone == null) {
				this.txtPhone = new JTextField(20);
			}
			return this.txtPhone;
		}
		
		private JTextField addFieldAssurance()
		{
			if (this.txtAssurance == null) {
				this.txtAssurance = new JTextField(20);
			}
			return this.txtAssurance;
		}
		
		private JTextField addFieldEmail()
		{
			if (this.txtEmail == null) {
				this.txtEmail = new JTextField(20);
			}
			return this.txtEmail;
		}
		
		private JTextArea addFieldRemarque()
		{
			if (this.txtRemarque == null) {
				this.txtRemarque = new JTextArea(8, 20);
				this.txtRemarque.setLineWrap(true);
				this.txtRemarque.setWrapStyleWord(true);
			}
			return this.txtRemarque;
		}
		
		private JTextField addFieldRecherche()
		{
			if (this.txtRechercher == null) {
				this.txtRechercher = new JTextField(20);
			}
			return this.txtRechercher;
		}
	
		//Button
		public JButton getBtnSave()
		{
			if(btnValider == null)
			{
				btnValider = new JButton("Valider");
				btnValider.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty()  || txtAdresse.getText().isEmpty() || txtVille.getText().isEmpty() || txtCP.getText().isEmpty() || txtPhone.getText().isEmpty())
						{
							//ApplyController.getInstance().message("requiredFields");
						}
						else
						{
							try {
								Client client = newClient();
								ApplyController.getInstance().move("listResa", new ArrayList());
							} catch (DALException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
			}
			return btnValider;
		}
		
		public JButton getBtnCancel()
		{
			if(btnAnnuler == null)
			{
				btnAnnuler = new JButton("Annuler");
				btnAnnuler.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
					}
				});
			}
			return btnAnnuler;
		}
	
		//Création du nouveau client
		public Client newClient() throws DALException
		{	
			ClientManager clientManager = ClientManager.getInstance();
			Client client = new Client(0, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtAdresse2.getText(), txtCP.getText(), txtVille.getText(), txtPhone.getText(), txtAssurance.getText(), txtEmail.getText(), txtRemarque.getText(), false);
			System.out.println(client.toString());
			clientManager.addClient(client);
			return client;
		}
		
		
		
		//Panel d'affichage des clients
		public JPanel viewClient(String recherche) throws DALException
		{
			
			ClientManager clientManager = ClientManager.getInstance() ;
			
			List<Client> clients = clientManager.getClientsByNom(recherche);
		
			JPanel panelClients = new JPanel();
			panelClients.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			
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
			
			gbc.gridx = 0;
			gbc.gridwidth = 3;
			
			for(Client client:clients)
			{
				gbc.gridy = y;
				panelClients.add(viewUnClient(client), gbc);
				y++;
			}
			
			return panelClients;
		}
		
		

		//Panel d'affichage d'un client
		public JPanel viewUnClient(Client client)
		{
			JPanel panelClient = new JPanel();
			panelClient.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			//Espace entre les cases
			gbc.insets = new Insets(5, 5, 5, 5);
			//Alignement à gauche
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			
			
			
			// Ligne 1
			gbc.gridy = 0;
			
			gbc.gridx = 0;
			panelClient.add(new JLabel(client.getNomClient() +" - "), gbc);
			gbc.gridx = 1;
			panelClient.add(new JLabel(client.getPrenomClient() + " - "), gbc);
			gbc.gridx = 2;
			panelClient.add(new JLabel(client.getCodePostal()+ " - "), gbc);
			gbc.gridx = 3;
			panelClient.add(new JLabel(client.getVille()), gbc);
			gbc.gridx = 4;
			panelClient.add(new JLabel(client.getVille()), gbc);
			gbc.gridx = 5;
			panelClient.add(addModifierButton(client), gbc);
			
			return panelClient;
		}
		
		public JButton addModifierButton(Client client){
			JButton modifierButton = new JButton("Modifier");
			modifierButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
												
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			return modifierButton;	
		}
		
		public JButton getBtnSearch()
		{
			if(btnRechercher == null)
			{
				btnRechercher = new JButton("Rechercher");
				btnRechercher.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						List<String> listeString = new ArrayList<>();
						listeString.add(txtRechercher.getText());
						try {
							ApplyController.getInstance().move("listClient", listeString);
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
			return btnRechercher;
		}
		
		
		public JPanel upClient(Client client)
		{
			JPanel panelClient = new JPanel();
			panelClient.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			//Espace entre les cases
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.LINE_START;
			
			//Colonne 1
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			panelClient.add(getBtnSave(), gbc);
			gbc.gridx = 1;
			panelClient.add(getBtnCancel(), gbc);	
			
			gbc.anchor = GridBagConstraints.LINE_START;
			
			gbc.gridwidth = 1;
			gbc.gridx = 0;
			gbc.gridy = 1;
			panelClient.add(new JLabel("Nom : "), gbc);
			gbc.gridy = 2;
			panelClient.add(new JLabel("Prenom : "), gbc);
			gbc.gridy = 3;
			panelClient.add(new JLabel("Adresse : "), gbc);
			gbc.gridy = 4;
			panelClient.add(new JLabel("Adresse 2 : "), gbc);
			gbc.gridy = 5;
			panelClient.add(new JLabel("Code Postale : "), gbc);
			gbc.gridy = 6;
			panelClient.add(new JLabel("Ville : "), gbc);
			gbc.gridy = 7;
			panelClient.add(new JLabel("Téléphone : "), gbc);
			gbc.gridy = 8;
			panelClient.add(new JLabel("Assurance : "), gbc);
			gbc.gridy = 9;
			panelClient.add(new JLabel("Email : "), gbc);
			gbc.gridy = 10;
			panelClient.add(new JLabel("Remarque : "), gbc);
			
			//Colonne 2
			gbc.gridx = 1;
			gbc.gridy = 1;
			panelClient.add(addFieldNom(), gbc);
			gbc.gridy = 2;
			panelClient.add(addFieldPrenom(), gbc);
			gbc.gridy = 3;
			panelClient.add(addFieldAdresse(), gbc);
			gbc.gridy = 4;
			panelClient.add(addFieldAdresse2(), gbc);
			gbc.gridy = 5;
			panelClient.add(addFieldCP(), gbc);
			gbc.gridy = 6;
			panelClient.add(addFieldVille(), gbc);
			gbc.gridy = 7;
			panelClient.add(addFieldTelephone(), gbc);
			gbc.gridy = 8;
			panelClient.add(addFieldAssurance(), gbc);
			gbc.gridy = 9;
			panelClient.add(addFieldEmail(), gbc);
			gbc.gridy = 10;
			panelClient.add(addFieldRemarque(), gbc);		
			
			txtNom.setText(client.getNomClient());
			txtPrenom.setText(client.getPrenomClient());
			txtAdresse.setText(client.getAdresse1());
			txtAdresse2.setText(client.getAdresse2());
			txtCP.setText(client.getCodePostal());
			txtVille.setText(client.getVille());
			txtPhone.setText(client.getNumeroTel());
			txtAssurance.setText(client.getAssurance());
			txtEmail.setText(client.getEmail());
			txtRemarque.setText(client.getRemarque());
			
			return panelClient;
		}
		
		
		
		
		
		
}
