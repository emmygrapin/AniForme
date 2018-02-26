package fr.eni.clinique.bo;

public class Race {

	private String race;
	private String espece;
	
	public Race(String race, String espece) {
		super();
		this.race = race;
		this.espece = espece;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	@Override
	public String toString() {
		return "Race [race=" + race + ", espece=" + espece + "]";
	}
	
	
	
}
