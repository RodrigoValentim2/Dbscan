package Factory;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static Connection connection; 
	
	public static Connection getConnection(){
		try{
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/db_taxi_drive", "postgres", "root");
			return connection;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		ConnectionFactory.getConnection();

	}

}
