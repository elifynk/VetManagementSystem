package model;

import helper.DBConnection;

public class User {
	private int id;
	String tcno,name,parola,type;
	DBConnection conn= new DBConnection();
	public User(int id, String tcno, String name, String parola, String type) { // Burasi kullanici girisinde kullanacagimiz kullanicinin ozelliklerini iceren class'imiz.s
		this.id = id;															// Program buradan nesneler olusturarak ilerliyor kullanici eklerken.
		this.tcno = tcno;
		this.name = name;
		this.parola = parola;
		this.type = type;
	}
	public User(){
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcno() { 
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}