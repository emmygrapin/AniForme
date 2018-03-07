package fr.eni.clinique.ihm.ecranAgenda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.Calendar;


import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bll.RaceManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.DALException;

public class AgendaGestion extends JInternalFrame implements ActionListener{
	private TableAgenda tableAgenda;
	private JPanel panelGestionAgendas;
	private JComboBox choixVeterinaire;
	private JFrame parent;
	private JPanel dateAgenda;
	private UtilDateModel model;


	public AgendaGestion(JFrame parent) {
		super("Agenda", true, true, true, true);
		this.parent = parent;
		this.setSize(900, 800);
		this.setContentPane(viewGestionAgendas());
		// Cache l'application lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * panel Global de Gestion de l'agenda contient le panel des combo box et le
	 * tableau de l'agenda
	 * 
	 * @return
	 */
	public JPanel viewGestionAgendas() {
		panelGestionAgendas = new JPanel();

		panelGestionAgendas.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestionAgendas.add(getChoixVeterinaire(), gbc);
		gbc.gridx = 1;
		panelGestionAgendas.add(getFieldDate(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth =2;
		panelGestionAgendas.add(new JScrollPane(getTableAgenda()), gbc);

		return panelGestionAgendas;

	}
	// Tableau de rdv pour un vétérinaire
	public TableAgenda getTableAgenda() {

		// if (tableAgenda == null) {
		Personnel premierVeterinaireListe = PersonnelMger.getInstance().getPersonnels().get(0);
		if (premierVeterinaireListe != null) {
			tableAgenda = new TableAgenda(premierVeterinaireListe.getCodePerso(), new Date());
			tableAgenda.setFillsViewportHeight(true);
			tableAgenda.setPreferredScrollableViewportSize(new Dimension(500, 200));
		}

		// }
		return tableAgenda;
	}
	// Permet de choisir le vétérinaire dont on veut afficher le planning
	public JComboBox getChoixVeterinaire() {
		Vector<String> veterinaires = new Vector<String>();

			List<Personnel> listeVeto = PersonnelMger.getInstance().getVeterinaires();

			for (Personnel veto : listeVeto) {
				veterinaires.add(veto.getNom());
			}

		if (this.choixVeterinaire == null) {
			this.choixVeterinaire = new JComboBox(veterinaires);
			choixVeterinaire.addActionListener(this);
		}

		return this.choixVeterinaire;

	}
	
	/**
	 * Créer et/ou retourne un champ Nom
	 * @return JTextField
	 */
	private JPanel getDatePanel() {
		UtilDateModel model = new UtilDateModel();
       
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        dateAgenda.add(datePicker);
        
		;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
