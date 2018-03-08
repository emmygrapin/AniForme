package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.ecranPrincipal.EcranPrincipalClinique;

/**
 * 
 * @author egrapin2017
 * Boîte de dialogue d'authentification
 */
public class login extends JDialog{
	
	private JTextField txtNom, txtMotDePasse;
	private JButton buttonValider;
	private JOptionPane alert;
	
	// JDialog modal avec JFrame en parent paramètre + super
	public login(JFrame parent){
		super(parent, true);
	
	this.setTitle("Authentification");
	this.setContentPane(viewAuthentification(parent));
	this.setSize(500, 200);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	
	}
	
	public JPanel viewAuthentification(JFrame parent) {

		JPanel panelAuthentification = new JPanel();
		panelAuthentification.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridy = 0;
		panelAuthentification.add(new JLabel("Identifiant"), gbc);
		gbc.gridy = 1;
		panelAuthentification.add(new JLabel("Mot De Passe"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAuthentification.add(this.addFieldIdentifiant(), gbc);
		gbc.gridy = 1;
		panelAuthentification.add(this.addFieldMotDePasse(), gbc);
		gbc.gridy = 2;
		panelAuthentification.add(this.addButtonValider((EcranPrincipalClinique)parent), gbc);

		return panelAuthentification;
	}
	// Champs identifiant de l'utilisateur
	public JTextField addFieldIdentifiant() {
		this.txtNom = new JTextField(20);
		return txtNom;
	}
	// Champs mot de passe de l'utilisateur
	public JTextField addFieldMotDePasse() {
		this.txtMotDePasse = new JPasswordField(20);
		return txtMotDePasse;
	}
	// Bouton valider qui génère l'action du login manager en lui passant l'identifiant
	// et le mot de passe saisis
	public JButton addButtonValider(EcranPrincipalClinique parent) {
		this.buttonValider = new JButton("Valider");

		// Action du bouton Valider d'authentification
		buttonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String identifiantSaisi = txtNom.getText();
					String motDePasseSaisi = txtMotDePasse.getText();
					Personnel personnel =LoginMger.getInstance().getConnexion(identifiantSaisi, motDePasseSaisi);
					login.this.setVisible(false);
					parent.setPersonnelActif(personnel);
					parent.refreshMenu();
				} catch (BLLException e1) {
					alert.showMessageDialog(null, e1.getMessage(), "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		return buttonValider;

	}
		
}
