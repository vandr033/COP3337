
public class Address {
	private String str;
	private String city;
	private String zip;
	private String state;
	
	Address(String street, String cty, String zipCode, String State)
	{
		str= street;
		city = cty;
		zip = zipCode;
		state = State;
	}
	
	String getZip() {
		return state;
	}
	
}
