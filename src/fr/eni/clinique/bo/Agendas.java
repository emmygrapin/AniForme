package fr.eni.clinique.bo;

import java.util.Date;

public class Agendas {
	
	private Personnels codeVeto;
	private Animaux animal;
	private Date dateRdv;
	public Agendas(Personnels codeVeto, Animaux codeAnimal, Date dateRdv) {
		super();
		this.codeVeto = codeVeto;
		this.animal = codeAnimal;
		this.dateRdv = dateRdv;
	}
	public Personnels getCodeVeto() {
		return codeVeto;
	}
	public void setCodeVeto(Personnels codeVeto) {
		this.codeVeto = codeVeto;
	}
	public Animaux getCodeAnimal() {
		return animal;
	}
	public void setCodeAnimal(Animaux codeAnimal) {
		this.animal = codeAnimal;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	

}
