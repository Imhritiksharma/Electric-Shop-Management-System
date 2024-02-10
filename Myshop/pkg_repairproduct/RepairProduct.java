package pkg_repairproduct;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class RepairProduct implements Serializable {
		private String customer_name;
		private String product_name;
		private int product_no;
		private String phone_no;
		private int total_rs;
		private int qty;
		private String problem_in_product;
		
		public String getCostomer_name() {
			return customer_name;
		}
		public void setCostomer_name(String costomer_name) {
			boolean ismatched = Pattern.matches("[a-zA-Z ]+", costomer_name);
			if(ismatched) {
				this.customer_name = costomer_name;
			}
			else {
				this.customer_name = "Customer";
			}
			
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
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
			boolean isValidno = Pattern.matches("[6-9][0-9]{9}", phone_no);
		try {
			  if(isValidno) {
			     	this.phone_no = phone_no;
			   }
			   else {
				     throw new InputMismatchException();
			    }
				
			 }
			catch(InputMismatchException im) {
				System.out.println("Please Enter Vailid Phone Number\n" + im);
			}
		}
		public int getTotal_rs() {
			return total_rs;
		}
		public void setTotal_rs(int total_rs) {
			this.total_rs = total_rs;
		}
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		public String getProblem_in_product() {
			return problem_in_product;
		}
		public void setProblem_in_product(String problem_in_product) {
			this.problem_in_product = problem_in_product;
		}
		public RepairProduct(String costomer_name, String product_name, int product_no, String phone_no, int total_rs,int qty,
				String problem_in_product) {
			super();
			this.setCostomer_name(costomer_name);
			this.product_name = product_name;
			this.product_no = product_no;
			this.setPhone_no(phone_no);
			this.total_rs = total_rs;
			this.qty = qty;
			this.problem_in_product = problem_in_product;
		}
		public RepairProduct() {
			super();
		}
		@Override
		public String toString() {
			return "RepairProduct [Customer Name=" + customer_name + ", Product_name=" + product_name + ", Product_no="
					+ product_no + ", Phone_no=" + phone_no + ", Total Rupees =" + total_rs + ", Problem_in_product="
					+ problem_in_product + "]";
		}
		
}
	