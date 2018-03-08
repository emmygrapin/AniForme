package fr.eni.clinique.ihm.ecranAgenda;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.ecranDossMedical.DossierMedicalDialog;
import fr.eni.clinique.ihm.ecranPrincipal.EcranPrincipalClinique;

public class AgendaGestion extends JInternalFrame implements ActionListener {
	private TableAgenda tableAgenda;
	private JPanel panelGestionAgendas;
	private JComboBox choixVeterinaire;
	private JFrame parent;
	private JPanel dateAgenda;
	private UtilDateModel model;
	private Personnel personnelConnecte;
	private JDatePickerImpl datePicker;
	private JButton btnDossierMedical;
	private static JOptionPane alert;
	private Agenda agenda;

	public AgendaGestion(JFrame parent, Personnel personnelActif) {
		super("Agenda", true, true, true, true);
		this.parent = parent;
		this.personnelConnecte = personnelActif;
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
		panelGestionAgendas.add(getChoixVeterinaire(personnelConnecte), gbc);
		gbc.gridx = 1;
		panelGestionAgendas.add(getDatePanel(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth =2;
		panelGestionAgendas.add(new JScrollPane(getTableAgenda(personnelConnecte)), gbc);
		gbc.gridy = 2;
		panelGestionAgendas.add(getDossierMedical(), gbc);
		

		return panelGestionAgendas;

	}

	// Tableau de rdv pour un vétérinaire
	public TableAgenda getTableAgenda(Personnel personnelConnecte) {
		if (personnelConnecte != null) {
			if (personnelConnecte.getRole() == "VET") {
				tableAgenda = new TableAgenda(personnelConnecte);
			} else {
				if (tableAgenda == null) {
					Personnel premierVeterinaireListe = PersonnelMger.getInstance().getVeterinaires().get(0);
					if (premierVeterinaireListe != null) {
						tableAgenda = new TableAgenda(premierVeterinaireListe);

					}
				}
			}

			tableAgenda.setFillsViewportHeight(true);
			tableAgenda.setPreferredScrollableViewportSize(new Dimension(500, 200));
		}

		return tableAgenda;
	}

	// Permet de choisir le vétérinaire dont on veut afficher le planning
	public JComboBox getChoixVeterinaire(Personnel personnelConnecte) {
		Vector<Personnel> veterinaires = new Vector<Personnel>();

		List<Personnel> listeVeto = PersonnelMger.getInstance().getVeterinaires();
		int indexCbx =0;
		int i =0;
		for (Personnel veto : listeVeto) {
			veterinaires.add(veto);
			if(veto.getNom().equals(personnelConnecte.getNom())){
				indexCbx= i;
			}
			i++;
		}
		
		if (this.choixVeterinaire == null) {
			this.choixVeterinaire = new JComboBox(veterinaires);
			if (personnelConnecte.getRole() == "VET" || personnelConnecte.getRole() == "ADM") {	
				choixVeterinaire.setSelectedIndex(indexCbx);
			} 
			choixVeterinaire.addActionListener(this);
		}
		
		return this.choixVeterinaire;

	}

	/**
	 * 
	 * @return
	 */
	private JPanel getDatePanel() {

		UtilCalendarModel model = new UtilCalendarModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl dateAgenda = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(dateAgenda, new DateComponentFormatter());
		datePicker = new JDatePickerImpl(dateAgenda, new DateComponentFormatter());
		
		datePicker.addActionListener(this);
		
		return datePicker;
	}

	
	private JButton getDossierMedical() {
		if (this.btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier médical");
			btnDossierMedical.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (tableAgenda.getAnimalRdvSelect() != null) {
						Animal animal = tableAgenda.getAnimalRdvSelect();
						Client client = tableAgenda.getAnimalRdvSelect().getClient();
						DossierMedicalDialog  dossierMedical = new DossierMedicalDialog(client, animal, AgendaGestion.this, tableAgenda);
						
					} else {
						alert.showMessageDialog(null, "Veuillez sélectionner un animal pour voir son dossier médical", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		
		}
		
		return this.btnDossierMedical;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// TODO Auto-generated method stub
		//	Personnel veterinaire = (Personnel) choixVeterinaire.getSelectedItem();
	


		
		//Traitement de la date
		
		int years = datePicker.getModel().getYear();
		int days = datePicker.getModel().getDay();
		int mounth = datePicker.getModel().getMonth();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.setTimeZone(new java.util.SimpleTimeZone(0, "TS"));
		cal.set(years, mounth, days);
		Date date = cal.getTime();

		Personnel veterinaire = (Personnel) choixVeterinaire.getSelectedItem();
		tableAgenda.setPersonnel(veterinaire);
		tableAgenda.setInfos(veterinaire, date);
	
	}
}
