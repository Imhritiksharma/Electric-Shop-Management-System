package pkg_main;

import java.util.InputMismatchException;
import java.util.Scanner;
import pkg_product.Product;
import pkg_product.ProductManager;
import pkg_repairproduct.RepairProduct;
import pkg_repairproduct.RepairProductManager;
import pkg_transaction.RepairProductTransactionManager;
import pkg_exception.ProductNotFoundException;
import pkg_password.PasswordManager;

public class Main {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		try{
			PasswordManager passm = new PasswordManager();
			if(!passm.isEmpty()) {
				System.out.println("Login to Access ----->>>>");
				System.out.println("Enter Email id");
				String email = sc.nextLine();
				System.out.println("Enter Password");
				String pass = sc.nextLine();
				boolean pas = passm.singin(email, pass);
				if(!pas) {
					return;
				}

			}
			else {
				String emailid;
				String pass;
				System.out.println("Enter info to Create Account ------>>>>>>");
				System.out.println("Enter Email id");
				emailid = sc.nextLine();
				System.out.println("Enter Password");
				pass = sc.nextLine();
				boolean fli = passm.firstLogin(emailid, pass);
				if(!fli){
					return;
				}
				System.out.println("Welcome to Shop System");
				System.out.println("Your login info");
				passm.viewLogininfo();
			 }
		}catch(InputMismatchException ie){
			System.out.println("Enter valid information");
			return;
		}
		
