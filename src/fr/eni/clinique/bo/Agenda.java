package fr.eni.clinique.bo;

import java.util.Date;

public class Agenda {
	
	private Personnel codeVeto;
	private Animal animal;
	private Date dateRdv;
	public Agenda(Personnel codeVeto, Animal codeAnimal, Date dateRdv) {
		super();
		this.codeVeto = codeVeto;
		this.animal = codeAnimal;
		this.dateRdv = dateRdv;
	}
	public Personnel getCodeVeto() {
		return codeVeto;
	}
	public void setCodeVeto(Personnel codeVeto) {
		this.codeVeto = codeVeto;
	}
	public Animal getCodeAnimal() {
		return animal;
	}
	public void setCodeAnimal(Animal codeAnimal) {
		this.animal = codeAnimal;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	

}
