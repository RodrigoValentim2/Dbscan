package dao;

import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Factory.ConnectionFactory;
import model.Ponto;

public class tdriveDao {
	
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	
	public ArrayList<Ponto> selectClustersByWeekday(int weekday){
		try {
			con = ConnectionFactory.getConnection();
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM clusters WHERE weekday = "+weekday);
			
			ArrayList<Ponto> points = new ArrayList<Ponto>();
			rs.beforeFirst();
			while(rs.next()){
				Ponto p = new Ponto(rs.getInt("id_taxista"), rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getInt("idPonto"));
				p.cluster = rs.getInt("cluster");
				p.weekday = rs.getInt("weekday");
				p.type = rs.getInt("iscore");
				p.studentId = rs.getInt("student_id");
				points.add(p);
			}
			
			stm.close();
			rs.close();
			
			return points;
		} catch (Exception e) {
			
		}
		return null;
	}

}
