package fr.eni.clinique.ihm.ecranPersonnel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.ihm.ApplyController;

public class PersonnelGestion extends JInternalFrame {

	private JPanel selectPersonnel, panelGestionPersonnels;
	private TablePersonnel tablePersonnel;
	private JButton buttonNewPersonnel;
	private NewPersonnelDialog newPersonnelDialog;
	private JFrame parent;
	private static JOptionPane alert;

	public PersonnelGestion(JFrame parent) throws DALException {

		super("Gestion du personnel", true, true, true, true);
		this.parent = parent;

		// PersonnelController personnelController =
		// PersonnelController.getinstance();
		// Réglage de la taille du conteneur
		this.setSize(900, 800);

		// Réglage de la position du conteneur
		// this.setLocationRelativeTo(null);

		this.setContentPane(viewGestionPersonnels());
		// Cache l'application lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// J'affiche la fenêtre
		// this.setVisible(true);

	}

	/**
	 * panel Global de Gestion du personnel contient le panel des boutons et le
	 * panel des personnes
	 * 
	 * @return
	 */
	public JPanel viewGestionPersonnels() {
		panelGestionPersonnels = new JPanel();

		panelGestionPersonnels.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestionPersonnels.add(addViewButtons(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelGestionPersonnels.add(new JScrollPane(getTablePersonnel()), gbc);

		return panelGestionPersonnels;

	}

	/**
	 * panel des boutons
	 * 
	 * @return
	 */
	public JPanel addViewButtons() {

		JPanel panelButtons = new JPanel();
		Dimension dimension = new Dimension(500, 100);
		panelButtons.setPreferredSize(dimension);
		panelButtons.setLayout(new GridBagLayout());
		panelButtons.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints gbc = new GridBagConstraints();

		// Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelButtons.add(buttonNewPersonnel(), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		panelButtons.add(deleteButton(), gbc);
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelButtons.add(resetButton(), gbc);

		return panelButtons;
	}

	/**
	 * Bouton d'ajout d'un personnel
	 * 
	 * @return
	 */
	public JButton buttonNewPersonnel() {
		JButton addButton = new JButton("Ajouter", new ImageIcon("images/ajouter.png"));
		addButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		addButton.setHorizontalTextPosition(AbstractButton.CENTER);
		addButton.setMnemonic(KeyEvent.VK_M);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					newPersonnelDialog = new NewPersonnelDialog(parent);
					refreshTablePersonnels();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		return addButton;
	}

	/**
	 * Bouton qui supprime un personnel de l'affichage et archive le personnel
	 * en question
	 * 
	 * @return
	 */
	public JButton deleteButton() {
		JButton deleteButton = new JButton("Supprimer", new ImageIcon("images/supprimer.png"));
		deleteButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		deleteButton.setHorizontalTextPosition(AbstractButton.CENTER);
		deleteButton.setMnemonic(KeyEvent.VK_M);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (tablePersonnel.getPersonnelSelect() != null) {
						int option = alert.showConfirmDialog(null,
								"Etes-vous sûr(e) de vouloir archiver cet utilisateur?", "Confirmation",
								JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							int identifiant = tablePersonnel.getPersonnelSelect().getCodePerso();
							Personnel personnel = DAOFactory.getPersonnelDAO().selectById(identifiant);
							PersonnelMger.getInstance().updateIsArchive(personnel);
							alert.showMessageDialog(null, "L'utilisateur a bien été archivé", "Information",
									JOptionPane.INFORMATION_MESSAGE);
							refreshTablePersonnels();
							tablePersonnel.setPersonnelSelect(null);
						}
						tablePersonnel.setPersonnelSelect(null);
					}
				} catch (Exception e1) {
					alert.showMessageDialog(null, "L'utilisateur n'a pas été correctement archivé", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		return deleteButton;
	}

	/**
	 * Bouton qui permet à l'utilisateur de réinitialiser un mot de passe
	 * 
	 * @return
	 */
	public JButton resetButton() {
		JButton resetButton = new JButton("Réinitialiser", new ImageIcon("images/Reinitialiser.png"));
		resetButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		resetButton.setHorizontalTextPosition(AbstractButton.CENTER);
		resetButton.setMnemonic(KeyEvent.VK_M);
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (tablePersonnel.getPersonnelSelect() != null) {
						int identifiant = tablePersonnel.getPersonnelSelect().getCodePerso();
						Personnel personnel = DAOFactory.getPersonnelDAO().selectById(identifiant);
						new ResetMotDePasseDialog(personnel, parent);
						alert.showMessageDialog(null, "Le mot de passe a bien été réinitialisé", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						refreshTablePersonnels();
						tablePersonnel.setPersonnelSelect(null);

					}
				} catch (Exception e1) {
					alert.showMessageDialog(null, "Le mot de passe n'a pas été correctement réinitialisé", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		return resetButton;
	}

	public TablePersonnel getTablePersonnel() {

		if (tablePersonnel == null) {

			this.tablePersonnel = new TablePersonnel();
			tablePersonnel.setFillsViewportHeight(true);
			tablePersonnel.setPreferredScrollableViewportSize(new Dimension(500, 200));

		}
		return tablePersonnel;
	}

	public NewPersonnelDialog getNewPersonnelDialog() {
		return newPersonnelDialog;
	}

	public void refreshTablePersonnels() {
		PersonnelMger personnelManager = PersonnelMger.getInstance();
		List<Personnel> listePersonnels = personnelManager.getPersonnels();
		getTablePersonnel().getTableModPersonnel().setListePersonnels(listePersonnels);
		;
	}
}
