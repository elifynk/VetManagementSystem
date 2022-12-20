package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Randevu;
import model.Whour;

public class randevularimGUI extends JFrame {

	private JPanel w_pane;
	private JTable table_randevularim;
	private DefaultTableModel randevuModel;
	private Object[] whourData= null;
	private Whour whour= new Whour();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] randevuData= null;
	private Randevu randevu= new Randevu();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					randevularimGUI frame = new randevularimGUI();
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
	public randevularimGUI() {
		JPanel w_randevularim = new JPanel();
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 86, 542, 333);
		w_pane.add(w_tab);
		w_randevularim.setBackground(new Color(155, 230, 194));
	
		w_randevularim.setLayout(null);
		w_tab.addTab("Randevularim", null, w_randevularim, null);
		JScrollPane w_scrollRandevu = new JScrollPane();
		w_scrollRandevu.setBounds(10, 11, 517, 283);
		w_randevularim.add(w_scrollRandevu);
		
		table_randevularim = new JTable(randevuModel);
		table_randevularim.setBackground(new Color(192, 192, 192));
		w_scrollRandevu.setViewportView(table_randevularim);
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
