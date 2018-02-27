package fr.eni.clinique.bll;

public class AppliTestBLL {

	public static void main(String[] args) throws BLLException {
		LoginMger authentifier = LoginMger.getInstance();
		//authentifier.getConnexion("grapin", "password");
		authentifier.getConnexion("gaillard", "password1");
		
	}

}
