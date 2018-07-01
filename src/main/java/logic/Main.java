package logic;

import java.util.ArrayList;
import java.util.List;

import dao.PontoDao;
import dao.VerticeDao;
import model.Aresta;
import model.Ponto;
import model.Vertice;

public class Main {
		
	
	public static void main(String[] args) {
		MapMatching m = new MapMatching();
		Dbscan c = new Dbscan();
		VerticeDao dao = new VerticeDao();
		PontoDao pdao = new PontoDao();
		
		System.out.println("Consultando os dados...");
		ArrayList<Ponto> pontos = (ArrayList<Ponto>) pdao.PontoEntreIntervalo("17:00:00", "17:30:00", "2008-02-05");
		System.out.println("tamanho da lista"+ pontos.size());
		System.out.println("Mapeando...");
	   	m.map(pontos);
		System.out.println("Aplicando db...");
		c.dbscan(pontos,0.004, 60 );
		c.CriarrCSV("resultado", pontos);
		
		//teste 1
		//data: 2008-02-04
		//Numero de clusters: 990
		//minPoint 30
		
		
		
		//teste 2
		//2008-02-04
		//clusters 255
		// min60
		//teste2();
		
		//teste 3
		//2008-02-05
		//clusters 179
		// min60
		//teste2();

	}

}
