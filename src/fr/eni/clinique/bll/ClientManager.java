package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

public class ClientManager {

	private ClientDAO daoClient;
	private static ClientManager _instance;
	
	private ClientManager(){
		daoClient = DAOFactory.getClientDAO();
	}
	
	public static ClientManager getInstance() 
	{
		if(_instance == null)
		{
			_instance = new ClientManager();
		}
		return _instance;		
	}
	
	public List<Client> getClients() throws DALException
	{
		return daoClient.selectAll();
	}
	
	public Client getClient(int idClient) throws DALException{
		return daoClient.selectById(idClient);
	}
	
	public List<Client> getClientsByNom(String nom) throws DALException{
		return daoClient.selectByNom(nom);
	}
	
	public void addClient(Client client) throws DALException 
	{
		daoClient.insert(client);
		
	}
	
	public void removeClient(Client client) throws DALException
	{
		daoClient.delete(client.getCodeClient());
	}
	
}
