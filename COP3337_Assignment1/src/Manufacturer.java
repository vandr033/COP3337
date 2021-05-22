
public class Manufacturer {
	private String manuName;
	Address addr;
	
	Manufacturer(String companyName, Address a){
		manuName = companyName;
		addr = a;
		
	}
	
	String getManuName() {
		return manuName;
	}

	public Address getAddress() {
		return addr;
	}

}
