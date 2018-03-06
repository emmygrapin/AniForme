package fr.eni.clinique.bll;

import java.text.SimpleDateFormat;
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
	
	public List<Agenda> getAgendasParVeto(int codeVeto){
		List<Agenda> listeAgendasVeto = null;
		try{
		 daoAgenda.selectByVeterinaire(codeVeto);
		}
		catch (DALException e) {
			// TODO: handle exception
		}
		return listeAgendasVeto;	
	}
	
	public List<Agenda> getAgendasParVetoParDate(int codeVeto, Date dateRdv){
		List<Agenda> listeAgendasDate = null;
		try{
			
			SimpleDateFormat dateRdvF = new SimpleDateFormat("yyyy-dd-MM HH:mm");
			String date = dateRdvF.format(dateRdv );
			daoAgenda.selectByVeterinaireByDate(codeVeto, date);
		}
		catch(DALException e){
			
		}
		return listeAgendasDate;
	}
	
	public void addAgenda(Agenda data) throws DALException{
		daoAgenda.insert(data);
	}
	
	public void deleteAgenda(Agenda data) throws DALException{
		daoAgenda.delete(data);
	}

}
