package fr.eni.clinique.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * 
 * @author egrapin2017
 * Boîte de dialogue d'authentification
 */
public class login extends JDialog{
	JDialog boiteDialog = new JDialog(this,"Authentification", false);
	
	public login(){
	AuthentifierController authentifierController = AuthentifierController.getInstance();
	this.setTitle("Authentification");
	this.setContentPane(authentifierController.viewAuthentification());
	this.setSize(500, 200);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	
}
}
