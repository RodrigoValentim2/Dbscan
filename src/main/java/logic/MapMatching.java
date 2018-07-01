package logic;

import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dao.VerticeDao;
import model.Ponto;
import model.Vertice;

public class MapMatching {

	private List<Vertice> verteces;

	public ArrayList<Ponto> map(ArrayList<Ponto> clusters){
		
		// pego todos os vertices do banco	
		VerticeDao vDao = new VerticeDao();
		verteces =  vDao.buscaTodosVertices();
		for(Ponto p: clusters){
			Vertice v = findVertice(p);
			p.idVertece = v.id;
			p.latitudeVertice = v.latitude;
			p.longitudeVertice  = v.longitude;
			
		}
		return clusters;
	}
	private Vertice findVertice(Ponto p){
		Vertice v = verteces.get(0);
		for (Vertice vertice : verteces) {
			if(euclideanDistance(p, new Ponto(0, vertice.longitude, vertice.latitude)) < euclideanDistance(p, new Ponto(0, v.longitude, v.latitude)))
				v = vertice;
		}
		return v;
	}
	 
	private double euclideanDistance(Ponto source, Ponto target){
		return Math.sqrt(Math.pow((source.longitude - target.longitude),2)+ Math.pow((source.latitude - target.latitude),2));
	}
	
	public void exportarCSV(String fileName, List<Ponto> list){
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			
			writer.println("student_id;id_taxista;longitude;latitude;id_vertice");
			System.out.println("Exportando arquivo com Map Matching...");
			for (Ponto p : list) {
				writer.println(p.studentId+";"+p.idTaxiDriver+";"+p.longitudeVertice+";"+p.latitude+";"+p.idVertece);
			}	//                                             p
			System.out.println("Arquivo Exportado com Sucesso!");
			
			writer.close();
		}catch (Exception e) {
			System.out.println("[ERROR]: "+e.toString());
		}
	}
	public void CriarrCSV(String fileName, List<Ponto> list){
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println("student_id;taxi_id;weekday;longitude;latitude;cluster;iscore;id_vertice");
			for (Ponto p : list) {
				writer.println(p.studentId+";"+p.idTaxiDriver+";"+p.weekday+";"+p.longitude+";"+p.latitude+";"+p.cluster+";"+p.type+";"+p.idVertece);
			}
			System.out.println("Arquivo criado com sucesso!");
			
			writer.close();
		}catch (Exception e) {
			System.out.println("[ERROR]: "+e.toString());
		}
	}
}
	

