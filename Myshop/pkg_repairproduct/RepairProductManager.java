package pkg_repairproduct;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RepairProductManager {
	ObjectInputStream ois_repair = null;
	ObjectOutputStream oos_repair = null;
	
	File repair_file = null;
	ArrayList<RepairProduct> repair_list = null;
	
	@SuppressWarnings("unchecked")
	public RepairProductManager() {
		repair_file = new File("Repair_Product.dat");
		repair_list = new ArrayList<RepairProduct>();
		
		if(repair_file.exists()) {
			try {
				ois_repair = new ObjectInputStream(new FileInputStream(repair_file));
				repair_list = (ArrayList<RepairProduct>) ois_repair.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean equals(int product_no,String phone_no) {
		for(RepairProduct rp:repair_list) {
			if((rp.getProduct_no() == product_no) && (rp.getPhone_no().equals(phone_no))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addRepairpro(RepairProduct reprot) {
		for(RepairProduct oldrepro:repair_list) {
			if((oldrepro.getProduct_no() == reprot.getProduct_no()) && (oldrepro.getPhone_no().equals(reprot.getPhone_no()))) {
				return false;
			}
		}	
		repair_list.add(reprot);
		return true;
	}
	public void viewAll() {
		for(RepairProduct repairpro:repair_list) {
			System.out.println(repairpro);
		}
	}
	public RepairProduct searchbyno(int search_no) {
		RepairProduct local = null;
		for(RepairProduct repairpro:repair_list) {
			if(repairpro.getProduct_no() == search_no) {
				System.out.println(repairpro);
				local = repairpro;
			}
		}
		return local;
	}
	public boolean delterepro(int product_no,String phone_no) {
		ListIterator<RepairProduct> iter = repair_list.listIterator();
		while(iter.hasNext()) {
			RepairProduct repro = iter.next();
			if((repro.getProduct_no() == product_no) && (repro.getPhone_no().equals(phone_no))) {
				repair_list.remove(repro);
				return true;
			}
		}
		return false;
	}
	public RepairProduct searchbyphoneno(String search_phoneno) {
		RepairProduct local = null;
		for(RepairProduct reparipro:repair_list) {
			if(reparipro.getPhone_no().equals(search_phoneno)) {
				System.out.println(reparipro);
				local = reparipro;
			}
		}
		return local;
	}
	public void writeToFile() {
		try {
			oos_repair = new ObjectOutputStream(new FileOutputStream(repair_file));
			oos_repair.writeObject(repair_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
