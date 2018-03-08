package fr.eni.clinique.ihm.ecranDossMedical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.ecranAgenda.AgendaGestion;
import fr.eni.clinique.ihm.ecranAgenda.TableAgenda;
import fr.eni.clinique.ihm.ecranAgenda.TableModelAgenda;

public class DossierMedicalDialog extends JDialog {

	private Client clientActif;
	private Animal animalActif;
	private AgendaGestion parent;
	private Agenda agenda;
	private TableModelAgenda tableModelAgenda;
	
	private JButton btnValider, btnAnnuler;
	private JTextArea txtAreaAntecedents;
	
	public DossierMedicalDialog(Client client, Animal animal, AgendaGestion parent, TableAgenda tableAgenda) {
		this.clientActif = client;
		this.animalActif = animal;
		this.parent = parent;
		this.tableModelAgenda = tableAgenda.getTableModAgenda();
		this.setTitle("Dossier médical");
		this.setContentPane(viewDossierMedical());
		this.setSize(700, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Composant global 
	 * @return JPanel
	 */
	public JPanel viewDossierMedical() {
		
		JPanel panelDossMedical = new JPanel();
		panelDossMedical.setLayout(new BorderLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		panelDossMedical.add("North", viewButtons());
		panelDossMedical.add("South", getAnimalInfos());
		pack();
		
	//NE PAS SUPPRIMER 	
//		Dimension dimension = new Dimension(900, 900);
//		panelDossMedical.setPreferredSize(dimension);
//		
//		// bloc boutons
//		gbc.anchor = GridBagConstraints.CENTER;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.gridwidth = 2;
//		panelDossMedical.add(viewButtons(), gbc);
//		
////		// bloc infos dossier médical
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		gbc.gridwidth = 1;
//		panelDossMedical.add(getAnimalInfos(),gbc);
		
		return panelDossMedical;
	}
	
	/**
	 * Composant contenant le dossier médical de l'animal
	 * @return JPanel
	 */
	private JPanel getAnimalInfos() {
		
		JPanel panelAnimalInfo = new JPanel();
		panelAnimalInfo.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(450, 700);
		panelAnimalInfo.setPreferredSize(dimension);
		
		// bloc description animal
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAnimalInfo.add(viewClient(clientActif), gbc);
		
		// bloc affichage description de l'animal
		gbc.gridy = 1;
		panelAnimalInfo.add(getAnimalData(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAnimalInfo.add(new JLabel("Antécédents/Consultations"),gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridheight = 2;
		gbc.gridy = 1;
		panelAnimalInfo.add(new JScrollPane(getTextAreaAntecedents()), gbc);
		txtAreaAntecedents.setText(animalActif.getAntecedents());
		
		return panelAnimalInfo;
	}

	/**
	 * Composant contenant la descrption de l'animal.
	 * @return JPanel
	 */
	private JPanel getAnimalData() {
		JPanel panelAnimDescr = new JPanel();
		panelAnimDescr.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAnimDescr.add(new JLabel("Animal : "), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panelAnimDescr.add(new JLabel(String.valueOf(animalActif.getCodeAnimal())), gbc);
		gbc.gridy = 1;
		panelAnimDescr.add(new JLabel(animalActif.getNomAnimal()), gbc);
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panelAnimDescr.add(new JLabel(animalActif.getCouleur()), gbc);
		gbc.gridx = 2;
		panelAnimDescr.add(new JLabel(animalActif.getSexeToString()), gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		panelAnimDescr.add(new JLabel(animalActif.getRace().getRace()), gbc);
		gbc.gridy = 4;
		panelAnimDescr.add(new JLabel(animalActif.getTatouage()), gbc);
		
		return panelAnimDescr;
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
	 * Composant contenant le nom et le prénom du client
	 * @return JPanel
	 */
	public JPanel viewClient(Client client) {
		
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		Dimension dimension = new Dimension(300, 50);
		panelClient.setPreferredSize(dimension);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelClient.setBorder(BorderFactory.createTitledBorder("Client:"));
		panelClient.add(new JLabel(client.getNomClient() + " " + client.getPrenomClient()));
		
		return panelClient;
	}

	
	/**
	 * Bouton qui valide les modifications du dossier médical
	 * @return JButton 
	 */
	public JButton getBtnValider() {

		if ( this.btnValider == null) {
			
			this.btnValider = new JButton("Valider"); 
			this.btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					AnimalManager animalMgr = AnimalManager.getInstance();
		
						
						try {
						
							animalActif.setAntecedents(txtAreaAntecedents.getText());
							animalMgr.update(animalActif);
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tableModelAgenda.fireTableDataChanged();
						//ferme la fenêtre
						DossierMedicalDialog.this.dispose();
				}
			});
		}
		return this.btnValider;
	}
	
	/**
	 * Bouton qui annule les modifications du dossier médical. 
	 * Retourne sur l'agenda d'un-e vétérinaire 
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
					DossierMedicalDialog.this.dispose();
				}
			});
		}
		return this.btnAnnuler;
	}
	
	/**
	 * Composant contenant l'historique de l'animal : "Antecedents"
	 * @return JPanel
	 */
	private JTextArea getTextAreaAntecedents() {
		if ( this.txtAreaAntecedents == null) {
			txtAreaAntecedents = new JTextArea(8,20);	
			txtAreaAntecedents.setLineWrap(true);
			txtAreaAntecedents.setWrapStyleWord(true);
		}
		
		return this.txtAreaAntecedents;
	}
}
