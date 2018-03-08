package fr.eni.clinique.ihm.ecranPrincipal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.login;
import fr.eni.clinique.ihm.ecranAgenda.AgendaGestion;
import fr.eni.clinique.ihm.ecranAnimal.AnimalGestion;
import fr.eni.clinique.ihm.ecranClient.InfosClient;
import fr.eni.clinique.ihm.ecranPersonnel.PersonnelGestion;
//import fr.eni.clinique.ihm.ecranRDV.GestionRDV;


public class EcranPrincipalClinique extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	

	private static EcranPrincipalClinique instance;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBarre;
	private JMenu menuPersonnel, menuClient, menuAnimaux, menuAgenda;
	private PersonnelGestion ecranPersonnelGest;
	private AnimalGestion ecranAnimalGest;
	private Personnel personnelActif;
	private AgendaGestion ecranAgendaGest;
//	private GestionRDV ecranRDVGest;
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
	
		//desktopPane.add(getEcranAnimalGestion());


	}
	
	public void chargerpages(){
		desktopPane.add(getEcranPersonnelGestion());		
		desktopPane.add(getEcranInfosGestion());
		//desktopPane.add(getEcranRDVGestion());
		desktopPane.add(getEcranAgendaGestion());
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

		

//			@Override
			public void run() {
				
			EcranPrincipalClinique ecran;
			ecran = EcranPrincipalClinique.getInstance();
//			
//				//TODO dialog authentification
				login loginEcran = new login(ecran);
//				
			if(ecran.personnelActif != null)
			{
				ecran.chargerpages();
				ecran.setVisible(true);
			}
				
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

		// Menu Gestion de Personnel
		menuPersonnel = new JMenu("Gestion du personnel");
		menuBarre.add(menuPersonnel);
		
		menuPersonnel.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	getEcranPersonnelGestion().setVisible(true);
		    }

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		// Menu Gestion de Client
		menuClient = new JMenu("Gestion des Rendez-vous");
		menuBarre.add(menuClient);
		
		// Sous menu Prise Rendez vous
		menuItem = new JMenuItem("Prise de rendez-vous");
		menuItem.setActionCommand("rendezvous");
		menuItem.addActionListener(this);
		menuClient.add(menuItem);

		// Sous menu Gestion Clients
		menuItem = new JMenuItem("Gestion Clients");
		menuItem.setActionCommand("gestionClient");
		menuItem.addActionListener(this);
		menuClient.add(menuItem);
		
		
		// Menu Agenda
		menuAgenda = new JMenu("Agenda");
		menuBarre.add(menuAgenda);
		menuAgenda.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	getEcranAgendaGestion().setVisible(true);
		    }

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		// Menu Gestion Animaux
		menuAnimaux = new JMenu("Gestion d'Animaux");
		menuBarre.add(menuAnimaux);
		
		menuAnimaux.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	//getEcranAnimalGestion().setVisible(true);
		    }

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void refreshMenu ()
	{
		if(personnelActif.getRole() != "ADM")
		{
			menuPersonnel.enable(false);
			menuPersonnel.setVisible(false);
			this.validate();
			this.repaint();
		}
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
		case "gestionClient":
			getEcranInfosGestion().setVisible(true);
			break;
		case "rendezvous":
//			getEcranRDVGestion().setVisible(true);
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
	
	
//	public GestionRDV getEcranRDVGestion() {
//		if(ecranRDVGest == null){
//			try {
//				ecranRDVGest  = new GestionRDV();
//
//			} catch (DALException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return ecranRDVGest;
//	}
		
	public AgendaGestion getEcranAgendaGestion(){
		if(ecranAgendaGest ==  null){
		ecranAgendaGest = new AgendaGestion(this, this.getPersonnelActif());
		}
		return ecranAgendaGest;
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
//	public AnimalGestion getEcranAnimalGestion() {
//		if(ecranAnimalGest == null){
//			try {
//				ecranAnimalGest  = new AnimalGestion(this);
//			} catch (DALException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return ecranAnimalGest;
//	}
	
	public void setPersonnelActif(Personnel personnel)
	{
		personnelActif = personnel;	
	}


	public Personnel getPersonnelActif() {
		return personnelActif;
	}
	
	
}
