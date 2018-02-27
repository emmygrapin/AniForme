package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Client;

public class TestClientJDBC {

	public static void main(String[] args) throws DALException {
		// TODO Auto-generated method stub
		Client client = new Client (0, "Le Mercier", "Alexandre", "adresse1", "adresse2", "44000", "Nantes", "02000000", "assurance Rattounes", "a@a.com", "remarque", false);
		ClientDAO dao = DAOFactory.getClientDAO();
		
		System.out.println("Creation client -----------------");
		
		System.out.println(client.toString());
		
		Client client2 = dao.insert(client);
		
		System.out.println("Update client -----------------");
		
		client2.setAdresse1("new_adresse");
		
		dao.update(client2);
		
		Client client3 = dao.selectById(client2.getCodeClient());
		
		System.out.println(client3.toString());
		
		System.out.println("Delete client -----------------");
		
		dao.delete(client3.getCodeClient());
		
		System.out.println("Select All : ");
		
		List<Client> clients = dao.selectAll();
		
		for(Client unclient:clients)
		{
			System.out.println(unclient.toString());
		}
		
		
	}

}
