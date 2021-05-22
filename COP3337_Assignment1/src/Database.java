import java.util.ArrayList;


import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Database {
	ArrayList <Product> list;
	
	Product Product;
	int index;
	boolean existing;
	
	Database()
	{
		list = new ArrayList<Product>();
	}
	//adds the product object to the list database
	void add(Product Products)
	{
		list.add(Products);
	}
	
	Product delete(int n)
	{
		return list.remove(n); 
	}
	
	

	
	void search(String P) { //search method with parameter of the product name
		 existing = false; 
		int i =0;
		while(!existing && i < list.size()) {
			Product p = list.get(i); // another instance of product class
			
			if(p.getProdName().equalsIgnoreCase(P)) {
				Product = p;
				existing = true;
				index = i; 
				
			} else {
				i++;
			}
		}
		
	}
	
	
	
	// I dont know what this does yet 
	  ArrayList<Product> getList() {
		 return list;
	 }
	  
	 public int showIndex() {
		 return index;
	 }
	 
	 boolean inList() {
		 return existing; 
	 }

	public int size() {
		return list.size();
	}
	
	Product getProduct(){
		return Product;
	}
	
	public static boolean isValidDate(String inDate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public boolean isValidString(String string) {
	      string = string.toLowerCase();
	      char[] arrayOfChars = string.toCharArray();
	      for (int i = 0; i < arrayOfChars.length; i++) {
	         char ch = arrayOfChars[i];
	         if (!(ch >= 'a' && ch <= 'z')) {
	            return false;
	         }
	      }
	      return true;
	   }


}

