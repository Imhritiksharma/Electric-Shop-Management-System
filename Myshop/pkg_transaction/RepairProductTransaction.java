package pkg_transaction;

import java.io.Serializable;

public class RepairProductTransaction implements Serializable {
	private int product_no;
	private String phone_no;
	
	private String receiveDate;
	private String returnDate;
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public RepairProductTransaction(int product_no, String phone_no, String receiveDate, String returnDate) {
		super();
		this.product_no = product_no;
		this.phone_no = phone_no;
		this.receiveDate = receiveDate;
		this.returnDate = returnDate;
	}
	public RepairProductTransaction() {
		super();
	}
	@Override
	public String toString() {
		return "Transaction [Product_no = " + product_no + ", phone_no = " + phone_no + ", Receive Date = "
				+ receiveDate + " , Return Date = " + returnDate + "]";
	}
	
	
}
