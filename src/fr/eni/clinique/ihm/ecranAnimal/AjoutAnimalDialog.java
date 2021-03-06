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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JLabel labelCode;
	private Client clientActif;
	private AnimalGestion parent;

	private JOptionPane alert;
	
	Hashtable<String, Vector<String>> cbxItems;

	public AjoutAnimalDialog(Client client,  AnimalGestion parent, Animal animal) {
	
	this.parent = parent;
	this.clientActif = client;
	this.setTitle("Animaux");
	this.setContentPane(viewNewAnimal(animal));
	this.setSize(700, 800);
	this.setLocationRelativeTo(null);
	this.setResizable(true);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	
	}
	
	/**
	 * Page de cr�ation d'un animal avec les diff�rents composants
	 * @return
	 * @throws DALException 
	 */
	public JPanel viewNewAnimal(Animal animal) {
		
		JPanel panelGestAnim = new JPanel();
		panelGestAnim.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		Dimension dimension = new Dimension(500, 800);
		panelGestAnim.setPreferredSize(dimension);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestAnim.add(viewButtons(animal), gbc);
		
		gbc.gridy = 1;
		panelGestAnim.add(viewClient(clientActif), gbc);
		
		gbc.gridy = 2;
		panelGestAnim.add(getFormNewAnimal(animal), gbc);
		
		
		return panelGestAnim;
	}
	
	/**
	 *  Composant contenant les boutons "valider" et "annuler"
	 * @return JPanel
	 */
	public JPanel viewButtons(Animal animal) {
		
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
		panelButtons.add(getBtnValider(animal), gbc);
		gbc.gridx = 1;
		panelButtons.add(getBtnAnnuler(), gbc);
		
		return panelButtons;
	}
	
	/**
	 * Composant contenant le nom et le pr�nom du client
	 * @return JPanel
	 */
	public JPanel viewClient(Client client) {
		
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 50);
		panelClient.setPreferredSize(dimension);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelClient.setBorder(BorderFactory.createTitledBorder("Client:"));
		panelClient.add(new JLabel(client.getNomClient() + " " + client.getPrenomClient()));
		
		
		return panelClient;
	}

	/**
	 * Composant qui contient le formulaire de cr�ation d'un animal
	 * 
	 * @return JPanel
	 * @throws DALException 
	 */
	public JPanel getFormNewAnimal(Animal animal) {
		
		JPanel panelAnimal = new JPanel();
		panelAnimal.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// ligne "Code"
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAnimal.add(new JLabel("Code"), gbc);
		gbc.gridx = 1;
		panelAnimal.add(getLabelCode(animal), gbc);
		
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
		
		// ligne "Esp�ce" et "Race"
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
		panelAnimal.add(getFieldTatouage(), gbc);
		
		if (animal != null) {
			txtNom.setText(String.valueOf(animal.getCodeAnimal()));
			txtNom.setText(animal.getNomAnimal());
			cbxSexe.setSelectedItem(animal.getSexeToString());
			txtCouleur.setText(animal.getCouleur());
			cbxEspece.setSelectedItem(animal.getRace().getEspece());
			refreshRace();
			cbxRace.setSelectedItem(animal.getRace().getRace());
			txtTatouage.setText(animal.getTatouage());
		}
		
		return panelAnimal;
		
	}
	
	/**
	 * Bouton qui valide l'ajout d'un animal
	 * @return JButton 
	 */
	public JButton getBtnValider(Animal animal) {

		if ( this.btnValider == null) {
			
			this.btnValider = new JButton("Valider"); 
			this.btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					AnimalManager animalMgr = AnimalManager.getInstance();
					if (txtNom.getText().isEmpty() || txtCouleur.getText().isEmpty()) {
						alert.showMessageDialog(null, "Les champs doivent �tre tous renseign�s", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						
					} else {
						if (txtTatouage.getText().isEmpty()) {
							txtTatouage.setText("non tatou�");
						}
					
						if (animal != null) {
							try {
								Animal newAnimal = newAnimal(clientActif);
								newAnimal.setCodeAnimal(animal.getCodeAnimal());
								animalMgr.update(newAnimal);
							} catch (DALException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							Animal newAnimal = newAnimal(clientActif);
							try {
								animalMgr.addAnimal(newAnimal);
							} catch (DALException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						parent.refreshTableAnimaux();
						//ferme la fen�tre
						AjoutAnimalDialog.this.dispose();
					}
				}
			});
		}
		return this.btnValider;
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
	 * R�cup�re les donn�es de chaque champ et les stocke dans la base de donn�es. 
	 * Fonction appel� quand on d�clenche le bouton de validation
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

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return animal;
	}
	
	
	/**
	 * M�me action que la m�thode actionPerformed appel�e sans l'ActionListener
	 */
	private void refreshRace() {
		String espece = cbxEspece.getSelectedItem().toString();
		
		Object obj = cbxItems.get(espece);
		
		if ( obj == null) {
			cbxRace.setModel(new DefaultComboBoxModel());
		} else {
			cbxRace.setModel(new DefaultComboBoxModel((Vector<String>) obj));
		}
		
	}
	
	/**
	 * Action qui s'enclenche quand l'ActionListener est lanc� (dans la m�thode getCbxEspece())
	 * R�cup�re l'esp�ce s�lectionn� dans le menu d�roulant et affiche le tableau(Vector)
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
	
	private JLabel getLabelCode(Animal animal) {
		if (this.labelCode == null) {
			if (animal == null) {
				this.labelCode = new JLabel("aucun");
			} else {
				this.labelCode = new JLabel(animal.getCodeAnimal() + "");
			}
		}
		
		
		return labelCode;
	}
	
	/**
	 * Cr�er et/ou retourne un champ Nom
	 * @return JTextField
	 */
	private JTextField getFieldNom() {
		
		if( this.txtNom == null) {
			this.txtNom = new JTextField(20);
		}
		return this.txtNom;
	}
	
	/**
	 * Cr�er et/ou retourne un menu d�roulant contenant les sexes
	 * @return JComboBox
	 */
	private JComboBox getCbxSexe() {
		
		Vector<String> sexes = new Vector<String>();
		sexes.add("M�le");
		sexes.add("Femelle");
		sexes.add("Hermaphrodite");
		
		this.cbxSexe = new JComboBox(sexes);
		
		return this.cbxSexe;
	}
	
	/**
	 * Cr�er et/ou retourne un champ Couleur
	 * @return JTextField
	 */
	private JTextField getFieldCouleur() {
		
		if ( this.txtCouleur == null) {
			this.txtCouleur = new JTextField(20);
		}
		return this.txtCouleur;
	}
	
	
	/**
	 * Composant qui contient les menus d�roulant "Esp�ce" et "Race"
	 * @return JPanel
	 */
	private JPanel addRaceElement() {
		
		JPanel panelRace = new JPanel();
		panelRace.setLayout(new GridBagLayout());
		
		// cr�er les listes d�roulantes li�es (Esp�ces - Races)
			cbxlinkedEspeceRace();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(600, 50);
		panelRace.setPreferredSize(dimension);
		
		// "Esp�ce"
		gbc.gridx = 0;
		panelRace.add(new JLabel("Esp�ce"), gbc);
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
	 * M�thode qui lie les esp�ces avec leurs races respectives
	 */
	public void cbxlinkedEspeceRace() {
		
		// prend en param�tre une esp�ce avec un tableau de races correspondant � l'esp�ce
		cbxItems = new Hashtable<>();
		List<Race> listeEspeces;
		
		try {
			listeEspeces = RaceManager.getInstance().getEspeces();
			for (Race espece : listeEspeces) {
				
				Vector<String> races = new Vector<String>();
				
				// liste de races en fonction de l'esp�ce
				List<Race> racesByEspece = RaceManager.getInstance().getRacesByEspece(espece.getEspece());
				
				for (Race uneRace : racesByEspece) {
					// stocke chaque race dans le tableau(Vector) instanci�
					races.add(uneRace.getRace());
					
				}
				// boucle sur chaque esp�ce pour remplir le tableau(HashTable) de races correspondantes
				cbxItems.put(espece.getEspece(), races);
			}
		} catch (DALException e) {
			// TODO erreur message DALException cbxlinkedEspeceRace
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Cr�er et/ou retourne un menu d�roulant contenant les esp�ces
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
			// TODO erreur message requ�te 
			e.printStackTrace();
		}
		
		if ( this.cbxEspece == null ) {
			this.cbxEspece = new JComboBox(especes);
			cbxEspece.addActionListener(this);
		}

		return this.cbxEspece;
	}
	
	/**
	 * Cr�er et/ou retourne un menu d�roulant contenant les races
	 * @return JComboBox
	 */
	private JComboBox getCbxRace() {
		
		if (this.cbxRace == null) {
			this.cbxRace = new JComboBox();			
		}
		return this.cbxRace;
	}
	
	/**
	 * Cr�er et/ou retourne un champ Tatouage
	 * @return JTextField
	 */
	private JTextField getFieldTatouage() {
		
		if ( txtTatouage == null) {
			txtTatouage = new JTextField(20);
		}
		return txtTatouage;
	}
}
