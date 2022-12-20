package model;

import helper.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

public class Randevu {
	private int id, doktorID, hastaID;
	private String doktorName, hastaName, randevuDate;
	 
	DBConnection conn = new DBConnection();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement= null;
	
	public Randevu() {

	}
	
	public Randevu(int id, int doktorID, int hastaID, String doktorName,
			String hastaName, String randevuDate) { 
		super();
		this.id = id;
		this.doktorID = doktorID;
		this.hastaID = hastaID;
		this.doktorName = doktorName;
		this.hastaName = hastaName;
		this.randevuDate = randevuDate;
	}
	
	public ArrayList<Randevu> getHastaList(int hasta_id) throws SQLException { //Randevu türünden bir arraylist oluşturulur.
		ArrayList<Randevu> list = new ArrayList<>();
		Randevu obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM randevu WHERE hasta_id= " +hasta_id); //randevu veri tablosundan seçili hasta id'sine ait veriler çekilir.
			while (rs.next()) {
				obj = new Randevu();
				obj.setId(rs.getInt("id"));
				obj.setDoktorID(rs.getInt("doktor_id"));
				obj.setDoktorName(rs.getString("doktor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setRandevuDate(rs.getString("randevu_date"));
				list.add(obj);
			} //tabloda yazması gereken bilgiler alınarak randevu türünden bir objeye aktarılır.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	
	public ArrayList<Randevu> getDoktorList(int doktor_id) throws SQLException { //Randevu türünden bir arraylist oluşturulur.
		ArrayList<Randevu> list = new ArrayList<>();
		Randevu obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM randevu WHERE doktor_id= " +doktor_id); //randevu veri tablosundan seçili doktor id'sine ait veriler çekilir.
			while (rs.next()) {
				obj = new Randevu();
				obj.setId(rs.getInt("id"));
				obj.setDoktorID(rs.getInt("doktor_id"));
				obj.setDoktorName(rs.getString("doktor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setRandevuDate(rs.getString("randevu_date"));
				list.add(obj);
			} //tabloda yazması gereken bilgiler alınarak randevu türünden bir objeye aktarılır.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
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
	public int getDoktorID() {
		return doktorID;
	}
	public void setDoktorID(int doktorID) {
		this.doktorID = doktorID;
	}
	public int getHastaID() {
		return hastaID;
	}
	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}
	public String getDoktorName() {
		return doktorName;
	}
	public void setDoktorName(String doktorName) {
		this.doktorName = doktorName;
	}
	public String getHastaName() {
		return hastaName;
	}
	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}
	public String getRandevuDate() {
		return randevuDate;
	}
	public void setRandevuDate(String randevuDate) {
		this.randevuDate = randevuDate;
	}
	
	
}
