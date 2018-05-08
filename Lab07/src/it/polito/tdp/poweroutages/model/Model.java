package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private List<PowerOutage> powerOutage;
	private List<PowerOutage> powerOutagePerNerc;
	private List<PowerOutage> soluzione;
	private int bestScore;
	PowerOutageIdMap powerOutageMap;
	
	public Model() {
		podao = new PowerOutageDAO();
		
		powerOutageMap = new PowerOutageIdMap();
		
		
		powerOutage = podao.getAllPowerOutage(powerOutageMap);
		System.out.println(powerOutage.size());

	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> getPowerOutagePerNerc(Nerc nerc) {
		return podao.getPowerOutageFromNerc(nerc, powerOutageMap);
	}

	public List<PowerOutage> getAllPowerOutage() {
		return podao.getAllPowerOutage(powerOutageMap);
	}
	
	
	public String calcolaWorstCase(Nerc nerc, int oreDisservizioMax, int maxAnni) {
		
		bestScore = Integer.MIN_VALUE;
		int livello = 0;
		powerOutagePerNerc = new ArrayList<PowerOutage>();
		powerOutagePerNerc = this.getPowerOutagePerNerc(nerc);
		
		soluzione = new ArrayList<PowerOutage>();
		
		recursive(soluzione, 0, oreDisservizioMax, maxAnni, livello, 0);
		
		String elencoSoluzione = "Numero clienti interessati: " + this.calcolaClientiInteressati(soluzione) + "\nOre totali disservizio: "
								 + this.calcolaOreDisservizio(soluzione) + "\n";
		
		for(PowerOutage p : soluzione) {
			elencoSoluzione += p.toString()+"\n";
		}
		
		return elencoSoluzione;
	}
	
	public void recursive(List<PowerOutage> parziale, int oreDisservizio, int oreDisservizioMax, int maxAnni, int livello, int counter) {

			if(this.calcolaClientiInteressati(parziale) > bestScore) {
				bestScore = this.calcolaClientiInteressati(parziale);
				soluzione = new ArrayList<PowerOutage>(parziale);
				} 
				

		for(PowerOutage p : powerOutagePerNerc) {
			
			if(!parziale.contains(p)) {
			parziale.add(p);

			if(this.disservizioMaxValido(parziale, oreDisservizioMax, p) && this.intervalloTempoValido(parziale, maxAnni, p)) {
				recursive(parziale, this.calcolaOreDisservizio(parziale), oreDisservizioMax, maxAnni, livello+1, counter);
						}
		parziale.remove(livello);	
			}
	}
}
	
		
	private int calcolaOreDisservizio(List<PowerOutage> parziale) {

		int totOreDisservizio = 0;
		for(PowerOutage p : parziale) {
			totOreDisservizio += p.getOreDisservizio();
			
		}
		
		return totOreDisservizio;
		
	}

	private boolean disservizioMaxValido(List<PowerOutage> parziale, int oreDisservizioMax, PowerOutage poweroutage) {

		int totOreDisservizio = 0;
		for(PowerOutage p : parziale) {
			totOreDisservizio += p.getOreDisservizio();
			
		}
		
			if(totOreDisservizio > oreDisservizioMax)
				return false;
		
		else return true;
		
	}

	public boolean intervalloTempoValido(List<PowerOutage> parziale, int maxAnni, PowerOutage poweroutage) {
		
		int annoMinimo = Integer.MAX_VALUE;
		
		for(PowerOutage p : parziale) {
			if(p.getAnno() < annoMinimo)
				annoMinimo = p.getAnno();
		}
		
		if((parziale.get(parziale.size()-1).getAnno()) - annoMinimo > maxAnni)
			return false;
		
		else return true;
		
	
		
	}
	
	public int calcolaClientiInteressati(List<PowerOutage> parziale) {
		
		int sommaClienti = 0;
		
		for(PowerOutage p : parziale) {
			sommaClienti += p.getNumCustomersAffected();
		}
		
		
		return sommaClienti;
		
	}
		
	
	
}
