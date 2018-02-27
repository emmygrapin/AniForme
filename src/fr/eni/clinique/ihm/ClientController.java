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
	
	private JTextField txtNom, txtPrenom, txtAdresse, txtAdresse2, txtVille, txtCP, txtPhone, txtEmail, txtAssurance;
	private JTextArea txtRemarque;
	private JButton btnValider, btnAnnuler;
	
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
		JPanel panelReservation = new JPanel();
		panelReservation.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.LINE_START;
		
		//Colonne 1
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panelReservation.add(getBtnSave(), gbc);
		gbc.gridx = 1;
		panelReservation.add(getBtnCancel(), gbc);	
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelReservation.add(new JLabel("Nom : "), gbc);
		gbc.gridy = 2;
		panelReservation.add(new JLabel("Prenom : "), gbc);
		gbc.gridy = 3;
		panelReservation.add(new JLabel("Adresse : "), gbc);
		gbc.gridy = 4;
		panelReservation.add(new JLabel("Adresse 2 : "), gbc);
		gbc.gridy = 5;
		panelReservation.add(new JLabel("Code Postale : "), gbc);
		gbc.gridy = 6;
		panelReservation.add(new JLabel("Ville : "), gbc);
		gbc.gridy = 7;
		panelReservation.add(new JLabel("Téléphone : "), gbc);
		gbc.gridy = 8;
		panelReservation.add(new JLabel("Assurance : "), gbc);
		gbc.gridy = 9;
		panelReservation.add(new JLabel("Email : "), gbc);
		gbc.gridy = 10;
		panelReservation.add(new JLabel("Remarque : "), gbc);
		
		//Colonne 2
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelReservation.add(addFieldNom(), gbc);
		gbc.gridy = 2;
		panelReservation.add(addFieldPrenom(), gbc);
		gbc.gridy = 3;
		panelReservation.add(addFieldAdresse(), gbc);
		gbc.gridy = 4;
		panelReservation.add(addFieldAdresse2(), gbc);
		gbc.gridy = 5;
		panelReservation.add(addFieldCP(), gbc);
		gbc.gridy = 6;
		panelReservation.add(addFieldVille(), gbc);
		gbc.gridy = 7;
		panelReservation.add(addFieldTelephone(), gbc);
		gbc.gridy = 8;
		panelReservation.add(addFieldAssurance(), gbc);
		gbc.gridy = 9;
		panelReservation.add(addFieldEmail(), gbc);
		gbc.gridy = 10;
		panelReservation.add(addFieldRemarque(), gbc);		
		
		return panelReservation;
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
		public JPanel viewClient() throws DALException
		{
			
			ClientManager clientManager = ClientManager.getInstance() ;
			
			List<Client> clients = clientManager.getClients();
		
			JPanel panelClients = new JPanel();
			panelClients.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			//Espace entre les cases
			gbc.insets = new Insets(5, 5, 5, 5);
			int y = 0;
			gbc.gridx = 0;
		
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
			gbc.anchor = GridBagConstraints.LINE_START;
			
			
			
			// Ligne 1
			gbc.gridy = 0;
			
			gbc.gridx = 0;
			panelClient.add(new JLabel(client.getNomClient()), gbc);
			gbc.gridx = 1;
			panelClient.add(new JLabel(client.getPrenomClient()), gbc);
			gbc.gridx = 2;
			panelClient.add(new JLabel(client.getEmail()), gbc);
			gbc.gridx = 3;
			
			
			return panelClient;
		}
		
		
		
		
		
		
		
		
		
		
		
}
