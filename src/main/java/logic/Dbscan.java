package logic;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.VerticeDao;
import model.Aresta;
import model.Ponto;
import model.Vertice;

public class Dbscan{
	private List<Ponto> pontos = null;
	private Dijkstra dijkstra;
	private HashMap<Integer, Ponto> map = new HashMap<Integer, Ponto>();
	private int c ;
	private VerticeDao vdao;
	
	private List<Vertice> verteces;
	private List<Aresta> arestas;
	private HashMap<Integer, Double> distances; 
	

		
	
	
	public Dbscan(){
		VerticeDao vdao = new VerticeDao();
		verteces = vdao.buscaTodosVertices();
	    arestas = vdao.buscarTodasArestas();
	 	dijkstra = new Dijkstra();
		//dijkstra = new Dijkstra( verteces, arestas);
	
		
	
	}
	
	public void dbscan(ArrayList<Ponto> pontos, double eps, int minPontos){
		c = 0;
		this.pontos = pontos;
		for(Ponto p: pontos){
			if(p.visited == false){
					p.visited = true;	
			
				ArrayList<Ponto> vizinhosP = (ArrayList<Ponto>) vizinhoDoPonto(p, eps);
				int count = vizinhosP.size() ;			
					//verifico se ele é outlier
				if(count < minPontos){
					p.cluster = Ponto.OUTLIER;
					p.type = Ponto.OUTLIER;
				//se ele não for então ele é um core
				}else{	
						c++;
						System.out.println("Numero de clusters: "+ c);
						p.cluster = c;
						p.type = p.CORE;
						expandCluster(vizinhosP, c, minPontos, eps);
			
				}		
			 }
		 }	
		System.out.println("Numero de clusters: "+ c);
			

	}
	private void expandCluster(ArrayList<Ponto> vizinhosP, int c, int minPontos, double eps){
		
		for (Ponto p : vizinhosP) {
			p.cluster = c;
		}
		while(vizinhosP.size() > 0){
			Ponto p = vizinhosP.get(0);
			vizinhosP.remove(0);
			if(p.visited == false){
				p.visited = true;
				ArrayList<Ponto> novosVizinhos = (ArrayList<Ponto>) vizinhoDoPonto(p, eps);
				int count = novosVizinhos.size() ;	
			//	System.out.println("count: "+ count);
				for (Ponto ponto : novosVizinhos) {
					ponto.cluster = c;
				}
				
				if(count >= minPontos){
					p.type = Ponto.CORE;
					vizinhosP.addAll(novosVizinhos);
				}else{
					p.type = Ponto.BORDER;
				}	
			}	
		}
	}
	
	
	
	public List<Ponto>  vizinhoDoPonto(Ponto p, double eps){
		List<Ponto> vizinhosP = new ArrayList<Ponto>();
		for(Ponto ponto: pontos){
			double dEuclidiana = this.euclideanDistance(p, ponto);
			if(dEuclidiana <= eps ){
				double d = this.dijkstra.dijkstra(p.idVertece, ponto.idVertece);
				if(d <= eps){
					if(!(map.containsKey(ponto.idPonto))){
						map.put(ponto.idPonto, ponto);
						vizinhosP.add(ponto);
						//System.out.println("Tamanho dos vizinhos: "+ vizinhosP.size());
					}
				 }
		     }
		}
			//quando eu retornava apenas o tamanho da lista vizinhos o o algoritimo não conseguia terminar
			//
			//ArrayList<Ponto> distintos = new ArrayList<Ponto>();
			//for (Ponto pont : vizinhosP) {
			//	if(!contains(distintos, pont)){
				//	distintos.add(pont);
				//}
			//}
		//System.out.println("número de vizinhos: "+ vizinhosP.size());
			return vizinhosP;

	
	}
	public void CriarrCSV(String fileName, List<Ponto> list){
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println("student_id;taxi_id;weekday;longitude;latitude;cluster;iscore;id_vertice");
			for (Ponto p : list) {
				writer.println(p.studentId+";"+p.idTaxiDriver+";"+p.weekday+";"+p.longitudeVertice+";"+p.latitudeVertice+";"+p.cluster+";"+p.type+";"+p.idVertece);
			}
			System.out.println("Arquivo cridado com sucesso!");
			
			writer.close();
		}catch (Exception e) {
			System.out.println("[ERROR]: "+e.toString());
		}
	}
	
	private boolean contains(ArrayList<Ponto> pontos, Ponto p){
		for (Ponto pont : pontos) {
			if(pont.idTaxiDriver == p.idTaxiDriver)
				return true;
		}
		return false;
	}
	private double euclideanDistance(Ponto source, Ponto target){
		return Math.sqrt(Math.pow((source.longitude - target.longitude),2)+ Math.pow((source.latitude - target.latitude),2));
	}

}
