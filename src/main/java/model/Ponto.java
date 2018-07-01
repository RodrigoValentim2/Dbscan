package model;

public class Ponto {
	
	public static int CORE = 1;
	public static int BORDER = 0;
	public static int OUTLIER = -1;
	
	public int idPonto;
	public int studentId;
	public int idTaxiDriver;
	public double longitude;
	public double latitude;
	public double idVertice;
	public double longitudeVertice;
	public double latitudeVertice;
	public int cluster;
	public boolean visited = false;
	public int type;
	public int weekday;
	
	public int idVertece;

	public Ponto(int idTaxiDriver, double longitude, double latitude, int idPonto){
		this.idTaxiDriver = idTaxiDriver;
		this.longitude = longitude;
		this.latitude = latitude;
		this.idPonto = idPonto;
	}
	public Ponto(int idTaxiDriver, double longitude, double latitude){
		this.idTaxiDriver = idTaxiDriver;
		this.longitude = longitude;
		this.latitude = latitude;
		
	}

}
