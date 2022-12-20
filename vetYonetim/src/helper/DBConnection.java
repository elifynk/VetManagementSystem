package helper;
import java.sql.*;
public class DBConnection {
	Connection c =null;
	
	public DBConnection(){
		
	}
	public Connection connDB(){
		try{
			this.c= DriverManager.getConnection("jdbc:mariadb://localhost:3325/vet?user=root&password=131120");
			return c;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return c;
	}
}
