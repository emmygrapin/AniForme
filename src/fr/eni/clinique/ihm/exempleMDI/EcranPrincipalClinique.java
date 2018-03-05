package fr.eni.clinique.ihm.exempleMDI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.login;
import fr.eni.clinique.ihm.ecranAnimal.AnimalGestion;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelGestion;


public class EcranPrincipalClinique extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private PersonnelGestion ecranPersonnelGest;
	private AnimalGestion ecranAnimalGest;


	public EcranPrincipalClinique() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane Ã  la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());
		
		//(écran enfant) faire pour tous les écrans
		desktopPane.add(getEcranPersonnelGestion());
		
		desktopPane.add(getEcranAnimalGestion());
		

	}

	// Lancement de l'application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				EcranPrincipalClinique ecran = new EcranPrincipalClinique();
				//TODO dialog authentification
				login loginEcran = new login(ecran);
				//TODO setVisible ici
				ecran.setVisible(true);
				
			}
		});

	}

	public void createMenuBar() {

		// Menu Fichier
		JMenu menu = new JMenu("Fichier");
		menuBarre.add(menu);

		// Sous menu DÃ©connexion
		JMenuItem menuItem = new JMenuItem("DÃ©connexion");
		menuItem.setActionCommand("deconnexion");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Sous menu fermer
		menuItem = new JMenuItem("Fermer");
		menuItem.setActionCommand("fermer");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Menu Agenda
		menuItem = new JMenuItem("Gestion du personnel");
		menuBarre.add(menuItem);		
		menuItem.setActionCommand("gestionPersonnel");
		menuItem.addActionListener(this);
		
		// Menu Agenda
		menuItem = new JMenuItem("Gestion d'Animaux");
		menuBarre.add(menuItem);		
		menuItem.setActionCommand("gestionAnimal");
		menuItem.addActionListener(this);

	}

	// RÃ©agir aux clicks sur les menus
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "deconnexion":
			System.out.println("Deconnexion");
			break;
		case "fermer":
			System.exit(0);
			break;

		case "gestionPersonnel":
			getEcranPersonnelGestion().setVisible(true);
			break;
		case "gestionAnimal":
			getEcranAnimalGestion().setVisible(true);
			break;

		default:
			System.out.println("Probleme e=" + e);
		}

	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuBar getMenuBarre() {
		if (menuBarre == null) {
			menuBarre = new JMenuBar();

			createMenuBar();
		}
		return menuBarre;
	}

	/**
	 * Getter 
	 * @return
	 */
	public PersonnelGestion getEcranPersonnelGestion() {
		if(ecranPersonnelGest == null){
			try {
				ecranPersonnelGest  = new PersonnelGestion();
			} catch (DALException e) {
				// TODO Auto-generated catch block "echec affichage écran"
				e.printStackTrace();
			}
		}
		return ecranPersonnelGest;
	}
	
	/**
	 * Getter 
	 * @return
	 */
	public AnimalGestion getEcranAnimalGestion() {
		if(ecranAnimalGest == null){
			try {
				ecranAnimalGest  = new AnimalGestion(this);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ecranAnimalGest;
	}

}
