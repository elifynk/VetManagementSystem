package model;

import helper.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RandevuTuru {
	private int id;
	private String name;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public RandevuTuru() { 

	}

	public RandevuTuru(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<RandevuTuru> getList() throws SQLException { //RandevuTuru türünden bir Arraylist oluşturulur.
		ArrayList<RandevuTuru> list = new ArrayList<>();
		RandevuTuru obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM randevuturu"); //randevuturu tablosundaki tüm veriler seçilir.
			while (rs.next()) {
				obj = new RandevuTuru();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			} //id ve name bilgileri alınarak bir objeye aktarılır ve bu obje Arrayliste gönderilir.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
	
	public RandevuTuru getFetch(int id){
		Connection con = conn.connDB();
		RandevuTuru c = new RandevuTuru();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM randevuturu WHERE id=" +id); //seçilen id'ye göre randevuturu tablosundaki veriler seçilir.
			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			} //id ve name bilgileri alınır ve döngüden çıkılır.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return c;
	}
	
	public boolean addRandevuTuru(String name) throws SQLException { 
		String query ="INSERT INTO randevuturu" + "(name) VALUES" + "(?)"; //name bilgisine göre randevuturu tablosuna veri girişi yapılması üzere sorgu oluşturulur.
		boolean key=false;
		Connection con = conn.connDB();
		try {
			st= con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		} //verinin name bilgisi alınır ve tabloya eklenir.
		if(key)
			return true;
		
		else
		return false;
	}
	public boolean deleteRandevuTuru(int id) throws SQLException {
		String query ="DELETE FROM randevuturu WHERE id= ?"; //seçilen id'ye göre randevuturu tablosundaki veriyi silmek üzere sorgu oluşturulur.
		boolean key=false;
		Connection con = conn.connDB();
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
	public boolean updateRandevuTuru(int id, String name) throws SQLException {
		String query ="UPDATE randevuturu SET name= ? WHERE id= ?"; //seçilen id'ye göre randevuturu tablosundaki verinin adını güncellemek üzere sorgu oluşturulur.
		boolean key=false;
		Connection con = conn.connDB();
		try {
			st= con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
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
	
	public ArrayList<User> getRandevuTuruList(int randevuturu_id) throws SQLException{ //User türünden bir Arraylist oluşturulur.
		ArrayList<User> list= new ArrayList<>();
		User obj;
		try{ //user ve worker tablolarından eşleşen seçili randevuturu_id bilgisine göre veri seçimi yapılması üzere sorgu oluşturulur.
			Connection con = conn.connDB();
			st= con.createStatement();
			rs= st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.parola FROM worker w LEFT JOIN user u ON w.user_id=u.id WHERE randevuturu_id= "+randevuturu_id);
			while(rs.next()){
				obj= new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.name"),rs.getString("u.parola"),rs.getString("u.type"));
				list.add(obj);
			} //alınan bilgiler bir objeye aktarılır ve bu obje arrayliste gönderilir.
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	//RandevuTuru classındaki verilerin bilgileri başka classlarda kullanılabilmesi için gerekli metotlar oluşturulur.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
