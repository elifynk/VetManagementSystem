package helper;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void changeButtonText(){
		UIManager.put("OptionPane.cancelButtonText", "Iptal");
		UIManager.put("OptionPane.noButtonText", "Hayir");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	public static void showMsg(String str){
		String msg;
		changeButtonText();
		switch(str){
		case "fill":
			msg="Lutfen tum alanlari doldurunuz!";
			break;
		case "success":
			msg="Islem basarili!";
			break;
		case "error":
			msg="Bir hata olustu!";
			break;
		default:
			msg= str;
		}
		JOptionPane.showMessageDialog(null,msg ,"Mesaj" , JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean karar(String str){
		String msg;
		changeButtonText();
		switch(str){
		case "emin":
			msg="Bu islemi gerceklestirmek istiyor musun?";
			break;
		default:
			msg= str;
			break;
		}
		int snc= JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
		if(snc==0){
			return true;
		}
		else{
			return false;
		}
	}
}

