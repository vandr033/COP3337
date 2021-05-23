
import java.util.Date;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.ArrayList;

public class TestBankAccount {

	public static void main(String args[])
	{
	
		Database db = new Database(); // creates database
		Database close = new Database();// creates db for inactive accounts
		Address testAddress = new Address("123","Miami","33159", "FL");
		Manufacturer testManu = new Manufacturer("Apple",testAddress);
		Product testProduct = new Product("Iphone",10, 99.9,"10/21/2020", testManu);
		db.add(testProduct);
		
		
//		Address testAddress1 = new Address("123","Miami","33159", "FL");
//		Manufacturer testManu1 = new Manufacturer("Apple",testAddress1);
//		Product testProduct1 = new Product("Iphone",10, 99.9,"10/21/2020", testManu1);
//		close.add(testProduct1);
		boolean done = false;
		
		while (!done) {
			
			int firstOption = GetData.getInt("\tABC Enterprises Inventory Manager\n" 
					+ "\nPlease choose from the following:" + "\n1. Locate a Single Product"+ 
					"\n2.Create an inventory report" + "\n3. List all deleted products" 
					+ " \n4. Update Product information" + "\n5.Add a Product" + "\n6.Delete a Product \n7.Exit");
					
			switch(firstOption)
			{
			case 1: 
				//Locates a single product from the DB
				String pdNameSearch = GetData.getString("Enter the product name: "); 
            	db.search(pdNameSearch);
            	
            	if(!db.inList()) {
            		JOptionPane.showMessageDialog(null, "Product not found.");
            	} else {
            		
            		Product searchedProduct = db.getProduct();
            		String s = "Name\tPrice\tQuantity\n";
            		s = s + searchedProduct.getProdName() + "\t$" + searchedProduct.getunitPrice() + "\t"  + searchedProduct.getQuantity();
            		display(s, "Product", 0);
            		
            	}
				break;
			case 2: 
				//Creates an inventory report

				ArrayList list = db.getList();
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty");
				}else
				{
					int i= 0;
					int length = db.size();
					String s = "Product:\tPurchase Date\tQuantity\tPrice\tManufacturer\tState\n";
					while(i<length)
					{
						Product p = (Product)list.get(i);
						
						s = s + p.getProdName() + "\t" + p.getDate() + "\t" + p.getQuantity() + "\t" + p.getunitPrice() +  "\t" + p.getManu().getManuName()+ "\t" + p.getManu().getAddress().getZip() + "\n";
						i++;
					}
					display(s,"Deleted Products",1);
				}
		
				
				
				
	
				
				
				break;
			case 3: 
				// shows all deleted products
				ArrayList deletelist = close.getList();
				if(deletelist.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty");
				}else
				{
					int i= 0;
					int length = db.size();
					String s = "Product:\tPurchase Date\tManufacturer\tState\n";
					while(i<length)
					{
						Product p = (Product)deletelist.get(i);
						
						s = s + p.getProdName() + "\t" + p.getDate()  + "\t"   + p.getManu().getManuName()+ "\t" + p.getManu().getAddress().getZip() + "\n";
						i++;
					}
					display(s,"Deleted Products",1);
				}
				break;
			case 4: 
				// updates product information
				String searchProdName = GetData.getString("Enter name of product you'd like to update");
            	
            	db.search(searchProdName); // search for the name in the database
            	
            	if(!db.inList()) {
            		JOptionPane.showMessageDialog(null,"Product name not found");
            	}else {
            		int option = GetData.getInt("Would you like to (type 1) Change the price, (type 2) Change the quantity");
            		
            		switch(option) {
            		case 1: //change product price 
            			double newPrice = GetData.getDouble("Enter new price of product"); 
            			Product updatedProdPriceObj = db.getProduct(); // make a new wProduct object to update the price
            			updatedProdPriceObj.updatePrice(newPrice);
            			
            			break;
            			
            		case 2: 
            			// change quantity 
            			int newProdAmount = GetData.getInt("Enter the new amount");
            			Product updatedProdQuanObj = db.getProduct();// make a new Product object to update the quantity
            			updatedProdQuanObj.updateQuantity(newProdAmount);
            			break; 
            			
            			default: //error message 
            				JOptionPane.showMessageDialog(null, "Invalid option");
            		}
            	}
            	
            	
				
				break;
			case 5:
				// add a product
				
				
				String name =  GetData.getString("What is the name of the product?");
				Double price = GetData.getDouble("What is the price of the product");
				int quantity = GetData.getInt("How many products are there?");
				
				String productDate = GetData.getString("Please enter a date (dd/MM/yyy)" );
				while(!db.isValidDate(productDate)) {
					 productDate = GetData.getString("Please enter a valid date dd/MM/yyy format" );
				}
				
				String manuAddStr = GetData.getString("Please enter the street of the manufacturer");
				String manuAddCity = GetData.getString("Please enter the City of the manufacturer");
				String manuAddZip = GetData.getString("Please enter the Zip of the manufacturer");
				String manuAddState = GetData.getString("Please enter the State of the manufacturer");
				while (!db.isValidString(manuAddState)) {
					manuAddState = GetData.getString("Please enter the State of the manufacturer");
				}
				
				Address newAddress = new Address(manuAddStr, manuAddCity, manuAddZip, manuAddState);
				String manufacturerName = GetData.getString("Please enter the name of the manufacturer");
				Manufacturer newManu = new Manufacturer(manufacturerName, newAddress);
				
				Product newProduct= new Product(name, quantity, price, productDate, newManu); 
				
				//saves product into array
				db.add(newProduct);
				
				break;
			case 6:
				 //Delete a product
                String addedProdName = GetData.getString("Which product would you like to delete?");
                db.search(addedProdName); //searches for product name in database

                if(!db.inList())
                {
                    //show option pane
                    JOptionPane.showMessageDialog(null, "Product not found.");
                }
                else //if product in list
                {
                    Product p = db.getProduct();//get product from database
                    int index = db.showIndex(); //get index
                    close.add(db.delete(index)); //add deleted object to close database
                    JOptionPane.showMessageDialog(null, "The " + addedProdName + " product has been deleted."); //delete message
                }
				
				break;
			case 7: 
				// Exit
				done = true;
				break;
				default:
					JOptionPane.showMessageDialog(null, "Please enter a Valid Option");
			}
			
			

				
		}
		
		

	}
	static void display(String s, String heading, int MESSAGE_TYPE) {
		JTextArea text = new JTextArea(s,20,30);
		JScrollPane pane = new JScrollPane(text);
		JOptionPane.showMessageDialog(null, pane, heading, MESSAGE_TYPE);
	}
	
}

