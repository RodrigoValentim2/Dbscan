package logic;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


import dao.VerticeDao;
import model.Aresta;
import model.Vertice;

public class Dijkstra {
	
	private VerticeDao vdao;
	private double INFINITY = Double.MAX_VALUE;
	private ArrayList<Vertice> vertices;
	private ArrayList<Aresta> arestas;
	private HashMap<Integer, Double> distances;

	public Dijkstra(){
		vdao = new VerticeDao();
		vertices = vdao.buscaTodosVertices();
		arestas = vdao.buscarTodasArestas();
		System.out.println("Tamanho de todos os vertices:  "+ vertices.size());
		System.out.println("tamanho de todas as arestas: "+ arestas.size());
	}
	public Dijkstra(ArrayList<Vertice> vertices, ArrayList<Aresta> arestas) { 
	    this.vertices = vertices;
		this.arestas = arestas;
		this.distances = new HashMap<Integer, Double>();
	}
	
	public double dijkstra(int idSouce, int idTarget){
		
		distances = new HashMap<Integer, Double>();
		for (Vertice vertex : vertices) {
			distances.put(vertex.id, INFINITY);
		}
		
		PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>();
		
		
		
		Vertice ver = findVertex(idSouce);
		ver.id = idSouce;
		//System.out.println("idSouce: "+ idSouce);
		ver.estimate = 0.0;
		distances.put(ver.id, 0.0);
		queue.add(ver);
		
		while (!queue.isEmpty()) {
			Vertice u = queue.poll();
			//System.out.println("queue "+ queue.size());
			if(u.open){
				u.open = false;
				//System.out.println("teste");
				double uest = distances.get(u.id);
				for(Aresta e: findVertices(u.id)){
					Vertice v = findVertex(e.target);
					double vest = distances.get(v.id);
					if (vest > (uest + e.cost)) {
						v.estimate = (e.cost+uest);
						distances.put(v.id, (uest + e.cost));
						queue.add(v);
					}
				}
			}
			
		}
		
		return distances.get(idTarget);
	}
	
	public Vertice findVertex(int id){
		for (Vertice vertice : vertices) {
			if(vertice.id == id)
				return vertice;
		}
		return null;
	}
	
	private ArrayList<Aresta> findVertices(int idVertex){
		ArrayList<Aresta> egs = new ArrayList<Aresta>();
		for (Aresta aresta : arestas) {
			if(aresta.source == idVertex)
				egs.add(aresta);
		}
		return egs;
	}
	
	
}
