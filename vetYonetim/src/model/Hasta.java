package model;

import helper.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hasta extends User{
	Connection con= conn.connDB();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement= null;
	
	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String parola, String type) {
		super(id, tcno, name, parola, type);
	} // Hasta nin sahip olacagi degiskenler verilmis tanimlarken
	
	public boolean register(String tcno, String parola, String name) throws SQLException {
		int key=0;
		boolean tekrar= false;
		String query= "INSERT INTO user" + "(tcno, parola, name, type) VALUES" + "(?,?,?,?)";
		try {
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");
			
			while(rs.next()){  				// Eger kayitli kullanici yoksa bunu bildiren kod
				tekrar= true;
				Helper.showMsg("Bu TC No'ya ait bir kayit bulunmaktadir!");
				break;
			}
			if(!tekrar){									// eger boyle bir kulanici varsa bunlar alinir
				try {
					preparedStatement= con.prepareStatement(query);
					preparedStatement.setString(1,tcno);
					preparedStatement.setString(2,parola);
					preparedStatement.setString(3,name);
					preparedStatement.setString(4,"hasta");
					preparedStatement.executeUpdate();
					key=1;
				} catch (Exception e) {
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key==1)
			return true;
		else
			return false;
	}
	
	public boolean addRandevu(int doktor_id, int hasta_id, String doktor_name, String hasta_name, String randevuDate) throws SQLException { // randevu ekleme kod blogu
		int key=0;
		String query= "INSERT INTO randevu" + "(doktor_id, doktor_name, hasta_id, hasta_name, randevu_date) VALUES" + "(?,?,?,?,?)";
		try {
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setInt(1,doktor_id);
			preparedStatement.setString(2,doktor_name);
			preparedStatement.setInt(3,hasta_id);
			preparedStatement.setString(4,hasta_name);
			preparedStatement.setString(5,randevuDate);
			preparedStatement.executeUpdate();
			key=1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key==1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatus(int doktor_id, String wdate) throws SQLException { // Calisma saati updateleme kod blogu
		int key=0;
		String query= "UPDATE whour SET status = ? WHERE doktor_id = ? AND wdate = ?";
		try {
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1,"p");
			preparedStatement.setInt(2,doktor_id);
			preparedStatement.setString(3,wdate);
			preparedStatement.executeUpdate();
			key=1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key==1)
			return true;
		else
			return false;
	}
}