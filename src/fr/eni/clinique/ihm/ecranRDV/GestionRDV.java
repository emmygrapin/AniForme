package fr.eni.clinique.ihm.ecranRDV;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.PersonnelMger;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DALException;

public class GestionRDV extends JInternalFrame  implements ActionListener {
	
	
	private JTextField txtNom, txtPrenom, txtAdresse, txtAdresse2, txtVille, txtCP, txtPhone, txtEmail, txtAssurance;
	private JTextArea txtRemarque;
	private JButton btnValider, btnAnnuler, btnArchiver;
	private JComboBox cmbClient, cmbAnimal, cmbVeto;
	private Client clientActif;
	private List<Integer> clientsID;
	Hashtable<Integer, Vector<String>> cbxItems;
	
	public GestionRDV(){
		
		super("Création Rendez-vous", true, true, true,true);
		
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

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		JPanel modulePar = new JPanel();
		modulePar.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 30, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		modulePar.add(new JLabel("Vétérinaire : "), gbc);
		gbc.gridy = 1;
		modulePar.add(datePicker, gbc);
		
		return modulePar;
	}
	
	
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
		Vector<String> vetos = new Vector<String>();
		
		if ( this.cmbVeto == null ) {
			List<Personnel> listeVetos = PersonnelMger.getInstance().getVeterinaires();
			
			for (Personnel veto : listeVetos) {
				vetos.add(veto.getNom());
			}
			
			this.cmbVeto = new JComboBox(vetos);
			cmbVeto.addActionListener(this);
		}

		return this.cmbVeto;	
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
				
				Vector<String> animaux = new Vector<String>();
				
				List<Animal> AnimauxbyClient = AnimalManager.getInstance().getAnimauxByClient(client);
				
				for (Animal unAnimal : AnimauxbyClient) {
					animaux.add(unAnimal.getNomAnimal());
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
		
	}
	
	
	
	
	

}
