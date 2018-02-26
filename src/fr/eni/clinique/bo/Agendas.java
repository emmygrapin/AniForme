package fr.eni.clinique.bo;

import java.util.Date;

public class Agendas {
	
	private Personnels codeVeto;
	private Animaux codeAnimal;
	private Date dateRdv;
	public Agendas(Personnels codeVeto, Animaux codeAnimal, Date dateRdv) {
		super();
		this.codeVeto = codeVeto;
		this.codeAnimal = codeAnimal;
		this.dateRdv = dateRdv;
	}
	public Personnels getCodeVeto() {
		return codeVeto;
	}
	public void setCodeVeto(Personnels codeVeto) {
		this.codeVeto = codeVeto;
	}
	public Animaux getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(Animaux codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	

}
