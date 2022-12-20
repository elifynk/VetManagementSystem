package view;

import helper.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import model.*;

import javax.swing.*;
import javax.swing.event.TableModelListener;

import java.awt.event.*;

public class danismanGUI extends JFrame {

	static Danisman danisman = new Danisman();
	RandevuTuru randevuTuru = new RandevuTuru();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JPasswordField fld_dPass;
	private JTextField fld_doktorID;
	private JTable table_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTable table_randevuTuru;
	private JTextField fld_randevuTuruName;
	private DefaultTableModel randevuTuruModel = null;
	private Object[] randevuTuruData = null;
	private JPopupMenu randevuTuruMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					danismanGUI frame = new danismanGUI(danisman);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public danismanGUI(Danisman danisman) throws SQLException {
		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "TC NO";
		colDoktorName[3] = "Sifre";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i = 0; i < danisman.getDoctorList().size(); i++) {
			doktorData[0] = danisman.getDoctorList().get(i).getId();
			doktorData[1] = danisman.getDoctorList().get(i).getName();
			doktorData[2] = danisman.getDoctorList().get(i).getTcno();
			doktorData[3] = danisman.getDoctorList().get(i).getParola();
			doktorModel.addRow(doktorData);
		}

		randevuTuruModel = new DefaultTableModel();
		Object[] colrandevuTuru = new Object[2];
		colrandevuTuru[0] = "ID";
		colrandevuTuru[1] = "Randevu Turu";
		randevuTuruModel.setColumnIdentifiers(colrandevuTuru);
		randevuTuruData = new Object[2];
		for (int i = 0; i < randevuTuru.getList().size(); i++) {
			randevuTuruData[0] = randevuTuru.getList().get(i).getId();
			randevuTuruData[1] = randevuTuru.getList().get(i).getName();
			randevuTuruModel.addRow(randevuTuruData);
		}
		
		DefaultTableModel workerModel= new DefaultTableModel();
		Object[] colWorker= new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad_Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData= new Object[2];
		
