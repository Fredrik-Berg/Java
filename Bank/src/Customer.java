
public class Customer {
	
	private String name;
	private long idNr;
	private int uid;
	private static int latestuid = 1000;

	
	public Customer(String name, long idNr){
	this.name = name;
	this.idNr = idNr;
	this.uid = ++latestuid; 
	
	}
	/**
	 * 
	 * @return the Customers name
	 */
	public String getName(){
		return name;
	}
	
	/** Tar reda på kundens personnummer. */
	
	 public long getIdNr(){
		 return idNr;
	 }
	 
	/** Tar reda på kundens kundnummer. */
	 
	public int getCustomerNr(){
		return uid;
	}
	
	/** Returnerar en strängbeskrivning av kunden. */
		
	public String toString() {
		return " ("+ getName() + ", id: " + getIdNr() + ", kundnr: " + getCustomerNr() + ")";
	}
	
}
