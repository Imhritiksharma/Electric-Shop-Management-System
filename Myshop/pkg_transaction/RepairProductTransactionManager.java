package pkg_transaction;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RepairProductTransactionManager {
	ObjectInputStream ois_repairtransaction = null;
	ObjectOutputStream oos_repairtransaction = null;
	
	File repairtransaction_file = null;
	ArrayList<RepairProductTransaction>  repairtransaction_list = null;
	
	@SuppressWarnings("unchecked")
	public RepairProductTransactionManager() {
		repairtransaction_file = new File("RepairProductTransaction.dat");
		repairtransaction_list = new ArrayList<RepairProductTransaction>();
		
		if(repairtransaction_file.exists()) {
			try {
				ois_repairtransaction = new ObjectInputStream(new FileInputStream(repairtransaction_file));
				repairtransaction_list = (ArrayList<RepairProductTransaction>) ois_repairtransaction.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void viewAll() {
		for(RepairProductTransaction repairtra:repairtransaction_list) {
			System.out.println(repairtra);
		}
	}
	public boolean receiveproduct(int product_no,String phone_no) {
		for(RepairProductTransaction repairtran:repairtransaction_list) {
			if((repairtran.getProduct_no() == product_no) &&(repairtran.getPhone_no().equals(phone_no)) &&(repairtran.getReturnDate() == null)) {
				return false;
			}
		}
		String dateofreceive = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		RepairProductTransaction reparipro = new RepairProductTransaction( product_no,  phone_no,  dateofreceive,  null);
		repairtransaction_list.add(reparipro);
		return true;
	}
	public boolean returnproduct(int product_no,String phone_no) {
		for(RepairProductTransaction repairpro:repairtransaction_list) {
			if((repairpro.getProduct_no() == product_no) && (repairpro.getPhone_no().equals(phone_no))) {
				if((repairpro.getReturnDate() == null)){
					String returndate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					repairpro.setReturnDate(returndate);
					return true;
				}
			}
		}
		return false;
	}
	public void writeToFile() {
		try {
			oos_repairtransaction = new ObjectOutputStream(new FileOutputStream(repairtransaction_file));
			oos_repairtransaction.writeObject(repairtransaction_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
