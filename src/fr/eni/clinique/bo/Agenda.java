package fr.eni.clinique.bo;


import java.util.Date;


public class Agenda {
	
	private Personnel personnel;
	private Animal animal;
	private Date dateRdv;
	public Agenda(Personnel personnel,Date dateRdv, Animal animal) {
		super();
		this.personnel = personnel;
		this.animal = animal;
		this.dateRdv = dateRdv;
	}
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	@Override
	public String toString() {
		return "Agenda [personnel=" + personnel + ", animal=" + animal + ", dateRdv=" + dateRdv + "]";
	}
	
	

}
