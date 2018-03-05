package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author alemercier2017
 *
 */
public class Client {
	
	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numeroTel;
	private String assurance;
	private String email;
	private String remarque;
	private Boolean archive;
	private List<Animal> animaux;
	
	
	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numeroTel, String assurance, String email, String remarque,
			Boolean archive, List<fr.eni.clinique.bo.Animal> animaux) {
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numeroTel = numeroTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
		this.animaux = animaux;
	}

	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numeroTel, String assurance, String email, String remarque,
			Boolean archive) {
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numeroTel = numeroTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
		this.animaux = new ArrayList<Animal>();
	}
	
	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(ArrayList<Animal> animaux) {
		this.animaux = animaux;
	}

	public int getCodeClient() {
		return codeClient;
	}
	
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	
	public String getNomClient() {
		return nomClient;
	}
	
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	public String getPrenomClient() {
		return prenomClient;
	}
	
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	
	public String getAdresse1() {
		return adresse1;
	}
	
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	
	public String getAdresse2() {
		return adresse2;
	}
	
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	
	public String getAssurance() {
		return this.assurance;
	}
	
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRemarque() {
		return remarque;
	}
	
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	
	public Boolean getArchive() {
		return this.archive;
	}
	
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Clients [codeClient=" + codeClient + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient
				+ ", adresse1=" + adresse1 + ", adresse2=" + adresse2 + ", codePostal=" + codePostal + ", ville="
				+ ville + ", numeroTel=" + numeroTel + ", assurance=" + assurance + ", email=" + email + ", remarque="
				+ remarque + ", archive=" + archive + ", animaux=" + animaux + "]";
	}
	
	
	

}
