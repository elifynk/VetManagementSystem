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

import model.RandevuTuru;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Color;
public class updateRandevuTuruGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_randevuTuruName;
	private static RandevuTuru randevuTuru;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateRandevuTuruGUI frame = new updateRandevuTuruGUI(randevuTuru);
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
	public updateRandevuTuruGUI(RandevuTuru randevuTuru) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(155, 230, 194));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Randevu Turu:");
		label.setBounds(10, 11, 86, 21);
		contentPane.add(label);
		
		fld_randevuTuruName = new JTextField();
		fld_randevuTuruName.setColumns(10);
		fld_randevuTuruName.setBounds(10, 32, 219, 20);
		fld_randevuTuruName.setText(randevuTuru.getName());
		contentPane.add(fld_randevuTuruName);
		
		JButton btn_updateRandevuTuru = new JButton("Duzenle");
		btn_updateRandevuTuru.setBackground(new Color(192, 192, 192));
		btn_updateRandevuTuru.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.karar("emin")){
					try {
						randevuTuru.updateRandevuTuru(randevuTuru.getId(), fld_randevuTuruName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btn_updateRandevuTuru.setBounds(10, 63, 219, 23);
		contentPane.add(btn_updateRandevuTuru);
	}
}
