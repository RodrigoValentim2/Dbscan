package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Factory.ConnectionFactory;
import model.Aresta;
import model.Vertice;

public class VerticeDao {

private final Connection connection;
	
	public VerticeDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public ArrayList<Vertice> buscaTodosVertices(){
		String sql = "SELECT * FROM table_vertices";
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Vertice v = new Vertice(rs.getInt("id_vertice"), rs.getDouble("longitude_vertice"), rs.getDouble("latitude_vertice"));
				vertices.add(v);
			}
			
			rs.close();
			st.close();
			
			return vertices;
		} catch (Exception e) {
			System.out.println("[ERROR]: " + e.toString());
		}
		return null;
	}
	
	public ArrayList<Aresta> buscarTodasArestas(){
		String sql = "SELECT * FROM table_roads";
		
		try {
			ArrayList<Aresta> arestas = new ArrayList<Aresta>();
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Aresta a = new Aresta(rs.getInt("id_road"), rs.getInt("idInicio"), rs.getInt("idDestino"), rs.getDouble("cost"));
				arestas.add(a);
			}
			
			rs.close();
			st.close();
			
			return arestas;
		} catch (Exception e) {
			System.out.println("[ERROR]: " + e.toString());
		}
		return null;
	}
}
	
	

