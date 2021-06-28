package it.polito.tdp.genes.model;

public class Vicino implements Comparable<Vicino>{

	private Genes g;
	private Double peso;
	public Vicino(Genes g, double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Vicino arg0) {
		
		return arg0.peso.compareTo(this.peso);
	}
	
	
}
