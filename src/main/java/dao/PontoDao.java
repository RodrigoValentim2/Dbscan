package dao;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Factory.ConnectionFactory;
import model.Ponto;

public class PontoDao {
	
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	public int LENGTH = 0;
	
	public List<Ponto> PontoEntreIntervalo(String hourBegin, String hourEnd, String data){
		try {
			con = ConnectionFactory.getConnection();
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM tdrive WHERE date_time BETWEEN '"+data+" "+hourBegin+"' AND '"+data+" "+hourEnd+"'");
			
			List<Ponto> pontos = new ArrayList<Ponto>();
			rs.beforeFirst();
			while(rs.next()){
				Ponto p = new Ponto(rs.getInt("taxi_id"), rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getInt("id_ponto"));
				p.studentId = 369584;
				pontos.add(p);
				LENGTH++;
			}
			
			stm.close();
			rs.close();
			
			return pontos;
		} catch (Exception e) {
			System.out.println("[ERROR]: "+e.toString());
		}
		return null;
	}

}
