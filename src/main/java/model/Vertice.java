package model;

public class Vertice implements Comparable<Vertice> {
	public int id;
	public double  latitude;
	public double longitude;
	public double estimate;
	public Vertice ancestor;
	public boolean open = true;
	
public Vertice(){}
	
	public Vertice(int id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	//compararção
	public int compareTo(Vertice o) {
		double result = this.estimate - o.estimate;
		if(this.estimate - o.estimate < 0)
			return -1;
		else if(this.estimate - o.estimate > 0)
			return 1;
		else 
			return 0;

	}
	
	
}
