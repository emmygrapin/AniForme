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
		this.setRole(role);
		this.archive = archive;
	}

	public Personnel(String nom, String motDePasse, String role, boolean archive) {
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.setRole(role);
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
		if (role != null) {
			if (role.equals("Vétérinaire")|role.equals("VET")) {
				this.role = "VET";
			} else if (role.equals("Administrateur")|role.equals("ADM")) {
				this.role = "ADM";
			} else if (role.equals("Secrétaire")|role.equals("SEC")) {
				this.role = "SEC";
			} else {
				this.role = "SEC";
			}
		}

	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return nom;
	}

}
