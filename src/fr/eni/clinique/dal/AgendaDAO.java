package fr.eni.clinique.dal;

import java.sql.Date;
import java.util.List;

import fr.eni.clinique.bo.Agenda;


public interface AgendaDAO {
	public List<Agenda> selectByVeterinaire(int codeVeto) throws DALException;
	
	public List<Agenda> selectByVeterinaireByDate(int codeVeto, String date)throws DALException;
	
	public void insert(Agenda data) throws DALException;
	
	public void delete(Agenda data) throws DALException;
}
