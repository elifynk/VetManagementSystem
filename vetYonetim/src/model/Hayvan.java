package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;
import view.hastaGUI;

public class Hayvan {
	

	private int hayvan_id;
	String sahip_name,hayvan_name,hayvan_type,hayvan_age,hayvan_gender,chip_id;
	
	DBConnection conn= new DBConnection();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement= null;
	public Hayvan() {
		
	}
	public Hayvan(int hayvan_id, String sahip_name, String hayvan_name, String hayvan_type, String hayvan_age, String hayvan_gender, String chip_id) {
		this.hayvan_id = hayvan_id;
		this.sahip_name = sahip_name;
		this.hayvan_name = hayvan_name;
		this.hayvan_type = hayvan_type;
		this.hayvan_age = hayvan_age;
		this.hayvan_gender = hayvan_gender;
		this.chip_id = chip_id;
		
	}
	
	
	public ArrayList<Hayvan> getHayvanList(String name) throws SQLException { //Randevu türünden bir arraylist oluşturulur.
		ArrayList<Hayvan> list = new ArrayList<>();
		Hayvan obj;
		Connection con= conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM hayvan WHERE sahip_name='"+name+"'"); //randevu veri tablosundan seçili hasta id'sine ait veriler çekilir.
			while (rs.next()) {
				obj = new Hayvan();
				obj.setHayvan_id(rs.getInt("hayvan_id"));
				obj.setSahip_name(rs.getString("sahip_name"));
				obj.setHayvan_name(rs.getString("hayvan_name"));
				obj.setHayvan_type(rs.getString("hayvan_type"));
				obj.setHayvan_age(rs.getString("hayvan_age"));
				obj.setHayvan_gender(rs.getString("hayvan_gender"));
				obj.setChip_id(rs.getString("chip_id"));
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
	public int getHayvan_id() {
		return hayvan_id;
	}
	public void setHayvan_id(int hayvan_id) {
		this.hayvan_id = hayvan_id;
	}
	public String getSahip_name() {
		return sahip_name;
	}
	public void setSahip_name(String sahip_name) {
		this.sahip_name = sahip_name;
	}
	public String getHayvan_name() {
		return hayvan_name;
	}
	public void setHayvan_name(String hayvan_name) {
		this.hayvan_name = hayvan_name;
	}
	public String getHayvan_type() {
		return hayvan_type;
	}
	public void setHayvan_type(String hayvan_type) {
		this.hayvan_type = hayvan_type;
	}
	public String getHayvan_age() {
		return hayvan_age;
	}
	public void setHayvan_age(String hayvan_age) {
		this.hayvan_age = hayvan_age;
	}
	public String getHayvan_gender() {
		return hayvan_gender;
	}
	public void setHayvan_gender(String hayvan_gender) {
		this.hayvan_gender = hayvan_gender;
	}
	public String getChip_id() {
		return chip_id;
	}
	public void setChip_id(String chip_id) {
		this.chip_id = chip_id;
	}
	public DBConnection getConn() {
		return conn;
	}
	public void setConn(DBConnection conn) {
		this.conn = conn;
	}
	
	
	
	
	
	
}
