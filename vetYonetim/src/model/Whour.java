package model;

import helper.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class Whour {
	private int id, doktor_id;
	private String doktor_name, wdate, status;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Whour(){
		
	}
	
	public Whour(int id, int doktor_id, String doktor_name, String wdate,
			String status) {
		this.id = id;
		this.doktor_id = doktor_id;
		this.doktor_name = doktor_name;
		this.wdate = wdate;
		this.status = status; 
	}
	
	public LinkedList<Whour> getWhourList(int doktor_id) throws SQLException{ //Whour türünden bir Linkedlist oluşturulur.
		LinkedList<Whour> list= new LinkedList<>();
		Whour obj;
		try{
			Connection con= conn.connDB();
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doktor_id= " + doktor_id); //whour tablosundaki seçili doktor_id'ye göre status='a' olan veriler seçilir.
			while(rs.next()){
				obj= new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoktor_id(rs.getInt("doktor_id"));
				obj.setDoktor_name(rs.getString("doktor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			} //verinin bilgileri alınarak bir objeye aktarılır ve bu obje linkedliste gönderilir.
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	//Bilgilerin başka class'larda da kullanılabilmesi için gerekli metotlar oluşturulur.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoktor_id() {
		return doktor_id;
	}

	public void setDoktor_id(int doktor_id) {
		this.doktor_id = doktor_id;
	}

	public String getDoktor_name() {
		return doktor_name;
	}

	public void setDoktor_name(String doktor_name) {
		this.doktor_name = doktor_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
