package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;



public class PowerOutage {
	
	private int id;
	private int anno; 
	private LocalDateTime dateBegan ;
	private LocalDateTime dateFinished ;
	private int numCustomersAffected ;
	private int oreDisservizio ;
	
	
	public PowerOutage(int id, Timestamp dateBegan, Timestamp dateFinished, int numCustomersAffected) {
		
		this.id = id;
		anno = dateBegan.toLocalDateTime().getYear();
		this.dateBegan = dateBegan.toLocalDateTime();
		this.dateFinished = dateFinished.toLocalDateTime();
		this.numCustomersAffected = numCustomersAffected;
		this.oreDisservizio = (int) ((dateFinished.getTime() - dateBegan.getTime())/3600000);
	}

	
	public PowerOutage(PowerOutage powerOutage) {
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(anno+" ");
		builder.append(dateBegan+" ");		
		builder.append(dateFinished+" ");
		builder.append(oreDisservizio+" ");
		builder.append(numCustomersAffected);
		
		return builder.toString();
	}


	public int getId() {
		return id;
	}


	public int getAnno() {
		return anno;
	}


	public LocalDateTime getDateBegan() {
		return dateBegan;
	}


	public LocalDateTime getDateFinished() {
		return dateFinished;
	}


	public int getNumCustomersAffected() {
		return numCustomersAffected;
	}


	public int getOreDisservizio() {
		return oreDisservizio;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
