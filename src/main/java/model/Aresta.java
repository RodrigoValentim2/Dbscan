package model;

public class Aresta {
	
	public int idEdge;
	public int source;
	public int target;
	public double cost;
	
	public Aresta(int idEdge, int idSource, int idTarget, double cost){
		this.idEdge = idEdge;
		this.source = idSource;
		this.target = idTarget;
		this.cost = cost;
	}

}
