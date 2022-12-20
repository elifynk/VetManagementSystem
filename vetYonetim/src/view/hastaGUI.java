package view;

import helper.DBConnection;
import helper.Helper;
import helper.Item;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Hasta;
import model.Hayvan;
import model.Randevu;

import model.RandevuTuru;
import model.Whour;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class hastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta= new Hasta();
	private RandevuTuru randevuTuru= new RandevuTuru();
	private JTable table_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData= null;
	private JTable table_whour;
	private Whour whour= new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData= null;
	private int selectDoktorID=0;
	private String selectDoktorName=null;
	private JTable table_randevularim;
	private JTable table_hayvanlarim;
	private DefaultTableModel randevuModel;
	private DefaultTableModel hayvanModel;
	private Object[] randevuData= null;
	private Object[] hayvanData= null;
	private Randevu randevu= new Randevu();
	private Hayvan hayvan= new Hayvan();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaGUI frame = new hastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public hastaGUI() {
		
	}

	public hastaGUI(Hasta hasta) throws SQLException {
		
		doktorModel = new DefaultTableModel();
		Object[] colDoktor = new Object[2];
		colDoktor[0] = "ID";
		colDoktor[1] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktor);
		doktorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "TARIH";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		randevuModel = new DefaultTableModel();
		Object[] colrandevu = new Object[3];
		colrandevu[0] = "ID";
		colrandevu[1] = "DOKTOR";
		colrandevu[2] = "TARIH";
		randevuModel.setColumnIdentifiers(colrandevu);
		randevuData = new Object[3];
		for(int i=0; i< randevu.getHastaList(hasta.getId()).size(); i++){
			randevuData[0]= randevu.getHastaList(hasta.getId()).get(i).getId();
			randevuData[1]= randevu.getHastaList(hasta.getId()).get(i).getDoktorName();
			randevuData[2]= randevu.getHastaList(hasta.getId()).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}
		
		hayvanModel = new DefaultTableModel();
		Object[] colhayvan = new Object[5];
		colhayvan[0] = "ID";
		colhayvan[1] = "HAYVAN ADI";
		colhayvan[2] = "HAYVAN TİPİ";
		colhayvan[3] = "HAYVAN YAŞI";
		colhayvan[4] = "HAYVAN CİNSİYETİ";
		hayvanModel.setColumnIdentifiers(colhayvan);
		hayvanData = new Object[5];
		for(int i=0; i< hayvan.getHayvanList(hasta.getName()).size(); i++){
			hayvanData[0]= hayvan.getHayvanList(hasta.getName()).get(i).getHayvan_id();
			hayvanData[1]= hayvan.getHayvanList(hasta.getName()).get(i).getHayvan_name();
			hayvanData[2]= hayvan.getHayvanList(hasta.getName()).get(i).getHayvan_type();
			hayvanData[3]= hayvan.getHayvanList(hasta.getName()).get(i).getHayvan_age();
			hayvanData[4]= hayvan.getHayvanList(hasta.getName()).get(i).getHayvan_gender();
			hayvanModel.addRow(hayvanData);
		}
		

		
	
		
		setTitle("Veteriner Yonetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 100, 600, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(155, 230, 194));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Hosgeldiniz, Sayin "+hasta.getName());
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setBounds(10, 24, 438, 29);
		w_pane.add(label);
		
		JButton button = new JButton("Cikis Yap");
		button.setBackground(new Color(192, 192, 192));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginGUI login= new loginGUI();
				login.setVisible(true);
				dispose(); 
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		button.setBounds(450, 24, 104, 30);
		w_pane.add(button);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 86, 542, 333);
		w_pane.add(w_tab);
		
		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(new Color(62, 206, 137));
		w_tab.addTab("Randevu Sistemi", null, w_randevu, null);
		w_randevu.setLayout(null);
		
		
		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 39, 179, 255);
		w_randevu.add(w_scrollDoktor);
		
		table_doktor = new JTable(doktorModel);
		table_doktor.setBackground(new Color(192, 192, 192));
		w_scrollDoktor.setViewportView(table_doktor);
		
		JLabel lblDoktorListesi = new JLabel("Doktor Listesi");
		lblDoktorListesi.setBounds(10, 24, 83, 14);
		w_randevu.add(lblDoktorListesi);
		
		JLabel label_1 = new JLabel("Randevu Turu:");
		label_1.setBounds(199, 11, 123, 27);
		w_randevu.add(label_1);
		
		JComboBox select_randevuTuru = new JComboBox();
		select_randevuTuru.setBounds(199, 39, 129, 27);
		//select_randevuTuru.addItem("Ameliyat");
		for(int i=0; i<randevuTuru.getList().size(); i++){
			select_randevuTuru.addItem(new Item(randevuTuru.getList().get(i).getId(), randevuTuru.getList().get(i).getName()));
		}
		select_randevuTuru.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(select_randevuTuru.getSelectedIndex() != 0){
					JComboBox c= (JComboBox) e.getSource();
					Item item= (Item) c.getSelectedItem();
					DefaultTableModel clearModel= (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<randevuTuru.getRandevuTuruList(item.getKey()).size(); i++){
							doktorData[0]= randevuTuru.getRandevuTuruList(item.getKey()).get(i).getId();
							doktorData[1]= randevuTuru.getRandevuTuruList(item.getKey()).get(i).getName();
							doktorModel.addRow(doktorData);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				else{
					DefaultTableModel clearModel= (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_randevu.add(select_randevuTuru);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(348, 39, 179, 255);
		w_randevu.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		table_whour.setBackground(new Color(192, 192, 192));
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel lblUygunSaatler = new JLabel("Uygun Saatler");
		lblUygunSaatler.setBounds(348, 24, 146, 14);
		w_randevu.add(lblUygunSaatler);
		
		JLabel lblDoktorSec_1 = new JLabel("Doktor Sec:");
		lblDoktorSec_1.setBounds(216, 103, 93, 27);
		w_randevu.add(lblDoktorSec_1);
		
		JButton btn_selWhour = new JButton("Sec");
		btn_selWhour.setBackground(new Color(192, 192, 192));
		btn_selWhour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row= table_doktor.getSelectedRow();
				if(row >= 0){
					String deger= table_doktor.getModel().getValueAt(row, 0).toString();
					int id= Integer.parseInt(deger);
					DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i< whour.getWhourList(id).size(); i++){
							whourData[0]= whour.getWhourList(id).get(i).getId();
							whourData[1]= whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoktorID= id;
					selectDoktorName= table_doktor.getModel().getValueAt(row, 1).toString();
				}
				else{
					Helper.showMsg("Lutfen bir doktor seciniz!");
				}
			}
		});
		btn_selWhour.setBounds(216, 130, 93, 27);
		w_randevu.add(btn_selWhour);
		
		JLabel lblDoktorSec = new JLabel("Randevu Al:");
		lblDoktorSec.setBounds(216, 181, 93, 27);
		w_randevu.add(lblDoktorSec);
		
		JButton btn_addRandevu = new JButton("Al");
		btn_addRandevu.setBackground(new Color(192, 192, 192));
		btn_addRandevu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selRow= table_whour.getSelectedRow();
				if(selRow >= 0){
					String date= table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean kontrol= hasta.addRandevu(selectDoktorID, hasta.getId(), selectDoktorName, hasta.getName(), date);
						if(kontrol){
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoktorID, date);
							updateWhourModel(selectDoktorID);
							updateRandevuModel(hasta.getId());
						}
						else{
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				else{
					Helper.showMsg("Lutfen gecerli bir tarih giriniz!");
				}
			}
		});
		btn_addRandevu.setBounds(216, 208, 93, 27);
		w_randevu.add(btn_addRandevu);
		
		JPanel w_randevularim = new JPanel();
		w_randevularim.setBackground(new Color(62, 206, 137));
		w_tab.addTab("Randevularim", null, w_randevularim, null);
		w_randevularim.setLayout(null);
		
		JScrollPane w_scrollRandevu = new JScrollPane();
		w_scrollRandevu.setBounds(10, 11, 517, 283);
		w_randevularim.add(w_scrollRandevu);
		
		table_randevularim = new JTable(randevuModel);
		w_scrollRandevu.setViewportView(table_randevularim);
		table_randevularim.setBackground(new Color(192, 192, 192));
		
		JPanel w_hayvanlarim = new JPanel();
		w_hayvanlarim.setBackground(new Color(62, 206, 137));
		w_hayvanlarim.setToolTipText("");
		w_tab.addTab("Evcil Hayvanlarim", null, w_hayvanlarim, null);
		w_hayvanlarim.setLayout(null);
		
		JScrollPane w_scrollHayvan = new JScrollPane();
		w_scrollHayvan.setBounds(10, 56, 506, 238);
		w_hayvanlarim.add(w_scrollHayvan);
		
		table_hayvanlarim = new JTable(hayvanModel);
		table_hayvanlarim.setBackground(new Color(192, 192, 192));
		w_scrollHayvan.setViewportView(table_hayvanlarim);
		
		JButton btnNewButton = new JButton("Yeni Evcil Hayvan Ekle");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HayvanEkleGUI hgui = new HayvanEkleGUI(hasta);
				hgui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(318, 11, 200, 30);
		w_hayvanlarim.add(btnNewButton);
	}
	
	public void updateWhourModel( int doktor_id) throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< whour.getWhourList(doktor_id).size(); i++){
			whourData[0]= whour.getWhourList(doktor_id).get(i).getId();
			whourData[1]= whour.getWhourList(doktor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	public void updateRandevuModel( int hasta_id) throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_randevularim.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< randevu.getHastaList(hasta_id).size(); i++){
			randevuData[0]= randevu.getHastaList(hasta_id).get(i).getId();
			randevuData[1]= randevu.getHastaList(hasta_id).get(i).getDoktorName();
			randevuData[2]= randevu.getHastaList(hasta_id).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}
	}

}