		setTitle("Veteriner Yonetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(155, 230, 194));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Sayin "
				+ danisman.getName());
		lblNewLabel.setBackground(new Color(20, 37, 154));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 12, 442, 29);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Cikis Yap");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginGUI login= new loginGUI();
				login.setVisible(true);
				dispose(); 
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton.setBounds(448, 11, 104, 30);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(new Color(192, 192, 192));
		w_tab.setBounds(10, 52, 542, 333);
		w_pane.add(w_tab);

		JPanel w_doktor = new JPanel();
		w_doktor.setBackground(new Color(62, 206, 137));
		w_tab.addTab("Doktor Listesi", null, w_doktor, null);
		w_doktor.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad-Soyad");
		lblNewLabel_1.setBounds(13, 11, 61, 21);
		w_doktor.add(lblNewLabel_1);

		fld_dName = new JTextField();
		fld_dName.setBounds(13, 32, 86, 20);
		w_doktor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel lblTcNo = new JLabel("TC No");
		lblTcNo.setBounds(13, 61, 61, 21);
		w_doktor.add(lblTcNo);

		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setBounds(13, 114, 61, 21);
		w_doktor.add(lblSifre);

		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(13, 83, 86, 20);
		w_doktor.add(fld_dTcno);

		fld_dPass = new JPasswordField();
		fld_dPass.setBounds(13, 132, 86, 21);
		w_doktor.add(fld_dPass);

		JButton btn_addDoktor = new JButton("Ekle");
		btn_addDoktor.setBackground(new Color(192, 192, 192));
		btn_addDoktor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0
						|| fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean kontrol = danisman.addDoktor(
								fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (kontrol) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoktorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoktor.setBounds(10, 164, 89, 23);
		w_doktor.add(btn_addDoktor);

		JLabel lblKullaniciId = new JLabel("Kullanici ID");
		lblKullaniciId.setBounds(10, 203, 86, 21);
		w_doktor.add(lblKullaniciId);

		JButton btnSil = new JButton("Sil");
		btnSil.setBackground(new Color(192, 192, 192));
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorID.getText().length() == 0) {
					Helper.showMsg("Lutfen gecerli bir doktor seciniz!");
				} else {
					if (Helper.karar("emin")) {
						int selectedID = Integer.parseInt(fld_doktorID
								.getText());
						try {
							boolean kontrol = danisman.deleteDoktor(selectedID);
							if (kontrol) {
								Helper.showMsg("success");
								fld_doktorID.setText(null);
								updateDoktorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSil.setBounds(10, 254, 89, 23);
		w_doktor.add(btnSil);

		fld_doktorID = new JTextField();
		fld_doktorID.setColumns(10);
		fld_doktorID.setBounds(10, 223, 86, 20);
		w_doktor.add(fld_doktorID);

		JScrollPane w_scroolDoktor = new JScrollPane();
		w_scroolDoktor.setBounds(131, 11, 318, 266);
		w_doktor.add(w_scroolDoktor);

		table_doktor = new JTable(doktorModel);
		table_doktor.setBackground(new Color(209, 209, 209));
		w_scroolDoktor.setViewportView(table_doktor);

		JPanel w_randevuTuru = new JPanel();
		w_randevuTuru.setBackground(new Color(62, 206, 137));
		w_tab.addTab("Randevu Turu", null, w_randevuTuru, null);
		w_randevuTuru.setLayout(null);

		JScrollPane w_scrollRandevuTuru = new JScrollPane();
		w_scrollRandevuTuru.setBounds(10, 11, 166, 270);
		w_randevuTuru.add(w_scrollRandevuTuru);

		randevuTuruMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Guncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		randevuTuruMenu.add(updateMenu);
		randevuTuruMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_randevuTuru.getValueAt(table_randevuTuru.getSelectedRow(), 0).toString());
				RandevuTuru selectRandevuTuru = randevuTuru.getFetch(selID);
				updateRandevuTuruGUI updateGUI = new updateRandevuTuruGUI(selectRandevuTuru);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateRandevuTuruModel();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.karar("emin")) {
					int selID = Integer.parseInt(table_randevuTuru.getValueAt(table_randevuTuru.getSelectedRow(), 0).toString());
					try {
						if (randevuTuru.deleteRandevuTuru(selID)) {
							Helper.showMsg("success");
							updateRandevuTuruModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		table_randevuTuru = new JTable(randevuTuruModel);
		table_randevuTuru.setBackground(new Color(192, 192, 192));
		table_randevuTuru.setComponentPopupMenu(randevuTuruMenu);
		table_randevuTuru.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_randevuTuru.rowAtPoint(point);
				table_randevuTuru.setRowSelectionInterval(selectedRow,selectedRow);
			}
		});
		w_scrollRandevuTuru.setViewportView(table_randevuTuru);

		JLabel lbl_RandevuTuru = new JLabel("Randevu Turu:");
		lbl_RandevuTuru.setBounds(186, 11, 86, 21);
		w_randevuTuru.add(lbl_RandevuTuru);

		fld_randevuTuruName = new JTextField();
		fld_randevuTuruName.setColumns(10);
		fld_randevuTuruName.setBounds(186, 32, 102, 20);
		w_randevuTuru.add(fld_randevuTuruName);

		JButton btn_addRandevuTuru = new JButton("Ekle");
		btn_addRandevuTuru.setBackground(new Color(192, 192, 192));
		btn_addRandevuTuru.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fld_randevuTuruName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (randevuTuru.addRandevuTuru(fld_randevuTuruName.getText())) {
							Helper.showMsg("success");
							fld_randevuTuruName.setText(null);
							updateRandevuTuruModel();
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
			}
		});
		btn_addRandevuTuru.setBounds(186, 63, 102, 23);
		w_randevuTuru.add(btn_addRandevuTuru);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(298, 11, 166, 270);
		w_randevuTuru.add(w_scrollWorker);
		
		table_worker = new JTable();
		table_worker.setBackground(new Color(192, 192, 192));
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(186, 218, 102, 29);
		for(int i=0; i<danisman.getDoctorList().size();i++){
			select_doktor.addItem(new Item(danisman.getDoctorList().get(i).getId(), danisman.getDoctorList().get(i).getName()));
		}
		select_doktor.addActionListener(e ->{
			JComboBox c=(JComboBox) e.getSource();
			Item item= (Item) c.getSelectedItem();
		});
		w_randevuTuru.add(select_doktor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.setBackground(new Color(192, 192, 192));
		btn_addWorker.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int selRow= table_randevuTuru.getSelectedRow();
				if(selRow >= 0){
					String selRandevuTuru= table_randevuTuru.getModel().getValueAt(selRow, 0).toString();
					int selRandevuTuruID= Integer.parseInt(selRandevuTuru);
					Item doktorItem= (Item)select_doktor.getSelectedItem();
					try {
						boolean kontrol= danisman.addWorker(doktorItem.getKey(), selRandevuTuruID);
						if(kontrol){
							Helper.showMsg("success");
						}
						else{
							Helper.showMsg("error");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
				else{
					Helper.showMsg("Lutfen randevu turu seciniz!");
				}
			}
		});
		btn_addWorker.setBounds(186, 258, 102, 23);
		w_randevuTuru.add(btn_addWorker);
		
		JLabel label = new JLabel("Randevu Turu:");
		label.setBounds(186, 122, 102, 21);
		w_randevuTuru.add(label);
		
		JButton btn_workerSelect = new JButton("Sec");
		btn_workerSelect.setBackground(new Color(192, 192, 192));
		btn_workerSelect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int selRow= table_randevuTuru.getSelectedRow();
				if(selRow >= 0){
					String selRandevuTuru= table_randevuTuru.getModel().getValueAt(selRow, 0).toString();
					int selRandevuTuruID= Integer.parseInt(selRandevuTuru);
					DefaultTableModel clearModel= (DefaultTableModel)table_worker.getModel();
					clearModel.setRowCount(0);
					
			
					try {	
						for(int i=0; i< danisman.getRandevuTuruList(selRandevuTuruID).size(); i++){
							workerData[0]= danisman.getRandevuTuruList(selRandevuTuruID).get(i).getId();
							workerData[1]= danisman.getRandevuTuruList(selRandevuTuruID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}	
					table_worker.setModel(workerModel);
				}
				else{
					Helper.showMsg("Lutfen randevu turu seciniz!");
				}
			}
		});
		btn_workerSelect.setBounds(186, 154, 102, 23);
		w_randevuTuru.add(btn_workerSelect);
		table_doktor.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						try {
							fld_doktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
						} catch (Exception e2) {
						}
					}
				});

		table_doktor.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer.parseInt(table_doktor.getValueAt(
							table_doktor.getSelectedRow(), 0).toString());
					String selectedName = table_doktor.getValueAt(
							table_doktor.getSelectedRow(), 1).toString();
					String selectedTcno = table_doktor.getValueAt(
							table_doktor.getSelectedRow(), 2).toString();
					String selectedPass = table_doktor.getValueAt(
							table_doktor.getSelectedRow(), 3).toString();
					try {
						boolean kontrol = danisman.updateDoktor(selectedID,
								selectedTcno, selectedPass, selectedName);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
			}
		});
	}

	public void updateDoktorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0); 
		for (int i = 0; i < danisman.getDoctorList().size(); i++) {
			doktorData[0] = danisman.getDoctorList().get(i).getId();
			doktorData[1] = danisman.getDoctorList().get(i).getName();
			doktorData[2] = danisman.getDoctorList().get(i).getTcno();
			doktorData[3] = danisman.getDoctorList().get(i).getParola();
			doktorModel.addRow(doktorData);
		}
	}

	public void updateRandevuTuruModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_randevuTuru.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < randevuTuru.getList().size(); i++) {
			randevuTuruData[0] = randevuTuru.getList().get(i).getId();
			randevuTuruData[1] = randevuTuru.getList().get(i).getName();
			randevuTuruModel.addRow(randevuTuruData);
		}
	}
}
