package fr.eni.clinique.bo;

/**
 * 
 * @author mdeoliveira2017
 *
 */
public class Animal {

	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private Race race;

	private Client client;
	private String tatouage;
	private String antecedents;
	private boolean archive;

	
	public Animal() {
		
	}
	
	public Animal(String nomAnimal, String sexe, String couleur, Race race,
			Client client, String tatouage, String antecedents, Boolean archive ) {
		super();
		setNomAnimal(nomAnimal);
		setSexe(sexe);
		setCouleur(couleur);
		setRace(race);
		setClient(client);
		setTatouage(tatouage);
		setAntecedents(antecedents);
		setArchive(archive);
	}

	public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, Race race,
			Client client, String tatouage, String antecedents, boolean archive) {
		super();
		setCodeAnimal(codeAnimal);
		setNomAnimal(nomAnimal);
		setSexe(sexe);
		setCouleur(couleur);
		setRace(race);
		setClient(client);
		setTatouage(tatouage);
		setAntecedents(antecedents);
		setArchive(archive);
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public String getSexe() {
		
		return sexe;
	}

	public void setSexe(String sexe) {
		if (sexe != null) {
			if (sexe.equals("Mâle")) { 
				this.sexe = "M";
			} else if (sexe.equals("Femelle")) {
				this.sexe = "F";			
			} else if (sexe.equals("Hermaphrodite")){
				this.sexe = "H";
			} else {
				this.sexe = "H";
			}
		}
		
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		
			this.race = race;
	
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getTatouage() {
		return tatouage;
	}

	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}

	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		
		
		this.archive = archive;
	}


	@Override
	public String toString() {
		return "Animal :\n\t[codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", race=" + race + ", client=" + client + ", tatouage=" + tatouage + ", antecedents=" + antecedents
				+ ", archive=" + archive  + "]\n";
	}
	
	
	
	
	
	
}

