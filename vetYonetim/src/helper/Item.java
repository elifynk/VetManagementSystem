package helper;

public class Item {
	private int key;
	private String deger;
	
	public Item(int key, String deger) {
		super();
		this.key = key;
		this.deger = deger;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getDeger() {
		return deger;
	}
	public void setDeger(String deger) {
		this.deger = deger;
	}
	
	@Override
	public String toString(){
		return deger;
	}
	
}