		try {
		int choice;
		
		sc = new Scanner(System.in);
		ProductManager pm = new ProductManager();
		RepairProductManager rpm = new RepairProductManager();
		RepairProductTransactionManager rptm = new RepairProductTransactionManager();
		System.out.println(" .........>");
		
		do {
			System.out.println("Enter 1 to New Product\nEnter 2 to Repair Product\nEnter 3 to Logout\nEnter 9 to Change PassWord");
			choice = sc.nextInt();
			if(choice == 1) {
				int pro_choice;
				do {
					System.out.println("Enter 1 to Search By Product Number\nEnter 2 to Search By Producct Name\n"
					+"Enter 3 to View All Product List\nEnter 4 to Add a Product \nEnter 5 to Delete a Product\n"
					+"Enter 6 to Update MRP,RLP\nEnter 99 to go home");
					pro_choice = sc.nextInt();
					
					switch(pro_choice) {
					case 1:   // search pro_no     InputMismatchException
						System.out.println("Enter Product Number");
						int prono = sc.nextInt();
						Product productno = pm.searchbyno(prono);
						if(productno == null) {
							System.out.println("Product Not Found\n");
						}
						else {
							System.out.println(productno+"\n");
						}
						break;
					case 2:    // search by name
						try {
							System.out.println("Enter Product Name");
							sc.nextLine();
							String productname = sc.nextLine();
							pm.searchbyName(productname);
							System.out.println("");
						}
						catch(InputMismatchException ime) {
							System.out.println("Please Type Product Name"); 
						}
						break;
					case 3:  // view all pro
						System.out.println("All Product Details..");
						pm.viewAll();
						System.out.println("");
						break;
					case 4:  // add product
						 String pro_name;
						 int pro_no;
						 int mRP;
						 int rLP;
						
						System.out.println("Enter Product Details.....>>");
						
						System.out.println("Enter Product Name");
						sc.nextLine();
						pro_name = sc.nextLine();
						
						System.out.println("Enter Product Number");
						pro_no = sc.nextInt();
						
						System.out.println("Enter Product Price");
						mRP = sc.nextInt();
						
						System.out.println("Enter Product RLP");
						rLP = sc.nextInt();
						
						Product pr = new Product(pro_name,pro_no,mRP,rLP);
						if(!pm.equals(pro_no)) {
							pm.addpro(pr);
							System.out.println("Product Added in The Shop");
						}
						else {
							System.out.println("Product Number Already Exist in The Shop");
						}
						break;
						
					case 5:  // delete product
						System.out.println("Enter Product Number What you Want to Delete");
						int deletepro = sc.nextInt();
						
						try {
							if(!pm.deleteproduct(deletepro)) {
								throw new ProductNotFoundException();
							}
							else {
								System.out.println("Product has been Deleted\n");
							}
						}
						catch(ProductNotFoundException pnfe) {
							System.out.println(pnfe+"\n");
						}
						break;
					case 6:   // update price
						System.out.println("Enter Product Number for Update Price");
						int updateprice = sc.nextInt();
						System.out.println("Enter Product MRP");
						mRP = sc.nextInt();
						System.out.println("Enter Product RLP");
						rLP = sc.nextInt();
						if(pm.updateproduct(updateprice, mRP, rLP)){
							System.out.println("Price has been Updated\n");
						}
						else {
							System.out.println("This Product is not Found\n");
						}
						break;
					case 99: 
						System.out.print("Thanks for using\n");
						break;
					default:
						System.out.println("Sorry Please Enter Vailid Number\n");
					
					}
				}while(pro_choice!=99);
			}
			else if(choice == 2) {
				int repro_choice;
				do {
					System.out.println("Enter 1 to Add Product For Repair\nEnter 2 to Search\n"
					+"Enter 3 to View All\nEnter 99 to Exits");
					repro_choice = sc.nextInt();
					
					switch(repro_choice) {
					case 1:   // add repair product
						 String customer_name;
						 String product_name;
						 int product_no;
						 String phone_no;
						 int total_rs;
						 int qty;
						 String problem_in_product;
						 
						 sc.nextLine();
						 System.out.println("Customer Name");
						 customer_name = sc.nextLine();
						 
						 System.out.println("Product Name");
						 product_name = sc.nextLine();
						 
						 System.out.println("Enter Product Number:-");
						 product_no = sc.nextInt();
						 
						 sc.nextLine();
						 
						 System.out.println("Enter Customer Phone Number:-");
						 phone_no = sc.nextLine();
						 
						 System.out.println("Enter Expected Rupees for Repair That Product");
						 total_rs = sc.nextInt();
						 
						 System.out.println("Enter Quantity");
						 qty = sc.nextInt();
						 
						 sc.nextLine();
						
						 System.out.println("Write Product Problem");
						 problem_in_product = sc.nextLine();
						 
						 RepairProduct reprot = new RepairProduct( customer_name,  product_name,  product_no,  phone_no,  total_rs,qty, problem_in_product);
						if(rpm.addRepairpro(reprot) && rptm.receiveproduct(product_no, phone_no)) {
							rpm.writeToFile();
							rptm.writeToFile();
							 System.out.println("Product Recieved Successfully in the Shop\n");
						 }else{
							System.out.println("Product already exists");
						 }
						 break;
					case 2 :   //   search by phone no than delete or not
						 System.out.println("Enter Repair Product Number");
						 int repro_no = sc.nextInt();
						 reprot = rpm.searchbyno(repro_no);
						 if(reprot != null) {
							 System.out.println("\nEnter 11 to Return Product\nEnter 99 to Back");
							 int delteornot = sc.nextInt();
							 if(delteornot == 11) {
								 System.out.println("Enter Phone Number");
								 sc.nextLine();
								 phone_no = sc.nextLine();
  							     if(rpm.delterepro(repro_no, phone_no) && rptm.returnproduct(repro_no, phone_no)) {
									rpm.writeToFile();
									rptm.writeToFile();
									System.out.println("Retruned Successfully");
								 }else{
									System.out.println("Product not found");
								 }
							 }
							 else if(delteornot == 99){
								 System.out.println("You Are Back Now");
							 }else{
								System.out.println("Invalid Choice");
							 }
						 }
						 else {
							 System.out.println("Sorry .. Not Found");
						 }
						 break;
						 
					case 3:  // view all
						System.out.println("Enter 11 to View All Repair Product\nEnter 22 to View All Transaction");
						int viewall = sc.nextInt();
						if(viewall == 11) {
							System.out.println("All Repir Product");
							rpm.viewAll();
							System.out.println("->->->->->->->->>");
						}
						else if(viewall == 22) {
							System.out.println("All Transaction");
							rptm.viewAll();
							System.out.println("->->->->->->->->>");
						}else{
							System.out.println("Invalid Choice");
						}
						 break;
					case 99: 
						System.out.println("Thanks for Using");
						break;
					default:
					    System.out.println("Enter Vailid");
						 
					}
				}while(repro_choice !=99);
			}
			else if(choice == 3) {
				System.out.println("Thanks for Using \n Have a GoodDay");
			}else if(choice == 9){
				PasswordManager pManager = new PasswordManager();
				sc.nextLine();
				System.out.println("Enter Email to change Password");
				String email = sc.nextLine();
				System.out.println("Enter Current Password");
				String password = sc.nextLine();
				System.out.println("Enter New PassWord");
				String newpass = sc.nextLine();
				pManager.changepassword(email, password, newpass);
				System.out.println("");
			}
			else {
				System.out.println("Please..  Enter Vailid Number");
			}
			
		}while(choice !=3);
		sc.close();
		}
		catch(InputMismatchException im) {
//			im.getCause().getMessage();
			System.out.println("Command Read Carefully and Enter Carefully");
		}
		
	}
}
