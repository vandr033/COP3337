
public class Product {
	//name, manufacturer name, manufacturer adress, quantity, unit price 
	private String productName;
	private double quantity;
	private double unitPrice;
	private String date;
	Manufacturer manu;
	
	
	Product(String name, int quant, double price, String d, Manufacturer m)
	{
	productName = name;
	quantity = quant;
	unitPrice = price;
	date = d;
	manu = m;
	}
	
	String getProdName() {
		return productName;
	}
	double getQuantity() {
		return quantity;
	}
	
	double getunitPrice() {
		return unitPrice;
	}
	
	String getDate() {
		return date;
	}
	
	Manufacturer getManu() {
		return manu;
	}
public void updatePrice(double newPrice) {
		
		unitPrice = newPrice;
		
	}
	public void updateQuantity(int newQuanity) {
		
		quantity = newQuanity;
		
	}
	
		
	
}
