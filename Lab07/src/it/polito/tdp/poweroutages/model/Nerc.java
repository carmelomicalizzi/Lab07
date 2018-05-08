package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class Nerc {

	private int id;
	private String value;

	private List<PowerOutage> powerOutages;
	
	public Nerc() {
		powerOutages = new ArrayList<PowerOutage>();
	}
	
	public Nerc(int id, String value) {
		powerOutages = new ArrayList<PowerOutage>();
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<PowerOutage> getPowerOutages() {
		return powerOutages;
	}

	public void setPowerOutages(List<PowerOutage> powerOutages) {
		this.powerOutages = powerOutages;
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
		Nerc other = (Nerc) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}
}
