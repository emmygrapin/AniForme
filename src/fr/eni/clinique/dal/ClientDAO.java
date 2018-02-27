package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Client;

/**
 * 
 * @author alemercier2017
 *
 */

public interface ClientDAO {

	public Client selectById(int codeClient) throws DALException;
	
	public List<Client> selectByNom(String nomClient) throws DALException;
	
	public List<Client> selectAll() throws DALException;
	
	public Client insert(Client data) throws DALException;
	
	public void update(Client data) throws DALException;
	
	public void delete(int codeClient) throws DALException;
	
}
