package fr.eni.clinique.ihm.ecranAnimal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class AjoutAnimalDialog extends JDialog implements ActionListener {
	
	private JTextField txtNom, txtCouleur, txtTatouage;
	private JButton btnValider, btnAnnuler;
	private JComboBox cbxSexe, cbxEspece, cbxRace;
	private Client client;
	private AnimalGestion parent;
	
	Hashtable<String, Vector<String>> cbxItems;

	public AjoutAnimalDialog(Client client,  AnimalGestion parent) {
	
	this.parent = parent;
	this.client = client;
	this.setTitle("Animaux");
	this.setContentPane(viewAjoutAnimal());
	this.setSize(700, 800);
	this.setLocationRelativeTo(null);
	this.setResizable(true);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	
	}
	
	/**
	 * Page de création d'un animal avec les différents composants
	 * @return
	 * @throws DALException 
	 */
	public JPanel viewAjoutAnimal() {
		
		JPanel panelGestAnim = new JPanel();
		panelGestAnim.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(700, 800);
		panelGestAnim.setPreferredSize(dimension);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.gridwidth = 1;
		panelGestAnim.add(viewButtons(), gbc);
		
		gbc.gridy = 1;
		panelGestAnim.add(viewClient(), gbc);
		
		gbc.gridy = 2;
		panelGestAnim.add(getFormAjoutAnimal(), gbc);
		
		
		return panelGestAnim;
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
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelButtons.add(getBtnValider(), gbc);
		gbc.gridx = 1;
		panelButtons.add(getBtnAnnuler(), gbc);
		
		return panelButtons;
	}
	
	/**
	 * Composant contenant le nom et le prénom du client
	 * @return JPanel
	 */
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
		//TODO récupérer le nom d'un client
		
		return panelClient;
	}

	/**
	 * Composant qui contient le formulaire de création d'un animal
	 * 
	 * @return JPanel
	 * @throws DALException 
	 */
	public JPanel getFormAjoutAnimal() {
		
		JPanel panelAnimal = new JPanel();
		panelAnimal.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);		

		// ligne "Nom"
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelAnimal.add(new JLabel("Nom"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(getFieldNom(), gbc);
		
		// ligne "Couleur et "Sexe"
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelAnimal.add(new JLabel("Couleur"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(getFieldCouleur(), gbc);
		
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridx = 2;
		panelAnimal.add(getCbxSexe(), gbc);
		
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
	 * Bouton qui valide l'ajout d'un animal
	 * @return JButton 
	 */
	public JButton getBtnValider() {

		if ( this.btnValider == null) {
			
			this.btnValider = new JButton("Valider"); 
			this.btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (txtNom.getText().isEmpty() || txtCouleur.getText().isEmpty() || txtTatouage.getText().isEmpty()) {
						
						
					} else {
						ClientManager clientManager = ClientManager.getInstance();
						Animal animal = newAnimal(client);
						parent.refreshTableAnimaux();
						AjoutAnimalDialog.this.dispose();
					}
				}
			});
		}
		return this.btnValider;
	}
	
	/**
	 * Récupère les données de chaque champ et les stocke dans la base de données. 
	 * Fonction appelé quand on déclenche le bouton de validation
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
//			System.out.println("new Animal animal : " + animal.toString());
			animalManager.addAnimal(animal);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return animal;
	}
	
	/**
	 * Bouton qui annule l'ajout d'un animal. Retourne sur la liste d'animaux du client
	 * @return JButton
	 */ 
	public JButton getBtnAnnuler()
	{
		if(this.btnAnnuler == null)
		{
			this.btnAnnuler = new JButton("Annuler");
			this.btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					AjoutAnimalDialog.this.dispose();
				}
			});
		}
		return this.btnAnnuler;
	}
	
	/**
	 * Action qui s'enclenche quand l'ActionListener est lancé (méthode getCbxEspece())
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
	 * Créer et/ou retourne un champ Nom
	 * @return JTextField
	 */
	private JTextField getFieldNom() {
		
		if( this.txtNom == null) {
			this.txtNom = new JTextField(20);
		}
		return this.txtNom;
	}
	
	/**
	 * Créer et/ou retourne un menu déroulant contenant les sexes
	 * @return JComboBox
	 */
	private JComboBox getCbxSexe() {
		
		Vector<String> sexes = new Vector<String>();
		sexes.add("Mâle");
		sexes.add("Femelle");
		sexes.add("Hermaphrodite");
		
		this.cbxSexe = new JComboBox(sexes);
		
		return this.cbxSexe;
	}
	
	/**
	 * Créer et/ou retourne un champ Couleur
	 * @return JTextField
	 */
	private JTextField getFieldCouleur() {
		
		if ( this.txtCouleur == null) {
			this.txtCouleur = new JTextField(20);
		}
		return this.txtCouleur;
	}
	
	/**
	 * Composant qui contient les menus déroulant "Espèce" et "Race"
	 * @return JPanel
	 */
	private JPanel addRaceElement() {
		
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
		panelRace.add(getCbxEspece(), gbc);
		
		// "Race"
		gbc.weightx = 0;
		gbc.gridx = 2;
		panelRace.add(new JLabel("Race"), gbc);
		gbc.weightx = 3;
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelRace.add(getCbxRace(), gbc);
		
		return panelRace;
	}

	/**
	 * Méthode qui lie les espèces avec leurs races respectives
	 */
	public void cbxlinkedEspeceRace() {
		
		// prend en paramètre une espèce avec un tableau de races correspondant à l'espèce
		cbxItems = new Hashtable<>();
		List<Race> listeEspeces;
		
		try {
			listeEspeces = RaceManager.getInstance().getEspeces();
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
		} catch (DALException e) {
			// TODO erreur message DALException cbxlinkedEspeceRace
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Créer et/ou retourne un menu déroulant contenant les espèces
	 * @return JComboBox
	 */
	private JComboBox getCbxEspece() {

		Vector<String> especes = new Vector<String>();
		
		try {
			List<Race> listeRaces = RaceManager.getInstance().getEspeces();

			for (Race race : listeRaces) {
				especes.add(race.getEspece());
			}
			
		} catch (DALException e) {
			// TODO erreur message requête 
			e.printStackTrace();
		}
		
		if ( this.cbxEspece == null ) {
			this.cbxEspece = new JComboBox(especes);
			cbxEspece.addActionListener(this);
		}

		return this.cbxEspece;
	}
	
	/**
	 * Créer et/ou retourne un menu déroulant contenant les races
	 * @return JComboBox
	 */
	private JComboBox getCbxRace() {
		
		if (this.cbxRace == null) {
			this.cbxRace = new JComboBox();			
		}
		return this.cbxRace;
	}
	
	/**
	 * Créer et/ou retourne un champ Tatouage
	 * @return JTextField
	 */
	private JTextField addFieldTatouage() {
		
		if ( txtTatouage == null) {
			txtTatouage = new JTextField(20);
		}
		return txtTatouage;
	}
}
