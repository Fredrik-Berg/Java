import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accountlist;

	/** Skapar en ny bank utan konton. */

	public Bank() {
		accountlist = new ArrayList<BankAccount>();

	}

	/**
	 * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare med
	 * de givna uppgifterna ska inte en ny Customer skapas, utan istället den
	 * befintliga användas. Det nya kontonumret returneras.
	 */

	public int addAccount(String holderName, long idNr) {
		Customer controlcustomer = this.findHolder(idNr);
		if (controlcustomer == null) {
			BankAccount nyttKonto = new BankAccount(holderName, idNr);
			accountlist.add(nyttKonto);
			return nyttKonto.getAccountNumber();
		}
		BankAccount nyKonto = new BankAccount(controlcustomer);
		accountlist.add(nyKonto);
		return nyKonto.getAccountNumber();
	}

	/**
	 * Returnerar den kontoinnehavaren som har det givna id-numret, eller null
	 * om ingen sådan finns.
	 */

	public Customer findHolder(long idNr) {
		for (BankAccount bankacc : accountlist) {
			if (bankacc.getHolder().getIdNr() == idNr) {
				return bankacc.getHolder();
			}
		}
		return null;

	}

	/**
	 * Tar bort konto med nummer ’number’ från banken. Returnerar true om kontot
	 * fanns (och kunde tas bort), annars false.
	 */

	public boolean removeAccount(int number) {
		for (BankAccount bankacc : accountlist) {
			if (bankacc.getAccountNumber() == number) {
				accountlist.remove(bankacc);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returnerar en lista innehållande samtliga bankkonton i banken. Listan är
	 * sorterad på kontoinnehavarnas namn.
	 */

	public ArrayList<BankAccount> getAllAccounts() {
		ArrayList<BankAccount> sortedlist = new ArrayList<>();
		for (BankAccount bankacc : accountlist) {
			sortedlist.add(bankacc);
		}
		for (int i = 0; (i <= sortedlist.size()); i++) {
			for (int j = 0; (j <= sortedlist.size()); j++) {
				if (sortedlist.size() > j + 1) {
					if (sortedlist.get(j).getHolder().getName().compareToIgnoreCase(sortedlist.get(j + 1).getHolder().getName()) > 0) {
						sortedlist.set(j + 1, sortedlist.set(j, sortedlist.get(j + 1)));
					}
				}
			}

		}
		return sortedlist;
	}

	/**
	 * Söker upp och returnerar bankkontot med kontonummer ’accountNumber’.
	 * Returnerar null om inget sådant konto finns.
	 */

	public BankAccount findByNumber(int accountNumber) {
		for (BankAccount bankacc : accountlist) {
			if (bankacc.getAccountNumber() == accountNumber) {
				return bankacc;
			}
		}
		return null;

	}

	/**
	 * Söker upp alla bankkonton som innehas av kunden med id-nummer ’idNr’.
	 * Kontona returneras i en lista. Kunderna antas ha unika id-nummer.
	 */

	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> templist = new ArrayList<>();
		for (BankAccount bankacc : accountlist) {
			if (bankacc.getHolder().getIdNr() == idNr) {
				templist.add(bankacc);
			}
		}
		return templist;
	}

	/**
	 * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla
	 * personer vars namn innehåller strängen ’namePart’ inkluderas i
	 * resultatet, som returneras som en lista. Samma person kan förekomma flera
	 * gånger i resultatet. Sökningen är "case insensitive", det vill säga gör
	 * ingen skillnad på stora och små bokstäver.
	 */

	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> foundpartnamelist = new ArrayList<>();
		for (BankAccount bankacc : accountlist) {
			if (bankacc.getHolder().getName().toLowerCase().contains(namePart.toLowerCase())) {
				foundpartnamelist.add(bankacc.getHolder());
			}
		}
		return foundpartnamelist;
	}
}
