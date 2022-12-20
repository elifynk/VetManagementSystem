package model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;

public class Danisman extends User {
	Connection con= conn.connDB();
	Statement st= null;
	ResultSet rs= null;
	PreparedStatement preparedStatement= null;
	
	public Danisman(int id, String tcno, String name, String parola, String type) {
		super(id, tcno, name, parola, type);
	}
	
	public Danisman() {
		super();
	}
	public Stack<User> getDoctorList() throws SQLException{ //User türünden bir Stack oluşturulur.
		Stack<User> list= new Stack<>();
		User obj;
		try{
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM user WHERE type = 'doktor'"); //User tablosundan doktor türündeki veriler çekilir.
			while(rs.next()){
				obj= new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("parola"),rs.getString("type")); //Veriden gerekli bilgiler alınarak bir nesneye atılır.
				list.push(obj); //bu nesne stack türündeki nesneye gönderilir.
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<User> getRandevuTuruList(int randevuturu_id) throws SQLException{//User türünden bir Arraylist oluşturulur.
		ArrayList<User> list= new ArrayList<>();
		User obj;
		
		try{
			st= con.createStatement();
			rs= st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.parola FROM worker w LEFT JOIN user u ON w.user_id=u.id WHERE randevuturu_id= "+randevuturu_id);
			while(rs.next()){ //worker ve user tablolarından istenen randevuturu_id bilgisine göre veriler çekilir.  
				obj= new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.name"),rs.getString("u.parola"),rs.getString("u.type"));
				list.add(obj);
			}//bu veriler bir objeye aktarılır ve bu obje arrayliste aktarılır.
		}catch(SQLException e){
			e.printStackTrace();
		}
	        return list; 
	    }
		

	public boolean addDoktor(String tcno, String parola, String name) throws SQLException { //doktor ekleme metodu oluşturulur.
		String query ="INSERT INTO user" + "(tcno,parola,name,type) VALUES" + "(?,?,?,?)"; //user tablosuna ekleme yapılması üzere sorgu oluşturulur.
		boolean key=false;
		try { //kullanıcı tipi doktor olacak şekilde veri girişi yapılır.
			st= con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, parola);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
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
	
	public boolean deleteDoktor(int id) throws SQLException { //doktor silme metodu oluşturulur.
		String query ="DELETE FROM user WHERE id= ?"; //user tablosuna silme yapılması üzere sorgu oluşturulur.
		boolean key=false;
		try { //kullanıcı tipi doktor olacak şekilde veri girişi yapılır.
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
	
	public boolean updateDoktor(int id,String tcno, String parola, String name) throws SQLException { //doktor güncelleme metodu oluşturulur.
		String query ="UPDATE user SET name= ?, tcno= ?, parola= ?, WHERE id= ?"; //user tablosuna güncelleme yapılması üzere sorgu oluşturulur.
		boolean key=false;
		try { //gerekli bilgiler alınarak tabloya yerleştirilir.
			st= con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, parola);
			preparedStatement.setInt(4, id);
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
	
	public boolean addWorker(int user_id, int randevuturu_id) throws SQLException {  //çalışan ekleme metodu oluşturulur.
		String query ="INSERT INTO worker" + "(user_id,randevuturu_id) VALUES" + "(?,?)"; //worker tablosuna ekleme yapılması üzere sorgu oluşturulur.
		boolean key=false;
		int sayac=0;
		try {
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM worker WHERE randevuturu_id AND user_id=" +randevuturu_id+user_id); //worker tablosundan seçili randevuturu_id ve user_id seçilir.
			while(rs.next()){ //sorgunun çalışması için sayaç oluşturulur.
				sayac++;
			}
			//if(sayac==0){ //id'ler birbirine eşitse sayaç=0dır ve bilgiler tabloya yazılır.
				preparedStatement= con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, randevuturu_id);
				preparedStatement.executeUpdate();
			//}
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(key)
			return true;
		
		else
		return false; //id'ler uyuşmuyorsa metot kullanılmaz.
	}
}
