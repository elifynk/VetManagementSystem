package view;

import helper.Helper;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Doktor;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class doktorGUI extends JFrame {

	private JPanel w_pane;
	public static Doktor doktor= new Doktor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData =null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doktorGUI frame = new doktorGUI(doktor);
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
	public doktorGUI() {
		getContentPane().setBackground(new Color(62, 206, 137));
		
	}
	public doktorGUI(Doktor doktor) throws SQLException {
		whourModel= new DefaultTableModel();
		Object[] colWhour= new Object[2];
		colWhour[0]= "ID";
		colWhour[1]= "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData= new Object[2];
		for(int i=0; i<doktor.getWhourList(doktor.getId()).size(); i++){
			whourData[0]= doktor.getWhourList(doktor.getId()).get(i).getId();
			whourData[1]= doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		setResizable(false);
		setTitle("Veteriner Yonetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(155, 230, 194));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Hosgeldiniz, Sayin "+doktor.getName());
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setBounds(10, 12, 428, 30);
		w_pane.add(label);
		
		JButton button = new JButton("Cikis Yap");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginGUI login= new loginGUI();
				login.setVisible(true);
				dispose(); 
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		button.setBounds(448, 11, 105, 31);
		button.setBackground(new Color(192, 192, 192));
		w_pane.add(button);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(10, 53, 542, 333);
		w_pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(new Color(62, 206, 137));
		w_tab.addTab("Calisma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 130, 20);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		select_time.setBounds(150, 11, 59, 20);
		w_whour.add(select_time);
		
		JButton btn_addhour = new JButton("Ekle");
		btn_addhour.setBackground(new Color(192, 192, 192));
		btn_addhour.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
				String date= "";
				String time,selectDate;
				try {
					date= sdf.format(select_date.getDate());
				} catch (Exception e2) {
					
				}
				if(date.length()==0){
					Helper.showMsg("Lutfen gecerli bir tarih seciniz!");
				}
				else{
					 time= " " + select_time.getSelectedItem().toString();
					 selectDate= date+time;
					try {
						boolean kontrol= doktor.addWhour(doktor.getId(), doktor.getName(), selectDate);
						if(kontrol){
							updateWhourModel(doktor);
							Helper.showMsg("success");
							
						
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
		btn_addhour.setBounds(219, 11, 59, 20);
		w_whour.add(btn_addhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 42, 537, 263);
		w_whour.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.setBackground(new Color(192, 192, 192));
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.setBackground(new Color(192, 192, 192));
		btn_deleteWhour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selRow= table_whour.getSelectedRow();
				if(selRow >=0){
					String selectRow= table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID= Integer.parseInt(selectRow);
					boolean kontrol;
					try {
						kontrol= doktor.deleteWhour(selID);
						if(kontrol){
							Helper.showMsg("success");
							updateWhourModel(doktor);
						}
						else{
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				else{
					Helper.showMsg("L�tfen bir tarih seciniz!");
				}
			}
		});
		btn_deleteWhour.setBounds(468, 11, 59, 20);
		w_whour.add(btn_deleteWhour);
	}
	
	public void updateWhourModel(Doktor doktor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<doktor.getWhourList(doktor.getId()).size(); i++){
			whourData[0]= doktor.getWhourList(doktor.getId()).get(i).getId();
			whourData[1]= doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
}
