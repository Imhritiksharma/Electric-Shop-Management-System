package pkg_product;

import java.io.Serializable;

public class Product implements Serializable {
	private String pro_name;
	private int pro_no;
	private int MRP;
	private int RLP;
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
			this.pro_name = pro_name;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public int getMRP() {
		return MRP;
	}
	public void setMRP(int mRP) {
		MRP = mRP;
	}
	public int getRLP() {
		return RLP;
	}
	public void setRLP(int rLP) {
		RLP = rLP;
	}
	public Product(String pro_name, int pro_no, int mRP, int rLP) {
		super();
		this.pro_name = pro_name;
		this.pro_no = pro_no;
		this.MRP = mRP;
		this.RLP = rLP;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product info [Product Name=" + pro_name + ", Product No:=" + pro_no + ", MRP=" + MRP + ", RLP=" + RLP + "]";
	}
	
}
