package fr.eni.clinique.ihm;

import javax.swing.JDialog;

import fr.eni.clinique.bo.Personnel;
/**
 * boîte de dialogue du changement de mot de passe utilisateur
 * @author egrapin2017
 *
 */
public class MotDePasseChangement extends JDialog {
	JDialog boiteDialog = new JDialog(this, "resetMotDePasse", false);

	public MotDePasseChangement(Personnel personnel) {
		MotDePasseController motDePasseController = MotDePasseController.getInstance();
		this.setTitle("Réinitialisation du mot de passe");
		this.setContentPane(motDePasseController.viewChangementMotDePasse(personnel));
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}
}
