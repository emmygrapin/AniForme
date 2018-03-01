package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.PersonnelMger;

/**
 * 
 * @author egrapin2017
 *
 */
public class NewPersonnelController {
	private static NewPersonnelController _instance;
	private JTextField txtNomPersonnel, txtMotDePassePersonnel;
	private JComboBox comboRoles;

	private NewPersonnelController() {

	}

	public static NewPersonnelController getinstance() {
		if (_instance == null) {
			NewPersonnelController._instance = new NewPersonnelController();
		}

		return _instance;
	}

	/**
	 * panel de création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JPanel viewNewPersonnel() {
		JPanel panelNewPersonnel = new JPanel();
		
		panelNewPersonnel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelNewPersonnel.add(new JLabel("Nom:"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelNewPersonnel.add(viewNewNomPersonnel(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelNewPersonnel.add(new JLabel("Mot de passe:"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelNewPersonnel.add(viewNewMotDePasse(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelNewPersonnel.add(new JLabel("Rôle:"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelNewPersonnel.add(viewNewRole(), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelNewPersonnel.add(buttonValiderNewPersonnel(), gbc);
		return panelNewPersonnel;

	}

	/**
	 * Champs Nom à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JTextField viewNewNomPersonnel() {
		JTextField txtNomPersonnel = new JTextField(30);
		return txtNomPersonnel;
	}

	/**
	 * Champs Mot de passe à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JTextField viewNewMotDePasse() {
		JTextField txtMotDePassePersonnel = new JPasswordField(20);
		return txtMotDePassePersonnel;
	}

	/**
	 * Combo Box pour choisir le rôle à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JComboBox viewNewRole() {
		Vector<String> roles = new Vector<String>();
		roles.add("VET");
		roles.add("ADM");
		roles.add("SEC");

		comboRoles = new JComboBox(roles);

		return comboRoles;
	}

	public JButton buttonValiderNewPersonnel() {
		JButton buttonValider = new JButton("Valider");

		// Action du bouton Valider de création d'un nouveau personnel
		buttonValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					String nomSaisi = txtNomPersonnel.getText();
					String motDePasseSaisi = txtMotDePassePersonnel.getText();
					String roleChoisi = comboRoles.getSelectedItem().toString();

					if (!nomSaisi.isEmpty() && !motDePasseSaisi.isEmpty() && !roleChoisi.isEmpty()) {
						PersonnelMger.getInstance().addPersonnel(nomSaisi, motDePasseSaisi, roleChoisi);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		return buttonValider;
	}

}
