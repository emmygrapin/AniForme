package fr.eni.clinique.ihm.ecranAnimal;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.RaceManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.ihm.ApplyController;

public class AnimalGestion implements ActionListener {

	private JTextField txtNom, txtCouleur, txtTatouage;
	private JButton btnValider, btnAnnuler, btnAjouter, btnEditer, btnSupprimer;
	private JComboBox cbxSexe, cbxEspece, cbxRace;
	
	Hashtable<String, Vector<String>> cbxItems;
	private Client client;
	private JButton btnAddAnimal;
	
	private TableAnimal tableAnimal;
	
	private static AnimalGestion _instance;
	
	public AnimalGestion(Client client) throws DALException{
		this.client = client;	
	}
	
	public JPanel viewGestionAnimaux() {
		JPanel panelGestionAnimaux = new JPanel();

		panelGestionAnimaux.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestionAnimaux.add(new JScrollPane(getTableAnimal()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelGestionAnimaux.add(viewButtons(), gbc);
		
		return panelGestionAnimaux;

	}	


	/**
	 *  Composant contenant les boutons "valider" et "annuler"
	 * @return JPanel
	 */
	public JPanel viewButtons() {
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelButtons.add(getBtnAjouter(), gbc);
		gbc.gridx = 1;
		panelButtons.add(getBtnSupprimer(), gbc);
		gbc.gridx = 2;
		panelButtons.add(getBtnEditer(), gbc);
		
		return panelButtons;
	}
	
	/**
	 * Composant qui contient le formulaire de création d'un animal
	 * 
	 * @return JPanel
	 * @throws DALException 
	 */
	public JPanel addAnimal() throws DALException {
		
		JPanel panelAnimal = new JPanel();
		panelAnimal.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);		

		// ligne "Nom"
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelAnimal.add(new JLabel("Nom"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(addFieldNom(), gbc);
		
		// ligne "Couleur et "Sexe"
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelAnimal.add(new JLabel("Couleur"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(addFieldCouleur(), gbc);
		
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridx = 2;
		panelAnimal.add(addCbxSexe(), gbc);
		
		// ligne "Espèce" et "Race"
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		panelAnimal.add(addRaceElement(), gbc);
		
		// ligne "Tatouage"
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		panelAnimal.add(new JLabel("Tatouage"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(addFieldTatouage(), gbc);
		
		
		return panelAnimal;
		
	}

	/**
	 * Composant qui contient les menus déroulant "Espèce" et "Race"
	 * @return JPanel
	 * @throws DALException
	 */
	private JPanel addRaceElement() throws DALException {
		
		JPanel panelRace = new JPanel();
		panelRace.setLayout(new GridBagLayout());
		
		// créer les listes déroulantes liées (Espèces - Races)
		cbxlinkedEspeceRace();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(600, 50);
		panelRace.setPreferredSize(dimension);
		
		// "Espèce"
		gbc.gridx = 0;
		panelRace.add(new JLabel("Espèce"), gbc);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelRace.add(addCbxEspece(), gbc);
		
		// "Race"
		gbc.weightx = 0;
		gbc.gridx = 2;
		panelRace.add(new JLabel("Race"), gbc);
		gbc.weightx = 3;
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelRace.add(addCbxRace(), gbc);
		
		return panelRace;
	}

	/**
	 * Méthode qui lie les espèces avec leurs races respectives
	 * @throws DALException
	 */
	public void cbxlinkedEspeceRace() throws DALException {
		
		// prend en paramètre une espèce avec un tableau de races correspondant à l'espèce
		cbxItems = new Hashtable<>();
		List<Race> listeEspeces = RaceManager.getInstance().getEspeces();
		
		for (Race espece : listeEspeces) {
			
			Vector<String> races = new Vector<String>();
			
			// liste de races en fonction de l'espèce
			List<Race> racesByEspece = RaceManager.getInstance().getRacesByEspece(espece.getEspece());
			
			for (Race uneRace : racesByEspece) {
				// stocke chaque race dans le tableau(Vector) instancié
				races.add(uneRace.getRace());
				
			}
			// boucle sur chaque espèce pour remplir le tableau(HashTable) de races correspondantes
			cbxItems.put(espece.getEspece(), races);
		}
	}
	
	/**
	 * Action qui s'enclenche quand l'ActionListener est lancé (dans la méthode addRaceElement)
	 * Récupère l'espèce sélectionné dans le menu déroulant et affiche le tableau(Vector)
	 *  de Races correspondantes dynamiquement
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String espece = cbxEspece.getSelectedItem().toString();
		
		Object obj = cbxItems.get(espece);
		
		if ( obj == null) {
			cbxRace.setModel(new DefaultComboBoxModel());
		} else {
			cbxRace.setModel(new DefaultComboBoxModel((Vector<String>) obj));
		}
		
	}
	

	/**
	 * Bouton qui valide l'ajout d'un animal
	 * @return JButton 
	 */
	public JButton getBtnValider() {

		if (btnValider == null) {
			
			btnValider = new JButton("Valider"); 
			btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (txtNom.getText().isEmpty() || txtCouleur.getText().isEmpty() || txtTatouage.getText().isEmpty()) {
						System.out.println("getBtnValider race : " + cbxRace.getSelectedItem());
						System.out.println("getBtnValider espèce : " + cbxEspece.getSelectedItem());
						
					} else {
							System.out.println("getBtnValider e race : " + cbxRace.getSelectedItem());
							System.out.println("getBtnValider e espèce : " + cbxEspece.getSelectedItem());
							Animal animal = newAnimal(client);
						
					}
				}
			});
		}
		return btnValider;
	}
	
	/**
	 * Bouton qui annule l'ajout d'un animal. Retourne sur la liste d'animaux du client
	 * @return JButton
	 */ 
	public JButton getBtnAnnuler()
	{
		if(btnAnnuler == null)
		{
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						ApplyController.getInstance().move("listAnim", new ArrayList());
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnAnnuler;
	}

	/**
	 * Bouton qui envoie sur la page d'ajout d'un animal.
	 * @return JButton
	 */
	public JButton getBtnAjouter() {
		
		if ( btnAjouter == null) {
			btnAjouter = new JButton("Ajouter",new ImageIcon("images/ajouter.png"));
			btnAjouter.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnAjouter.setHorizontalTextPosition(AbstractButton.CENTER);
			btnAjouter.setMnemonic(KeyEvent.VK_M);
			btnAjouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {	
					AjoutAnimalDialog newAnimal = new AjoutAnimalDialog(client, AnimalGestion.this);
				}
			});
		}
		
		return btnAjouter;
	}
	
	/**
	 * Bouton qui envoie sur la page de modification d'un animal. 
	 * @return JButton
	 */
	public JButton getBtnEditer() {
		
		if ( btnEditer == null) {
			btnEditer = new JButton("Editer", new ImageIcon("images/editer.png"));
			btnEditer.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnEditer.setHorizontalTextPosition(AbstractButton.CENTER);
			btnEditer.setMnemonic(KeyEvent.VK_M);
			btnEditer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
						try {
							if (tableAnimal.getAnimalSelect() != null) {
								int codeAnimal = tableAnimal.getAnimalSelect().getCodeAnimal();
								Animal animal = DAOFactory.getAnimalDAO().selectById(codeAnimal);
								AnimalManager animalMgr = AnimalManager.getInstance();
								animalMgr.update(animal);
								
							}
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
				}
			});
		}
		return btnEditer;
	}
	
	/**
	 * Bouton "supprimer" qui archive l'animal sélectionné.
	 * @param animal
	 * @return JButton
	 */
	public JButton getBtnSupprimer() {
		
		if ( btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer",  new ImageIcon("images/supprimer.png"));
			btnSupprimer.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnSupprimer.setHorizontalTextPosition(AbstractButton.CENTER);
			btnSupprimer.setMnemonic(KeyEvent.VK_M);
			btnSupprimer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						if (tableAnimal.getAnimalSelect() != null) {
							int codeAnimal = tableAnimal.getAnimalSelect().getCodeAnimal();
							Animal animal = DAOFactory.getAnimalDAO().selectById(codeAnimal);
							AnimalManager animalManager = AnimalManager.getInstance();
							animalManager.updateIsArchive(animal);	
							refreshTableAnimaux();
							// reset animal sélectionné
							tableAnimal.setAnimalSelect(null);
						}

					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnSupprimer;
	}
	
	
	/**
	 * 
	 * @param client
	 * @return animal
	 */
	private Animal newAnimal(Client client) {
		
		AnimalManager animalManager = AnimalManager.getInstance();
		RaceManager raceManager = RaceManager.getInstance();
		Race race;
		Animal animal = null;
		try {
			race = raceManager.getRace((cbxRace.getSelectedItem().toString()), (cbxEspece.getSelectedItem().toString()));
			animal = new Animal(txtNom.getText(), (String)cbxSexe.getSelectedItem(), txtCouleur.getText(),race , client , txtTatouage.getText(), "", false);
			System.out.println("new Animal animal : " + animal.toString());
			animalManager.addAnimal(animal);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return animal;
	}
	
	private JTextField addFieldNom() {
		
		if( txtNom == null) {
			txtNom = new JTextField(20);
		}
		return txtNom;
	}
	
	private JTextField addFieldCouleur() {
		
		if ( txtCouleur == null) {
			txtCouleur = new JTextField(20);
		}
		return txtCouleur;
	}
	
	private JComboBox addCbxSexe() throws DALException {
		
		Vector<String> sexes = new Vector<String>();
		sexes.add("Mâle");
		sexes.add("Femelle");
		sexes.add("Hermaphrodite");
		
		this.cbxSexe = new JComboBox(sexes);
		
		return this.cbxSexe;
	}
	
	private JComboBox addCbxEspece() throws DALException {

		Vector<String> especes = new Vector<String>();
		List<Race> listeRaces = RaceManager.getInstance().getEspeces();
		
		for (Race race : listeRaces) {
			especes.add(race.getEspece());
		}
		
		this.cbxEspece = new JComboBox(especes);
		cbxEspece.addActionListener(this);

		return this.cbxEspece;
	}
	
	private JComboBox addCbxRace() throws DALException {
		
//		Vector<String> races = new Vector<String>();
//		
//		List<Race> listeRaces = RaceManager.getInstance().getRacesByEspece(espece);
//		for (Race race : listeRaces ) {
//			races.add(race.getRace());
//		}
		this.cbxRace = new JComboBox();
		
		return this.cbxRace;
	}
	
	private JTextField addFieldTatouage() {
		
		if ( txtTatouage == null) {
			txtTatouage = new JTextField(20);
		}
		return txtTatouage;
	}
	
	/**
	 * instancie le tableau d'animaux
	 * @return
	 */
	public TableAnimal getTableAnimal() {
		
		if (tableAnimal == null) {
			
			this.tableAnimal = new TableAnimal(client);
			tableAnimal.setFillsViewportHeight(true);
			tableAnimal.setPreferredScrollableViewportSize(new Dimension(500, 200));
			
		}
		return tableAnimal;
	}

	public void refreshTableAnimaux() {
		
		AnimalManager animalMgr = AnimalManager.getInstance();
		List<Animal> listeAnimaux = null;
		try {
			listeAnimaux = animalMgr.getClientByAnimal(client);
		} catch (DALException e) {
			e.printStackTrace();
		}
		getTableAnimal().getTableModAnimal().setListeAnimaux(listeAnimaux);
		
		
	}

}
