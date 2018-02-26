package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Clients;

public class TestClientJDBC {

	public static void main(String[] args) throws DALException {
		// TODO Auto-generated method stub
		Clients client = new Clients (0, "Le Mercier", "Alexandre", "adresse1", "adresse2", "44000", "Nantes", "02000000", "assurance Rattounes", "a@a.com", "remarque", false);
		ClientDAO dao = DAOFactory.getClientDAO();
		
		client.toString();
		
		dao.insert(client);
		
		
	}

}
