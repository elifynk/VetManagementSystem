package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import view.doktorGUI;



public class Doktor extends User {
	Connection con= conn.connDB();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement= null;
	
	public Doktor() {
		super();
	}
	public Doktor(int id, String tcno, String name, String parola, String type) {
		super(id, tcno, name, parola, type);
	}
	int key=0;
	int sayac=0;
	public boolean addWhour(int doktor_id, String doktor_name, String wdate) throws SQLException {  // saat ekleme kod blogu
	
		
		try {
			String query= "INSERT INTO whour" + "(doktor_id,doktor_name,wdate) VALUES" + "(?,?,?)";
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM whour WHERE status ='a' AND doktor_id= " + doktor_id);
			
			while(rs.next()){
				sayac++;
				break;
			}
			
			
				try {
					preparedStatement= con.prepareStatement(query);
					preparedStatement.setInt(1,doktor_id);
					preparedStatement.setString(2,doktor_name);
					preparedStatement.setString(3,wdate);
					preparedStatement.executeUpdate();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
	
			key=1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key==1)
			{
			key=0;
			return true;
			
			}
		
		else {
			key=0;
			return false;
		}
			
	}
	
	public LinkedList<Whour> getWhourList(int doktor_id) throws SQLException{ // Linked list kullanarak calisma saatlerini ekrana yazdiran kod.
		LinkedList<Whour> list= new LinkedList<>();
		Whour obj; 
		try{
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doktor_id= " + doktor_id);
			while(rs.next()){
				obj= new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoktor_id(rs.getInt("doktor_id"));
				obj.setDoktor_name(rs.getString("doktor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean deleteWhour(int id) throws SQLException {   // Calisma saati silen kod blogu
		String query ="DELETE FROM whour WHERE id= ?";
		boolean key=false;
		try {
			st= con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(key)
			return true;
		
		else
		return false;
	}
}