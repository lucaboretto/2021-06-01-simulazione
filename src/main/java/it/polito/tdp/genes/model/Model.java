package it.polito.tdp.genes.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	Map<String, Genes> idGenesMap;
	
	public Model()	{
		this.dao = new GenesDao();
	}
	
	public void creaGrafo() {
		this.idGenesMap = new HashMap<>();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.getAllGenes(idGenesMap);
		for(String s: dao.getVertici()) {
			this.grafo.addVertex(this.idGenesMap.get(s));
		}
		
		for(Adiacenza a: this.dao.getArchi()) {
			Genes g1 = this.idGenesMap.get(a.getId1());
			Genes g2 = this.idGenesMap.get(a.getId2());
			if(a.getCrom1()==a.getCrom2()) {
				double peso = a.getPeso() *2;
				Graphs.addEdgeWithVertices(this.grafo, g1, g2, peso);
			}
			else {
				double peso = a.getPeso();
				Graphs.addEdgeWithVertices(this.grafo, g1, g2, peso);
			}
		}
		
		
		System.out.println("GRAFO CREATO");
		System.out.println("vertici: " + this.grafo.vertexSet().size());
		System.out.println("Archi: " + this.grafo.edgeSet().size());
	}
	
	public List<Genes> getVertici()	{
		List<Genes> vertici = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(vertici);
		return vertici;
	}
	public Set<DefaultWeightedEdge> getArchi()	{
		return this.grafo.edgeSet();
	}

	public List<Vicino> getAdiacenti(Genes g) {
		
		List<Vicino> result = new ArrayList<>();
		for(DefaultWeightedEdge e: this.grafo.edgesOf(g)) {
			Genes vicino = this.grafo.getEdgeTarget(e);
			Vicino v = new Vicino(vicino, this.grafo.getEdgeWeight(e));
			result.add(v);
		}
		
		Collections.sort(result);
		System.out.println("\nLISTA VICINI:\n");
		for(Vicino vv: result)
			System.out.println(vv.getG() + " " + vv.getPeso());
		return result;
	}
	
	
}
