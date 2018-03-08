package fr.eni.clinique.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.dal.AgendaDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

public class AgendaManager {

	private AgendaDAO daoAgenda;
	private static AgendaManager _instance;
	
	private AgendaManager() {
		daoAgenda = DAOFactory.getAgendaDAO();
	}
	
	public static AgendaManager getInstance() {
		
		if (_instance == null) {
			_instance = new AgendaManager();
		}
		return _instance;
		
	}
	
	/**
	 * sélection de l'agenda par codePerso
	 * @param codeVeto
	 * @return
	 */
	public List<Agenda> getAgendasParVeto(int codeVeto)throws DALException{
		List<Agenda> listeAgendasVeto = new ArrayList<Agenda>();
		try{
			listeAgendasVeto = daoAgenda.selectByVeterinaire(codeVeto);
		}
		catch (DALException e) {
			// TODO: handle exception
		}
		return listeAgendasVeto;	
	}
	
	/**
	 * sélection de l'agenda par codePerso et date
	 * @param codeVeto
	 * @param dateRdv
	 * @return
	 */
	public List<Agenda> getAgendasParVetoParDate(int codeVeto, Date dateRdv)throws DALException{
		List<Agenda> listeAgendasDate = new ArrayList<Agenda>();
		try
		{
			listeAgendasDate = daoAgenda.selectByVeterinaireByDate(codeVeto, dateRdv);
		}
		catch(DALException e){
			
		}
		return listeAgendasDate;
	}
	
	/**
	 * ajout d'un nouveau rdv dans l'agenda
	 * @param data
	 * @throws DALException
	 */
	public void addAgenda(Agenda data) throws DALException{
		daoAgenda.insert(data);
	}
	
	/**
	 * suppression d'un rdv dans l'agenda
	 * @param data
	 * @throws DALException
	 */
	public void deleteAgenda(Agenda data) throws DALException{
		daoAgenda.delete(data);
	}

}
