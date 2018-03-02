package fr.eni.clinique.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.RaceManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;

public class AnimalController {

	private JTextField txtNom, txtCouleur, txtTatouage;
	private JButton btnValider, btnAnnuler, btnAjouter, btnModifier, btnSupprimer;
	private JComboBox cbxSexe, cbxEspece, cbxRace;
	
	private static AnimalController _instance;
	
	private AnimalController(){
		
	}
	
	public static AnimalController getInstance() {
		
		if(_instance ==  null)
		{
			AnimalController._instance = new AnimalController();
		}
		return _instance;
		
	}
	
	/**
	 * Page de création d'un animal avec les différents composants
	 * @return
	 * @throws DALException 
	 */
	public JPanel viewGestionAddAnimal() throws DALException {
		
		JPanel panelGestAnim = new JPanel();
		panelGestAnim.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 800);
		panelGestAnim.setPreferredSize(dimension);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.gridwidth = 1;
		panelGestAnim.add(viewButtons(), gbc);
		
		gbc.gridy = 1;
		panelGestAnim.add(viewClient(), gbc);
		
		gbc.gridy = 2;
		panelGestAnim.add(addAnimal(), gbc);
		
		
		return panelGestAnim;
	}
	
	public JPanel viewClient() {
		
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 50);
		panelClient.setPreferredSize(dimension);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelClient.setBorder(BorderFactory.createTitledBorder("Client:"));
		
		return panelClient;
	}
	
	/**
	 * Ecran avec la liste d'animaux 
	 * @return JPanel
	 */
	public JPanel viewAnimaux() {

		JPanel panelListeAnim = new JPanel();
		panelListeAnim.setLayout(new GridBagLayout());
		
		panelListeAnim.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 50);
		panelListeAnim.setPreferredSize(dimension);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//panelListeAnim.add(), gbc);

		return panelListeAnim;
	}

	public JPanel viewUnAnimal() {
		
		JPanel panelAnimal = new JPanel();
		panelAnimal.setLayout(new GridBagLayout());
		
		panelAnimal.setBorder(BorderFactory.createEmptyBorder());
		
		return panelAnimal;
	}

	/**
	 *  Composant contenant les boutons "valider" et "annuler"
	 * @return JPanel
	 */
	public JPanel viewButtons() {
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridBagLayout());
		
		panelButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 50);
		panelButtons.setPreferredSize(dimension);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelButtons.add(getBtnValider(), gbc);
		gbc.gridx = 1;
		panelButtons.add(getBtnAnnuler(), gbc);
		
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
		panelRace.add(addCbxRace(cbxEspece.getSelectedItem().toString()), gbc);
		
		return panelRace;
	}

	public JPanel cbxlinkedEspeceRace() throws DALException {
		
		
		Hashtable<String, Vector<String>> cbxItems = new Hashtable<>();
		List<Race> listeEspeces = RaceManager.getInstance().getEspeces();
		
		
		for (Race espece : listeEspeces) {
			
			Vector<String> races = new Vector<String>();
			
			List<Race> racesByEspece = RaceManager.getInstance().getRacesByEspece(espece.getEspece());
			
			for (Race uneRace : racesByEspece) {
				
				races.add(uneRace.getRace());
				
				
			}
			
			cbxItems.put(espece.getEspece(), races);
	
		}
		
		
		

		
		
		this.cbxRace = new JComboBox();
		
		
		//Vector<String> especes = { "", "", "", ""
		return null;
		
	}
//	String[] items = { "Select Item", "Color", "Shape", "Fruit" };
//    mainComboBox = new JComboBox(items);
//    mainComboBox.addActionListener(this);
//
//    getContentPane().add(mainComboBox, BorderLayout.WEST);
//
//    subComboBox = new JComboBox();
//    getContentPane().add(subComboBox, BorderLayout.EAST);
//
//    String[] subItems1 = { "Select Color", "Red", "Blue", "Green" };
//    subItems.put(items[1], subItems1);
//
//    String[] subItems2 = { "Select Shape", "Circle", "Square", "Triangle" };
//    subItems.put(items[2], subItems2);
//
//    String[] subItems3 = { "Select Fruit", "Apple", "Orange", "Banana" };
//    subItems.put(items[3], subItems3);
//  }
//
//  public void actionPerformed(ActionEvent e) {
//    String item = (String) mainComboBox.getSelectedItem();
//    Object o = subItems.get(item);
//
//    if (o == null) {
//      subComboBox.setModel(new DefaultComboBoxModel());
//    } else {
//      subComboBox.setModel(new DefaultComboBoxModel((String[]) o));
//    }
//  }


	/**
	 * 
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
						try {
		
							ClientManager clientManager = ClientManager.getInstance();
							Client client = clientManager.getClient(1);
							Animal animal = newAnimal(client);
							//ApplyController.getInstance().move("listAnim", new ArrayList<>());
							
						} catch(DALException e1) {
							
						}
					}
				}
			});
		}
		return btnValider;
	}
	
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

	public JButton getBtnAjouter() {
		
		if ( btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						ApplyController.getInstance().move("newAnim", new ArrayList());
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		
		return btnAjouter;
	}
	
	public JButton getBtnModifier() {
		
		if ( btnModifier == null) {
			btnModifier = new JButton("Modifier");
		}
		return btnModifier;
	}
	
	public JButton getBtnSupprimer(Animal animal) {
		
		if ( btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						AnimalManager animalManager = AnimalManager.getInstance();
						animalManager.removeAnimal(animal);
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
		
		List<Race> listeRaces = RaceManager.getInstance().getRaces();
		
		for (Race race : listeRaces) {
			especes.add(race.getEspece());
		}
		
		this.cbxEspece = new JComboBox(especes);

		return this.cbxEspece;
	}
	
	private JComboBox addCbxRace(String espece) throws DALException {
		
		Vector<String> races = new Vector<String>();
		List<Race> listeRaces = RaceManager.getInstance().getRacesByEspece(espece);
		for (Race race : listeRaces ) {
			races.add(race.getRace());
		}
		this.cbxRace = new JComboBox(races);
		
		return this.cbxRace;
	}
	
	private JTextField addFieldTatouage() {
		
		if ( txtTatouage == null) {
			txtTatouage = new JTextField(20);
		}
		return txtTatouage;
	}
}
