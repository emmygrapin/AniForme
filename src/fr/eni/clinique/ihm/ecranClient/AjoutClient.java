package fr.eni.clinique.ihm.ecranClient;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;

public class AjoutClient extends JDialog {

	
	private JTextField txtNom, txtPrenom, txtAdresse, txtAdresse2, txtVille, txtCP, txtPhone, txtEmail, txtAssurance;
	private JTextArea txtRemarque;
	private JButton btnValider, btnAnnuler;
	
	public AjoutClient() throws DALException
	{
		
		this.setTitle("Ajouter un Client");
		this.setContentPane(addClient());
		this.setSize(400, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setVisible(true);
		
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
		panelClient.add(getBtnSave(true, null), gbc);
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
		
		//Création du nouveau client
		public Client newClient() throws DALException
		{	
			Client client = new Client(0, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtAdresse2.getText(), txtCP.getText(), txtVille.getText(), txtPhone.getText(), txtAssurance.getText(), txtEmail.getText(), txtRemarque.getText(), false);
			return client;
		}
		
		
//-----------------------------------------BTN---------------------------------------------------
	public JButton getBtnCancel()
	{
		if(btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					AjoutClient.this.dispose();
				}
			});
		}
		return btnAnnuler;
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
					//ApplyController.getInstance().message("requiredFields");
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
						AjoutClient.this.setVisible(false);
					} catch (DALException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	
		return btnValider;
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
	
}
