package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Nerc n = new Nerc(16, "MAAC");
		//System.out.println(model.getPowerOutagePerNerc(n));
		
		System.out.println(model.calcolaWorstCase(n, 200, 4));

	}
}
