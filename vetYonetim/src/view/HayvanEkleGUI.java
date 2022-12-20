package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.DBConnection;
import helper.Helper;
import model.Hasta;
import model.Hayvan;
import view.hastaGUI;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class HayvanEkleGUI extends JFrame {

	/**
	 * Launch the application.
	 */
	private JPanel contentPane;
	private JTextField sahiptxt;
	private JTextField adtxt;
	private JTextField turtxt;
	private JTextField gendertxt;
	private JTextField agetxt;
	private JTextField chiptxt;
	public static Hasta hasta;
	
	DBConnection conn= new DBConnection();
	Connection con= conn.connDB();
	Statement st= null;
	ResultSet rs= null;
	String s_ad,h_ad,h_age,h_gender,chip_id,h_tur;
	PreparedStatement preparedStatement= null;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HayvanEkleGUI frame = new HayvanEkleGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public HayvanEkleGUI(Hasta hasta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 100, 430, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(155, 230, 194));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSahibininAdSoyad = new JLabel("Sahibinin Adı Soyadı:");
		lblSahibininAdSoyad.setBounds(10, 34, 159, 21);
		contentPane.add(lblSahibininAdSoyad);
		
		JLabel lblHayvannAd = new JLabel("Hayvanın Adı:");
		lblHayvannAd.setBounds(10, 66, 159, 21);
		contentPane.add(lblHayvannAd);
		
		JLabel lblHayvannTr = new JLabel("Hayvanın Türü:");
		lblHayvannTr.setBounds(10, 98, 159, 21);
		contentPane.add(lblHayvannTr);
		
		JLabel lblHayvannCinsiyeti = new JLabel("Hayvanın Cinsiyeti:");
		lblHayvannCinsiyeti.setBounds(10, 130, 159, 21);
		contentPane.add(lblHayvannCinsiyeti);
		
		JLabel lblHayvannYa = new JLabel("Hayvanın Yaşı:");
		lblHayvannYa.setBounds(10, 162, 159, 21);
		contentPane.add(lblHayvannYa);
		
		JLabel lblHayvannChipId = new JLabel("Hayvanın Chip Id:");
		lblHayvannChipId.setBounds(10, 194, 159, 21);
		contentPane.add(lblHayvannChipId);
		
		sahiptxt = new JTextField(hasta.getName());
		sahiptxt.setEditable(false);
		sahiptxt.setColumns(10);
		sahiptxt.setBounds(179, 34, 159, 20);
		contentPane.add(sahiptxt);
		
		adtxt = new JTextField();
		adtxt.setColumns(10);
		adtxt.setBounds(179, 66, 159, 20);
		contentPane.add(adtxt);
		
		turtxt = new JTextField();
		turtxt.setColumns(10);
		turtxt.setBounds(179, 98, 159, 20);
		contentPane.add(turtxt);
		
		gendertxt = new JTextField();
		gendertxt.setColumns(10);
		gendertxt.setBounds(179, 130, 159, 20);
		contentPane.add(gendertxt);
		
		agetxt = new JTextField();
		agetxt.setColumns(10);
		agetxt.setBounds(179, 162, 159, 20);
		contentPane.add(agetxt);
		
		chiptxt = new JTextField();
		chiptxt.setColumns(10);
		chiptxt.setBounds(179, 194, 159, 20);
		contentPane.add(chiptxt);
		
		JButton btn_kayit = new JButton("Kaydet");
		btn_kayit.setBackground(new Color(192, 192, 192));
		btn_kayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							s_ad= sahiptxt.getText();
							h_ad=  adtxt.getText();
							h_age=  agetxt.getText();
						    h_gender= gendertxt.getText();
						    h_tur = turtxt.getText();
						    chip_id = chiptxt.getText();
							if(sahiptxt.getText().length()==0 || adtxt.getText().length()==0 || turtxt.getText().length()==0  || agetxt.getText().length()==0
						 || gendertxt.getText().length()==0  || chiptxt.getText().length()==0	){
					Helper.showMsg("fill");
				}
				else {
					String query= "INSERT INTO hayvan" + "(sahip_name, hayvan_name, hayvan_type, hayvan_age, hayvan_gender, chip_id) VALUES" + "(?,?,?,?,?,?)";
					try {
						st= con.createStatement();
						
						
							try {
								preparedStatement= con.prepareStatement(query);
								preparedStatement.setString(1,s_ad);
								preparedStatement.setString(2,h_ad);
								preparedStatement.setString(3,h_tur);
								preparedStatement.setString(4,h_age);
								preparedStatement.setString(5,h_gender);
								preparedStatement.setString(6,chip_id);
								preparedStatement.executeUpdate();
								Helper.showMsg("success");
							} catch (Exception e1) {
								Helper.showMsg("error");
						
						}
						
					} catch (SQLException e2) {
						e2.printStackTrace();
					
				}
				}
			}
		});
		btn_kayit.setBounds(110, 226, 159, 28);
		contentPane.add(btn_kayit);
		
		JButton btn_geri = new JButton("Geri Don");
		btn_geri.setBackground(new Color(192, 192, 192));
		btn_geri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hastaGUI hgui = null;
				try {
					hgui = new hastaGUI(hasta);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hgui.setVisible(true);
				dispose();
			}
		});
		btn_geri.setBounds(110, 265, 159, 28);
		contentPane.add(btn_geri);
		

	
		
		
	}
}
