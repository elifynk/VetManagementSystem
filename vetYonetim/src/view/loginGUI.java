package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import model.Danisman;
import model.Doktor;
import model.Hasta;
import model.Hayvan;
import helper.*;

public class loginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTC;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPass;
	private DBConnection conn= new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					loginGUI frame = new loginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}        
			}
		});
	}

	
	public loginGUI() {
		setResizable(false);
		setTitle("Vet Klinik Yonetimi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(155, 230, 194));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("vet.png")));
		lbl_logo.setBounds(202,40,103,72);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Veteriner Yonetim Sistemine Hosgeldiniz");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(107, 120, 293, 28);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(new Color(192, 192, 192));
		w_tabpane.setBounds(20, 159, 449, 201);
		w_pane.add(w_tabpane);
		
		JPanel hasta_login = new JPanel();
		hasta_login.setBackground(new Color(62, 206, 137));
		w_tabpane.addTab("Hayvan Sahibi Girisi", null, hasta_login, null);
		hasta_login.setLayout(null);
		
		JLabel lblTcNo = new JLabel("TC Numaraniz:");
		lblTcNo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTcNo.setBounds(10, 25, 102, 28);
		hasta_login.add(lblTcNo);
		
		JLabel lblSifreniz = new JLabel("Sifre:");
		lblSifreniz.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSifreniz.setBounds(10, 72, 83, 28);
		hasta_login.add(lblSifreniz);
		
		fld_hastaTC = new JTextField();
		fld_hastaTC.setBounds(122, 29, 262, 20);
		hasta_login.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		JButton btn_register = new JButton("Kayit Ol");
		btn_register.setBackground(new Color(192, 192, 192));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerGUI rGUI= new registerGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_register.setBounds(38, 126, 152, 36);
		hasta_login.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giris Yap");
		btn_hastaLogin.setBackground(new Color(192, 192, 192));
		btn_hastaLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTC.getText().length()==0 || fld_hastaPass.getText().length()==0){
					Helper.showMsg("fill");
				}
				else{
					boolean key= true;
					try{
						Connection con= conn.connDB();
						Statement st= con.createStatement();
						ResultSet rs= st.executeQuery("SELECT * FROM user");
						while(rs.next()){
							if(fld_hastaTC.getText().equals(rs.getString("tcno"))&& fld_hastaPass.getText().equals(rs.getString("parola"))){
								if(rs.getString("type").equals("hasta")){
									Hasta hasta= new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setParola("parola");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									hastaGUI hGUI= new hastaGUI(hasta);
								    hGUI.setVisible(true);
									dispose();
									key=false;
								}
								
							}
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
					if(key){
						Helper.showMsg("Boyle bir hasta bulunamad�, l�tfen kayit olunuz!");
					}
				}
				
			}
		});
		btn_hastaLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_hastaLogin.setBounds(232, 126, 152, 36);
		hasta_login.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(122, 78, 262, 20);
		hasta_login.add(fld_hastaPass);
		
		JPanel doktor_login = new JPanel();
		doktor_login.setBackground(new Color(62, 206, 137));
		w_tabpane.addTab("Calısan Girisi", null, doktor_login, null);
		doktor_login.setLayout(null);
		
		JLabel label = new JLabel("TC Numaraniz:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		label.setBounds(10, 23, 102, 28);
		doktor_login.add(label);
		
		fld_doktorTc = new JTextField();
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(122, 29, 262, 20);
		doktor_login.add(fld_doktorTc);
		
		JLabel label_1 = new JLabel("Sifre:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		label_1.setBounds(10, 70, 83, 28);
		doktor_login.add(label_1);
		
		JButton btn_doktorLogin = new JButton("Giris Yap");
		btn_doktorLogin.setBackground(new Color(192, 192, 192));
		btn_doktorLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(fld_doktorTc.getText().length()==0 || fld_doktorPass.getText().length()==0){
					Helper.showMsg("fill");
				}
				else{
					try{
						Connection con= conn.connDB();
						Statement st= con.createStatement();
						ResultSet rs= st.executeQuery("SELECT *FROM user");
						while(rs.next()){
							if(fld_doktorTc.getText().equals(rs.getString("tcno"))&& fld_doktorPass.getText().equals(rs.getString("parola"))){
								if(rs.getString("type").equals("danisman")){
									Danisman dman= new Danisman();
									dman.setId(rs.getInt("id"));
									dman.setParola("parola");
									dman.setTcno(rs.getString("tcno"));
									dman.setName(rs.getString("name"));
									dman.setType(rs.getString("type"));
									danismanGUI dGUI= new danismanGUI(dman);
									dGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")){
									Doktor doktor= new Doktor();
									doktor.setId(rs.getInt("id"));
									doktor.setParola("parola");
									doktor.setTcno(rs.getString("tcno"));
									doktor.setName(rs.getString("name"));
									doktor.setType(rs.getString("type"));
									doktorGUI dokGUI= new doktorGUI(doktor);
									dokGUI.setVisible(true);
									dispose();
								}
							}
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_doktorLogin.setBounds(79, 126, 305, 36);
		doktor_login.add(btn_doktorLogin);
		
		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(122, 76, 262, 20);
		doktor_login.add(fld_doktorPass);
	}
}
