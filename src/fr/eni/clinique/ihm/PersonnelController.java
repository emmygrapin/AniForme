package fr.eni.clinique.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DAOFactory;
/**
 * 
 * @author egrapin2017
 *
 */
public class PersonnelController {

	private static PersonnelController _instance;
	private int identifiant;
	private JPanel selectPersonnel;

	private PersonnelController() {

	}

	public static PersonnelController getinstance() {
		if (_instance == null) {
			PersonnelController._instance = new PersonnelController();
		}

		return _instance;
	}

	
	/**
	 *  panel Global de Gestion du personnel contient le panel des boutons et le panel
	 *  des personnes
	 * @return
	 */
	public JPanel viewGestionPersonnels() {
		JPanel panelGestionPersonnels = new JPanel();

		panelGestionPersonnels.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestionPersonnels.add(addViewButtons(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelGestionPersonnels.add(addViewPersonnels(), gbc);

		return panelGestionPersonnels;

	}

	/**
	 * panel des boutons
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
		panelButtons.add(addButton(), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		panelButtons.add(deleteButton(), gbc);
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelButtons.add(resetButton(), gbc);

		return panelButtons;
	}

	/**
	 * Panel contenant des panels unitaires de personnes
	 * 
	 * @return
	 */
	public JPanel addViewPersonnels() {
		PersonnelMger personnelMger = PersonnelMger.getInstance();

		List<Personnel> personnels = personnelMger.getPersonnels();

		JPanel panelPersonnels = new JPanel();
		panelPersonnels.setLayout(new GridBagLayout());
		panelPersonnels.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints gbc = new GridBagConstraints();

		// Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		int y = 1;

		for (Personnel personnel : personnels) {
			gbc.gridy = y;
			panelPersonnels.add(addViewUnPersonnel(personnel), gbc);
			y++;
		}
		return panelPersonnels;
	}

	/**
	 * panel unitaire affichant un personnel
	 * 
	 * @param personnel
	 * @return
	 */
	public JPanel addViewUnPersonnel(Personnel personnel) {
		JPanel panelPersonnel = new JPanel();
		Dimension dimension = new Dimension(500, 50);
		panelPersonnel.setPreferredSize(dimension);
		panelPersonnel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		panelPersonnel.add(new JLabel(personnel.getNom()), gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		panelPersonnel.add(new JLabel("************"), gbc);

		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		panelPersonnel.add(new JLabel(personnel.getRole()), gbc);

		// Evenement généré au click de la souris sur un personnel
		panelPersonnel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (selectPersonnel != null) {
					selectPersonnel.setBorder(BorderFactory.createEmptyBorder());
				}
				identifiant = personnel.getCodePerso();
				panelPersonnel.setBorder(BorderFactory.createLineBorder(Color.gray));
				selectPersonnel = panelPersonnel;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		return panelPersonnel;
	}

	/**
	 * Bouton d'ajout d'un personnel
	 * 
	 * @return
	 */
	public JButton addButton() {
		JButton addButton = new JButton("Ajouter", new ImageIcon("images/ajouter.png"));
		addButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		addButton.setHorizontalTextPosition(AbstractButton.CENTER);
		addButton.setMnemonic(KeyEvent.VK_M);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// PersonnelMger.getInstance().addPersonnel(personnel);

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
					if (identifiant != 0) {
						Personnel personnel = DAOFactory.getPersonnelDAO().selectById(identifiant);
						PersonnelMger.getInstance().updateIsArchive(personnel);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		return resetButton;
	}

}
