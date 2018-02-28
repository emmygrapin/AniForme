package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.ihm.AuthentifierController;


/**
 * 
 * @author egrapin2017
 * Panel contenu dans la boîte de dialogue d'authentification
 */
public class AuthentifierController {
	private JTextField txtNom, txtMotDePasse;
	private JButton buttonValider;

	private static AuthentifierController _instance;

	public AuthentifierController() {

	}

	public static AuthentifierController getInstance() {
		if (_instance == null) {
			AuthentifierController._instance = new AuthentifierController();
		}
		return _instance;

	}

	// Panel d'affichage de l'authentification
	public JPanel viewAuthentification() {

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
		panelAuthentification.add(this.addButtonValider(), gbc);

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
	public JButton addButtonValider() {
		this.buttonValider = new JButton("Valider");

		// Action du bouton Valider d'authentification
		buttonValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String identifiantSaisi = txtNom.getText();
					String motDePasseSaisi = txtMotDePasse.getText();
					LoginMger.getInstance().getConnexion(identifiantSaisi, motDePasseSaisi);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		return buttonValider;

	}
}
