/**
 * 
 */
package fr.eni.clinique.bo;

/**
 * @author egrapin2017
 *
 */
public class Personnel {
	
	private int codePerso;
	private String nom;
	private String motDePasse;
	private String role;
	private boolean archive;
	
	public Personnel(int codePerso, String nom, String motDePasse, String role, boolean archive) {
		this.codePerso = codePerso;
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.role = role;
		this.archive = archive;
	}

	public int getCodePerso() {
		return codePerso;
	}

	public void setCodePerso(int codePerso) {
		this.codePerso = codePerso;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Personnels [codePerso=" + codePerso + ", nom=" + nom + ", motDePasse=" + motDePasse + ", role=" + role
				+ ", archive=" + archive + "]";
	}
	

}
