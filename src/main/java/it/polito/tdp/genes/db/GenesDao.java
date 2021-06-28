package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getAllGenes(Map<String, Genes> map){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!map.containsKey(res.getString("GeneID"))) {
					Genes genes = new Genes(res.getString("GeneID"), 
							res.getString("Essential"), 
							res.getInt("Chromosome"));
					map.put(genes.getGeneId(), genes);
					}
				
			}
			res.close();
			st.close();
			conn.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
	}
	
	
	public List<Adiacenza> getArchi()	{
		String sql = "SELECT DISTINCT i.GeneID1 AS id1, i.GeneID2 AS id2, abs(i.Expression_Corr) AS peso, g1.Chromosome AS c1, g2.Chromosome AS c2 "
				+ "FROM genes g1, genes g2, interactions i, classification c1, classification c2 "
				+ "WHERE g1.Essential = \"Essential\" "
				+ "AND g2.Essential = \"Essential\" "
				+ "AND i.GeneID1 != i.GeneID2 "
				+ "AND i.GeneID1 = c1.GeneID "
				+ "AND c1.GeneID = g1.GeneID "
				+ "AND i.GeneID2 = c2.GeneID "
				+ "AND c2.GeneID = g2.GeneID";
		
		List<Adiacenza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Adiacenza a = new Adiacenza(res.getString("id1"), res.getString("id2"), res.getDouble("peso"),
						res.getInt("c1"), res.getInt("c2"));
				result.add(a);
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public List<String> getVertici(){
		String sql = "SELECT DISTINCT GeneID "
				+ "FROM genes "
				+ "WHERE genes.Essential = \"Essential\"";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("GeneID"));
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
