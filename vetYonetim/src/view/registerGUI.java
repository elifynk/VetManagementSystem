package view;

import helper.Helper;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import model.Hasta;
import model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Color;

public class registerGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_ad;
	private JTextField fld_tcno;
	private JPasswordField fld_sifre;
	private Hasta hasta= new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerGUI frame = new registerGUI();
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
	public registerGUI() {
		setResizable(false);
		setTitle("Veteriner Yonetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(155, 230, 194));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad:");
		lblAdSoyad.setBounds(57, 22, 159, 21);
		w_pane.add(lblAdSoyad);
		
		fld_ad = new JTextField();
		fld_ad.setColumns(10);
		fld_ad.setBounds(57, 43, 159, 20);
		w_pane.add(fld_ad);
		
		JButton btn_kayit = new JButton("Kayit Ol");
		btn_kayit.setBackground(new Color(192, 192, 192));
		btn_kayit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_sifre.getText().length()==0 || fld_ad.getText().length()==0){
					Helper.showMsg("fill");
				}
				else{
					try {
						boolean kontrol= hasta.register(fld_tcno.getText(),fld_sifre.getText(), fld_ad.getText());
						if(kontrol){
							Helper.showMsg("success");
							loginGUI login= new loginGUI();
							login.setVisible(true);
							dispose();
						}
						else{
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btn_kayit.setBounds(57, 194, 159, 28);
		w_pane.add(btn_kayit);
		
		JLabel lblTcNo = new JLabel("T.C. No:");
		lblTcNo.setBounds(57, 74, 159, 21);
		w_pane.add(lblTcNo);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(57, 95, 159, 20);
		w_pane.add(fld_tcno);
		
		JLabel lblSifre = new JLabel("Sifre:");
		lblSifre.setBounds(57, 126, 159, 21);
		w_pane.add(lblSifre);
		
		fld_sifre = new JPasswordField();
		fld_sifre.setBounds(57, 147, 159, 20);
		w_pane.add(fld_sifre);
		
		JButton btn_geri = new JButton("Geri Don");
		btn_geri.setBackground(new Color(192, 192, 192));
		btn_geri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginGUI login= new loginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_geri.setBounds(57, 241, 159, 28);
		w_pane.add(btn_geri);
	}
}
