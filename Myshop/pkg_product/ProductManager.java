package pkg_product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProductManager {
	ObjectInputStream ois_product = null;
	ObjectOutputStream oos_product = null;
	
	File product_file = null;
	ArrayList<Product> product_list = null;
	
	@SuppressWarnings("unchecked")
	public ProductManager() {
		product_file = new File("Product.dat");
		product_list = new ArrayList<Product>();
		
		if(product_file.exists()) {
			try {
				ois_product = new ObjectInputStream(new FileInputStream(product_file));
				product_list = (ArrayList<Product>) ois_product.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean equals(int pro_no) {
		for(Product p:product_list) {
			if(p.getPro_no() == pro_no) {
				return true;
			}
		}
		return false;
	}
	public void addpro(Product addprod) {
		product_list.add(addprod);
		writeToFile();
	}
	public boolean updateproduct(int pro_no,int mRP,int rLP) {
		for(Product p:product_list) {
			if(p.getPro_no() == pro_no) {
				p.setMRP(mRP);
				p.setRLP(rLP);
				writeToFile();
				return true;
			}
		}
		return false;
	}
	public void viewAll() {
		for(Product pro:product_list) {
			System.out.println(pro);
		}
	}
	public Product searchbyno(int search_no) {
		for(Product prod:product_list) {
			if(prod.getPro_no() == search_no) {
				return prod;
			}
		}
		return null;
	}
	public void searchbyName(String searchname) {
		for(Product product:product_list) {
			if(product.getPro_name().toLowerCase().contains(searchname.toLowerCase())) {
				System.out.println(product);
			}
		}
	}
	public boolean deleteproduct(int delete_prono) {
		ListIterator<Product> list_iter = (ListIterator<Product>) product_list.listIterator();
		while(list_iter.hasNext()) {
			Product product = list_iter.next();
			if(product.getPro_no() == delete_prono) {
				product_list.remove(product);
				writeToFile();
				return true;
			}
		}
		
		return false;
	}
	public void writeToFile() {
		try {
			oos_product = new ObjectOutputStream(new FileOutputStream(product_file));
			oos_product.writeObject(product_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
