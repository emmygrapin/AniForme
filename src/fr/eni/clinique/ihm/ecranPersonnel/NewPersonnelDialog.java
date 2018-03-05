package fr.eni.clinique.ihm.ecranPersonnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.PersonnelMger;

public class NewPersonnelDialog extends JDialog{

	private JTextField txtNomPersonnel, txtMotDePassePersonnel;
	private JComboBox comboRoles;
	private JButton validerNewPersonnel, annulerNewPersonnel;
	private PersonnelGestion personnelGestion;
	private JOptionPane alert;
	
	public NewPersonnelDialog(JFrame parent){
		super(parent, true);
		this.setTitle("Nouveau personnel");
		this.setContentPane(viewNewPersonnel());
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
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
		panelNewPersonnel.add(getNomNewPersonnel(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelNewPersonnel.add(new JLabel("Mot de passe:"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelNewPersonnel.add(getMotDePasseNewPersonnel(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelNewPersonnel.add(new JLabel("Rôle:"),gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelNewPersonnel.add(getRoleNewPersonnel(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelNewPersonnel.add(buttonValiderNewPersonnel(), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelNewPersonnel.add(buttonAnnulerNewPersonnel(),gbc);
		return panelNewPersonnel;

	}

	/**
	 * Champs Nom à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JTextField getNomNewPersonnel() {
		if(txtNomPersonnel == null)
		{ 
			txtNomPersonnel = new JTextField(30);
		}
		return txtNomPersonnel;
	}

	/**
	 * Champs Mot de passe à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JTextField getMotDePasseNewPersonnel() {
		if (txtMotDePassePersonnel == null)
		{
		txtMotDePassePersonnel = new JPasswordField(20);}
		return txtMotDePassePersonnel;
	}

	/**
	 * Combo Box pour choisir le rôle à la création d'un nouveau personnel
	 * 
	 * @return
	 */
	public JComboBox getRoleNewPersonnel() {
		Vector<String> roles = new Vector<String>();
		if(comboRoles ==  null){
			roles.add("VET");
			roles.add("ADM");
			roles.add("SEC");
		comboRoles = new JComboBox(roles);
		
		}
		return comboRoles;
	}

	public JButton buttonValiderNewPersonnel() {
		validerNewPersonnel = new JButton("Valider");

		// Action du bouton Valider de création d'un nouveau personnel
		validerNewPersonnel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					String nomSaisi = txtNomPersonnel.getText();
					String motDePasseSaisi = txtMotDePassePersonnel.getText();
					String roleChoisi = comboRoles.getSelectedItem().toString();

					if (!nomSaisi.isEmpty() && !motDePasseSaisi.isEmpty() && !roleChoisi.isEmpty()) {
						//ajout d'un nouveau personnel dans la bdd
						PersonnelMger.getInstance().addPersonnel(nomSaisi, motDePasseSaisi, roleChoisi);
						//fermer la boite de dialogue
						NewPersonnelDialog.this.setVisible(false);
						
					}
				} catch (Exception e1) {
					alert.showMessageDialog(null, "Cet identifiant existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		return validerNewPersonnel;
	}
	public JButton buttonAnnulerNewPersonnel() {
		annulerNewPersonnel = new JButton("Annuler");

		// Action du bouton Valider de création d'un nouveau personnel
		annulerNewPersonnel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						NewPersonnelDialog.this.setVisible(false);
						
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return annulerNewPersonnel;
	}
}
