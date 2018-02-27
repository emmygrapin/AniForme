package fr.eni.clinique.ihm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fr.eni.clinique.dal.DALException;

public class clinique extends JFrame {
	
	private JMenuBar menuBar;
	
	public clinique() throws DALException
	{
		// Création du conteneur de plus haut niveau		
		this.setTitle("Ani'Form");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		// Réglage de la taille du conteneur
		this.setSize(900, 800);
		
		// Réglage de la position du conteneur
		this.setLocationRelativeTo(null);
		
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(addMenu());
		
		// J'affiche la fenêtre
		this.setVisible(true);
	}
	
	
	//Menu
	private JMenuBar addMenu()
	{
		JMenu menu = new JMenu("Fichier");
		JMenu menu1 = new JMenu("Gestion des rendez-vous");
		JMenu menu2 = new JMenu("Agenda");
		JMenu menu3 = new JMenu("Gestion du personnel");
		
		
		this.menuBar= new JMenuBar();
		this.menuBar.add(menu);
		this.menuBar.add(menu1);
		this.menuBar.add(menu2);
		this.menuBar.add(menu3);
		
		return this.menuBar;
	}
	
	
}
