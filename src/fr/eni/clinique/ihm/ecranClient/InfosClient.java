package fr.eni.clinique.ihm.ecranClient;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.ecranAnimal.AnimalGestion;

public class InfosClient extends JInternalFrame{

	private JTextField txtNom, txtPrenom, txtAdresse, txtAdresse2, txtVille, txtCP, txtPhone, txtEmail, txtAssurance;
	private JTextArea txtRemarque;
	private JButton btnValider, btnAnnuler, btnArchiver;
	private Client clientActif;
	
	public InfosClient() throws DALException{
		
		super("Gestion des clients", true, true, true,true);
		
		// Réglage de la taille du conteneur
		this.setSize(1000, 800);
	
		this.setContentPane(globalClient());
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
	
	public JPanel upClient()
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
		//panelClient.add(getBtnSave(false, client), gbc);
		gbc.gridx = 1;
		//panelClient.add(getBtnCancel(), gbc);	
		
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
		
		if( clientActif != null)
		{
			txtNom.setText(clientActif.getNomClient());
			txtPrenom.setText(clientActif.getPrenomClient());
			txtAdresse.setText(clientActif.getAdresse1());
			txtAdresse2.setText(clientActif.getAdresse2());
			txtCP.setText(clientActif.getCodePostal());
			txtVille.setText(clientActif.getVille());
			txtPhone.setText(clientActif.getNumeroTel());
			txtAssurance.setText(clientActif.getAssurance());
			txtEmail.setText(clientActif.getEmail());
			txtRemarque.setText(clientActif.getRemarque());
		}
		else
		{
			txtNom.setText(null);
			txtPrenom.setText(null);
			txtAdresse.setText(null);
			txtAdresse2.setText(null);
			txtCP.setText(null);
			txtVille.setText(null);
			txtPhone.setText(null);
			txtAssurance.setText(null);
			txtEmail.setText(null);
			txtRemarque.setText(null);
		}
		return panelClient;
	}
	
	public JPanel globalClient()
	{
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panelClient.add(MenuClient(), gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelClient.add(upClient(), gbc);
		if(clientActif != null)
		{
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.anchor = GridBagConstraints.LINE_END;
			AnimalGestion animaux;
			try {
				animaux = new AnimalGestion(clientActif);
				panelClient.add(animaux.viewGestionAnimaux(), gbc);
			} catch (DALException e) {
				e.printStackTrace();
			}
			
		}
		
		return panelClient;
	}
	
	
	public JPanel MenuClient()
	{
		JPanel menuClient = new JPanel();
		menuClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 30, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		menuClient.add(addRechercheButton(), gbc);
		gbc.gridx = 1;
		menuClient.add(getBtnAjouter(), gbc);
		gbc.gridx = 2;
		menuClient.add(getBtnArchiver(), gbc);
		gbc.gridx = 3;
		menuClient.add(getBtnSave(false, clientActif), gbc);
		gbc.gridx = 4;
		menuClient.add(addCancelButton(), gbc);
		
		
		return menuClient;
	}
	
	
	//Création du nouveau client
	public Client newClient()
	{	
		Client client = new Client(0, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtAdresse2.getText(), txtCP.getText(), txtVille.getText(), txtPhone.getText(), txtAssurance.getText(), txtEmail.getText(), txtRemarque.getText(), false);
		return client;
	}
	
	public void refresh() 
	{	
		this.setContentPane(globalClient());
		this.validate();
		this.repaint();
	}
	
	

	//--------------------------BTN------------------------------------------
	
	public JButton addCancelButton(){
		JButton routingButton = new JButton("Annuler");
		routingButton.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				InfosClient.this.refresh();
		}
	});
		return routingButton;
	}
	
	
	public JButton addRechercheButton(){
		JButton routingButton = new JButton("Rechercher");
		routingButton.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RechercheClientDialog rechercheEcran = RechercheClientDialog.getInstance();
					rechercheEcran.setVisible(true);
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return routingButton;	
	}


	public JButton addModifierButton(Client client){
		JButton modifierButton = new JButton("Ouvrir");
		modifierButton.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Client> listeClient = new ArrayList<>();
				listeClient.add(client);
				try {
					ApplyController.getInstance().move("globalClient", listeClient);
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return modifierButton;	
	}
	

	public JButton getBtnSave(boolean newClient, Client upclient)
	{
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty()  || txtAdresse.getText().isEmpty() || txtVille.getText().isEmpty() || txtCP.getText().isEmpty() || txtPhone.getText().isEmpty())
				{
					
				}
				else
				{
					try {
						Client client = newClient();
						ClientManager clientManager = ClientManager.getInstance();
						if(newClient)
						{
							clientManager.addClient(client);
						}
						else
						{
							client.setCodeClient(upclient.getCodeClient());
							clientManager.updateClient(client);
						}
						
					} catch (DALException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	
		return btnValider;
	}

//	public JButton getBtnCancel()
//	{
//		if(btnAnnuler == null)
//		{
//			btnAnnuler = new JButton("Annuler");
//			btnAnnuler.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e)
//				{
//					try {
//						ApplyController.getInstance().move("globalClient", new ArrayList());
//					} catch (DALException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			});
//		}
//		return btnAnnuler;
//	}
		
	
	public JButton getBtnAjouter()
	{
		JButton routingButton = new JButton("Ajouter");
		routingButton.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AjoutClient ajoutEcran = new AjoutClient();
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return routingButton;	
	}


	public JButton getBtnArchiver()
	{
			btnArchiver = new JButton("Supprimer");
			btnArchiver.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(clientActif != null)
					{
						 JOptionPane jop = new JOptionPane();    	
					      int option = jop.showConfirmDialog(null, "Voulez-vous supprimer ce client ?", "Client supprimé.", 
					        JOptionPane.YES_NO_OPTION, 
					        JOptionPane.QUESTION_MESSAGE);

					      if(option == JOptionPane.OK_OPTION){
					    		try {
					    			clientActif.setArchive(true);
									ClientManager.getInstance().updateClient(clientActif);
									AnimalManager.getInstance().updateIsArchiveByClient(clientActif);
									InfosClient.this.clientActif = null;
									
									InfosClient.this.refresh();
								} catch (DALException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}    	
					      }
					}
				}
			});
		return btnArchiver;
	}
	
	 

	
	
	
//------------------------------------TextField-------------------------------------------------------------
	
	
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
	
	
	public void setClientActif(Client client)
	{
		clientActif = client;
	}
}
