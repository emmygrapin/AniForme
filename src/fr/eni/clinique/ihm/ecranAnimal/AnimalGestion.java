package fr.eni.clinique.ihm.ecranAnimal;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

public class AnimalGestion implements ActionListener {

	private JTextField txtNom, txtCouleur, txtTatouage;
	private JButton btnValider, btnAnnuler, btnAjouter, btnEditer, btnSupprimer;
	private JComboBox cbxSexe, cbxEspece, cbxRace;

	Hashtable<String, Vector<String>> cbxItems;
	private Client client;
	private JButton btnAddAnimal;

	private TableAnimal tableAnimal;

	private static AnimalGestion _instance;

	public AnimalGestion(Client client) throws DALException {
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
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelGestionAnimaux.add(viewButtons(), gbc);

		return panelGestionAnimaux;

	}

	/**
	 * Composant contenant les boutons "valider" et "annuler"
	 * 
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

	// ------------------------------les boutons
	// -----------------------------------------

	/**
	 * Bouton qui envoie sur la page d'ajout d'un animal.
	 * 
	 * @return JButton
	 */
	public JButton getBtnAjouter() {

		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter", new ImageIcon("images/ajouter.png"));
			btnAjouter.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnAjouter.setHorizontalTextPosition(AbstractButton.CENTER);
			btnAjouter.setMnemonic(KeyEvent.VK_M);
			btnAjouter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AjoutAnimalDialog newAnimal = new AjoutAnimalDialog(client, AnimalGestion.this, null);
				}
			});
		}

		return btnAjouter;
	}

	/**
	 * Bouton qui envoie sur la page de modification d'un animal.
	 * 
	 * @return JButton
	 */
	public JButton getBtnEditer() {

		if (btnEditer == null) {
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
							AjoutAnimalDialog editAnimalDialog = new AjoutAnimalDialog(client, AnimalGestion.this, animal);
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
	 * 
	 * @param animal
	 * @return JButton
	 */
	public JButton getBtnSupprimer() {

		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer", new ImageIcon("images/supprimer.png"));
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

	//
	/**
	 * instancie le tableau d'animaux
	 * 
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
			listeAnimaux = animalMgr.getAnimauxByClient(client);
		} catch (DALException e) {
			e.printStackTrace();
		}
		getTableAnimal().getTableModAnimal().setListeAnimaux(listeAnimaux);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
