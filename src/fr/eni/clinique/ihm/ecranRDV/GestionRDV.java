package fr.eni.clinique.ihm.ecranRDV;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.AgendaManager;
import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.ihm.ecranAgenda.TableAgenda;
import fr.eni.clinique.ihm.ecranClient.AjoutClient;
import fr.eni.clinique.ihm.ecranClient.InfosClient;

public class GestionRDV extends JInternalFrame  implements ActionListener {
	
	
	private JButton btnValider, btnDelete;
	private JComboBox cmbClient, cmbAnimal, cmbVeto, cmbHours, cmbMinutes;

	private List<Integer> clientsID;
	private List<Personnel> personnels;
	private JDatePickerImpl datePicker;
	Hashtable<Integer, Vector<Animal>> cbxItems;
	private TableAgenda tableAgenda;
	
	public GestionRDV(){
		
		super("Prise de Rendez-vous", true, true, true,true);
		
		// Réglage de la taille du conteneur
		this.setSize(900, 800);
	
		this.setContentPane(globalRDV());
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		cmblinkedClientAnimaux();
		
	}
	
	
	
	public JPanel globalRDV()
	{
		JPanel panelRDV = new JPanel();
		panelRDV.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelRDV.add(modulePour(), gbc);
		gbc.gridx = 1;
		panelRDV.add(modulePar(), gbc);
		gbc.gridx = 2;
		panelRDV.add(moduleQuand(), gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		panelRDV.add(moduleAgenda(), gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		panelRDV.add(getBtnSave(), gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		panelRDV.add(getBtnDelete(), gbc);
		
		return panelRDV;
	}
	
	
	
	public JPanel modulePour()
	{
		JPanel modulePour = new JPanel();
		modulePour.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 30, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		modulePour.add(new JLabel("Client : "), gbc);
		gbc.gridy = 1;
		modulePour.add(addcmbClient(), gbc);
		gbc.gridy = 2;
		modulePour.add(new JLabel("Animal : "), gbc);
		gbc.gridy = 3;
		modulePour.add(addcmbAnimal(), gbc);		
		
		return modulePour;
	}
	
	public JPanel modulePar()
	{
		JPanel modulePar = new JPanel();
		modulePar.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 30, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		modulePar.add(new JLabel("Vétérinaire : "), gbc);
		gbc.gridy = 1;
		modulePar.add(addcmbVeto(), gbc);
		
		return modulePar;
	}
	
	public JPanel moduleQuand()
	{
		
		UtilCalendarModel model = new UtilCalendarModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		datePicker.addActionListener(this);
		
		JPanel modulePar = new JPanel();
		modulePar.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 30, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		modulePar.add(new JLabel("Date : "), gbc);
		gbc.gridy = 1;
		modulePar.add(datePicker, gbc);
		gbc.gridy = 2;
		modulePar.add(addcmbHeures(), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		modulePar.add(addcmbMinutes(), gbc);
		
		return modulePar;
	}
	
	public JPanel moduleAgenda()
	{		
		JPanel moduleAgenda = new JPanel();
		moduleAgenda.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		moduleAgenda.add(new JScrollPane(getTableAgenda()), gbc);
		
		
		return moduleAgenda;
	}
	
	//-------------------------Table-----------------------------------------------
	
	public TableAgenda getTableAgenda() {

		if (tableAgenda == null) {
			Personnel premierVeterinaireListe = PersonnelMger.getInstance().getVeterinaires().get(0);
			if (premierVeterinaireListe != null) {
				tableAgenda = new TableAgenda(premierVeterinaireListe);
				tableAgenda.setFillsViewportHeight(true);
				tableAgenda.setPreferredScrollableViewportSize(new Dimension(500, 200));
			}
			else {
				
			}
		}
			return tableAgenda;
		}
	
	
	
	
	
	//--------------------------------------------------------------------------
	
	
	public JComboBox addcmbClient()
	{
		Vector<String> clients = new Vector<String>();
		
		if ( this.cmbClient == null ) {
			try {
				List<Client> listeClients = ClientManager.getInstance().getClients();
				clientsID = new ArrayList<Integer>();
				for (Client client : listeClients) {
					clients.add(client.getNomClient()+ " " + client.getPrenomClient());
					clientsID.add(client.getCodeClient());
				}
				
			} catch (DALException e) {
				// TODO erreur message requête 
				e.printStackTrace();
			}
			
		
				this.cmbClient = new JComboBox(clients);
				cmbClient.addActionListener(this);
		}

		return this.cmbClient;	
	}
	
	public JComboBox addcmbVeto()
	{
		Vector<Personnel> vetos = new Vector<Personnel>();
		personnels = new ArrayList<Personnel>();
		
		if ( this.cmbVeto == null ) {
			List<Personnel> listeVetos = PersonnelMger.getInstance().getVeterinaires();
			
			for (Personnel veto : listeVetos) {
				vetos.add(veto);
				personnels.add(veto);
			}
			
			this.cmbVeto = new JComboBox(vetos);
			cmbVeto.addActionListener(this);
		}

		return this.cmbVeto;	
	}
	
	
	public JComboBox addcmbHeures()
	{
		Vector<Integer> heures = new Vector<Integer>();
		
		if ( this.cmbHours == null ) {
			
			for (int i = 0; i < 24; i ++) {
				heures.add(i);
			}
			
			this.cmbHours = new JComboBox(heures);
		}

		return this.cmbHours;	
	}
	
	
	public JComboBox addcmbMinutes()
	{
		Vector<Integer> minutes = new Vector<Integer>();
				
		minutes.add(00);
		minutes.add(15);
		minutes.add(30);
		minutes.add(45);
		
		this.cmbMinutes = new JComboBox(minutes);

		return this.cmbMinutes;	
	}

	
	private JComboBox addcmbAnimal() {
		
		if (this.cmbAnimal == null) {
			this.cmbAnimal = new JComboBox();			
		}
		return this.cmbAnimal;
	}

	

	public void cmblinkedClientAnimaux() {
		
		cbxItems = new Hashtable<>();
		List<Client> listeClient;
		
		try {
			listeClient = ClientManager.getInstance().getClients();
			for (Client client : listeClient) {
				
				Vector<Animal> animaux = new Vector<Animal>();
				
				List<Animal> AnimauxbyClient = AnimalManager.getInstance().getAnimauxByClient(client);
				
				for (Animal unAnimal : AnimauxbyClient) {
					animaux.add(unAnimal);
				}
				
				cbxItems.put(client.getCodeClient(), animaux);
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int index = cmbClient.getSelectedIndex();
		
		int clientID = clientsID.get(index);
		
		Object obj = cbxItems.get(clientID);
		
		if ( obj == null) {
			cmbAnimal.setModel(new DefaultComboBoxModel());
		} else {
			cmbAnimal.setModel(new DefaultComboBoxModel((Vector<String>) obj));
		}
		
		
		//Traitement de la date
		
		int years = datePicker.getModel().getYear();
		int days = datePicker.getModel().getDay();
		int mounth = datePicker.getModel().getMonth();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.setTimeZone(new java.util.SimpleTimeZone(0, "TS"));
		cal.set(years, mounth, days);
		Date date = cal.getTime();

		Personnel veterinaire = (Personnel) cmbVeto.getSelectedItem();
		tableAgenda.setInfos(veterinaire, date);
		
		
		
	}
	
	public void  refreshTable ()
	{
		int index = cmbClient.getSelectedIndex();
		
		int clientID = clientsID.get(index);
		
		Object obj = cbxItems.get(clientID);
		
		if ( obj == null) {
			cmbAnimal.setModel(new DefaultComboBoxModel());
		} else {
			cmbAnimal.setModel(new DefaultComboBoxModel((Vector<String>) obj));
		}
		
		
		//Traitement de la date
		
		int years = datePicker.getModel().getYear();
		int days = datePicker.getModel().getDay();
		int mounth = datePicker.getModel().getMonth();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.setTimeZone(new java.util.SimpleTimeZone(0, "TS"));
		cal.set(years, mounth, days);
		Date date = cal.getTime();
	
		Personnel veterinaire = (Personnel) cmbVeto.getSelectedItem();
		tableAgenda.setInfos(veterinaire, date);
	}
	
	
	public JButton getBtnSave()
	{
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(cmbAnimal.getSelectedItem() == null)
				{
					
				}
				else
				{
					int index = cmbVeto.getSelectedIndex();
					int years = datePicker.getModel().getYear();
					int days = datePicker.getModel().getDay();
					int mounth = datePicker.getModel().getMonth();
					int hours = (int)cmbHours.getSelectedItem() - 1;
					int minutes = (int)cmbMinutes.getSelectedItem();

					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(0);
					cal.setTimeZone(new java.util.SimpleTimeZone(0, "TS"));
					cal.set(years, mounth, days, hours, minutes, 00);
					Date date2 = cal.getTime();
					
					Agenda agenda = new Agenda(personnels.get(index), date2,(Animal)cmbAnimal.getSelectedItem());
					try {
						AgendaManager.getInstance().addAgenda(agenda);
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshTable();
					
				}
			}
		});
	
		return btnValider;
	}
	
	public JButton getBtnDelete()
	{

		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (tableAgenda.getRdvSelect() != null)
				{
					try {
						AgendaManager.getInstance().deleteAgenda(tableAgenda.getRdvSelect());
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshTable();
				}
			}
		});
	
		return btnDelete;
	}
	
	
	
	
	

}
