

public class BankAccount {
	
	private static int latestaccNbr = 10000;
	private Customer Accountowner;
	private double Amount;
	private int accountNr;

	/**
	 * Skapar ett nytt bankkonto åt en innehavare med namn ’holderName’ och id
	 * ’holderId’. Kontot tilldelas ett unikt kontonummer och innehåller
	 * inledningsvis 0 kr.
	 */

	public BankAccount(String holderName, long holderId) {
		this.Accountowner = new Customer(holderName, holderId);
		this.accountNr = ++latestaccNbr;
		this.Amount = 0;
		
	}

	/**
	 * Skapar ett nytt bankkonto med innehavare ’holder’. Kontot tilldelas ett
	 * unikt kontonummer och innehåller inledningsvis 0 kr.
	 */

	public BankAccount(Customer holder) {
		this.Accountowner = holder;
		this.accountNr = ++latestaccNbr;
		this.Amount = 0;
	}

	/** Tar reda på kontots innehavare. */

	public Customer getHolder() {
		return Accountowner;
	}

	/** Tar reda på det kontonummer som identifierar detta konto. */
	public int getAccountNumber() {
		return accountNr;
	}

	/** Tar reda på hur mycket pengar som finns på kontot. */

	public double getAmount() {
		return Amount;
	}

	/** Sätter in beloppet ’amount’ på kontot. */

	public void deposit(double amount) {
		this.Amount = this.Amount + amount;
	}

	/**
	 * Tar ut beloppet ’amount’ från kontot. Om kontot saknar täckning blir
	 * saldot negativt.
	 */

	public void withdraw(double amount) {
		this.Amount = this.Amount - amount;
	}

	/** Returnerar en strängrepresentation av bankkontot. */

	public String toString() {
		return " Konto " + getAccountNumber() + Accountowner.toString() + ": " + getAmount();
	}
	
}

