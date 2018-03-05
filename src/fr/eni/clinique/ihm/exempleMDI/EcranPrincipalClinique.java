package fr.eni.clinique.ihm.exempleMDI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.login;
import fr.eni.clinique.ihm.ecranAnimal.AnimalGestion;
import fr.eni.clinique.ihm.ecranClient.InfosClient;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelGestion;


public class EcranPrincipalClinique extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	

	private static EcranPrincipalClinique instance;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuAgenda;
	private PersonnelGestion ecranPersonnelGest;
	private AnimalGestion ecranAnimalGest;

	private InfosClient ecranClientGest;
	private Client clientActif = null;
	


	private EcranPrincipalClinique() {

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

		
		desktopPane.add(getEcranInfosGestion());
		

		desktopPane.add(getEcranAnimalGestion());


	}

	
	public static EcranPrincipalClinique getInstance()
	{
		if( EcranPrincipalClinique.instance == null)
		{
			EcranPrincipalClinique.instance = new EcranPrincipalClinique();
		}
		return EcranPrincipalClinique.instance;
	}
	
	// Lancement de l'application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				EcranPrincipalClinique ecran;
				ecran = EcranPrincipalClinique.getInstance();
			
				//TODO dialog authentification
				login loginEcran = new login(ecran);
//				//TODO setVisible ici
				ecran.setVisible(true);
				
			}
		});

	}

	public void createMenuBar() {

		// Menu Fichier
		JMenu menu = new JMenu("Fichier");
		menuBarre.add(menu);

		// Sous menu DÃ©connexion
		JMenuItem menuItem = new JMenuItem("Déconnexion");
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
		


		menuItem = new JMenuItem("Gestion Client");
		menuBarre.add(menuItem);		
		menuItem.setActionCommand("gestionClient");
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
			
		case "gestionClient":
			getEcranInfosGestion().setVisible(true);
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
				ecranPersonnelGest  = new PersonnelGestion(this);
			} catch (DALException e) {
				// TODO Auto-generated catch block "echec affichage écran"
				e.printStackTrace();
			}
		}
		return ecranPersonnelGest;
	}
	
	public InfosClient getEcranInfosGestion() {
		if(ecranClientGest == null){
			try {
				ecranClientGest  = new InfosClient();

			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ecranClientGest;
	}
		

	
	public Client getClientActif() {
		return clientActif;
	}

	public void setClientActif(Client clientActif) {
		this.clientActif = clientActif;
	}
	
	public void refreshClientInfos()
	{
		ecranClientGest.setClientActif(clientActif);
		ecranClientGest.refresh();
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
