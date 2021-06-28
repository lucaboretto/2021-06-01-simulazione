package it.polito.tdp.genes.model;

public class Adiacenza {
	
	private String id1;
	private String id2;
	private double peso;
	private int crom1;
	private int crom2;
	public Adiacenza(String id1, String id2, double peso, int crom1, int crom2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
		this.crom1 = crom1;
		this.crom2 = crom2;
	}
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public int getCrom1() {
		return crom1;
	}
	public void setCrom1(int crom1) {
		this.crom1 = crom1;
	}
	public int getCrom2() {
		return crom2;
	}
	public void setCrom2(int crom2) {
		this.crom2 = crom2;
	}
	
	
	
	
}
