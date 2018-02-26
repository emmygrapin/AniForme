package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Clients;

/**
 * 
 * @author alemercier2017
 *
 */

public interface ClientDAO {

	public Clients selectById(int codeClient) throws DALException;
	
	public List<Clients> selectAll() throws DALException;
	
	public void insert(Clients data) throws DALException;
	
	public void update(Clients data) throws DALException;
	
	public void delete(int codeClient) throws DALException;
	
}
