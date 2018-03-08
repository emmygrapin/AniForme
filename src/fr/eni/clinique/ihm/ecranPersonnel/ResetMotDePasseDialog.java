package fr.eni.clinique.ihm.ecranPersonnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Personnel;
/**
 * boîte de dialogue du changement de mot de passe utilisateur
 * @author egrapin2017
 *
 */
public class ResetMotDePasseDialog extends JDialog {
	JDialog boiteDialog = new JDialog(this, "resetMotDePasse", false);
	private JTextField txtMotDePasse;
	private JButton buttonValider;
	private Personnel personnel;
	
	public ResetMotDePasseDialog(Personnel personnel,JFrame parent) {
		super(parent, true);
		this.setTitle("Réinitialisation du mot de passe");
		this.setContentPane(viewChangementMotDePasse(personnel));
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	/**
	 * panel de changement de mot de passe
	 * @param personnel
	 * @return
	 */
		public JPanel viewChangementMotDePasse(Personnel personnel) {
			JPanel changementMotDePassePanel = new JPanel();
			changementMotDePassePanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.gridx = 0;
			gbc.gridy = 0;
			changementMotDePassePanel.add(new JLabel("Nouveau mot de passe: "));
			gbc.gridx = 1;
			changementMotDePassePanel.add(addFieldMotDePasse(), gbc);
			gbc.gridx = 2;
			changementMotDePassePanel.add(addButtonValider(personnel), gbc);
			return changementMotDePassePanel;

		}

		/**
		 * Champs mot de passe de l'utilisateur
		 * @return
		 */
		public JTextField addFieldMotDePasse() {
			this.txtMotDePasse = new JPasswordField(20);
			return txtMotDePasse;
		}

		// Bouton valider qui génère l'action du login manager en lui passant
		// l'identifiant
		// et le mot de passe saisis
		public JButton addButtonValider(Personnel personnel) {
			this.buttonValider = new JButton("Valider");

			// Action du bouton Valider d'authentification
			buttonValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						
						String motDePasseSaisi = txtMotDePasse.getText();
						PersonnelMger.getInstance().resetMotDePasse(personnel,motDePasseSaisi);
						ResetMotDePasseDialog.this.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			return buttonValider;

		}
	
}
