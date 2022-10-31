import java.util.Scanner;

public class BankApplication {
	static Bank b;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		b = new Bank();
//		b.addAccount("Bosse", 22);
//		b.addAccount("Nisse", 23);
		
		while (true) {
			meny();
			try {
				switch (scan.nextInt()) {
				case 1:
					// hitta konton tillhörande idNr xxx
					System.out.println("Ange ett id-nummer: ");
					long id = scan.nextLong();
					if (!b.findAccountsForHolder(id).isEmpty()) {
						b.findAccountsForHolder(id).forEach(System.out::println);
					} else {
						System.out.println("Finns tyvärr inga konton kopplade till detta id-nummer");
					}
					break;

				case 2:
					// Hitta kontoinnehavare genom namn eller del av namn
					System.out.println("Ange ett namn eller del av ett namn: ");
					scan.nextLine();
					String name1 = scan.nextLine();
					if (!b.findByPartofName(name1).isEmpty()) {
						b.findByPartofName(name1).forEach(System.out::println);
					} else {
						System.out.println("Finns tyvärr inga konton kopplade till detta namn");
					}
					break;

				case 3:
					// Sätt in pengar
					System.out.println("Ange önskat kontos kontonummer att sätta in pengar på: ");
					int accNr = scan.nextInt();
					System.out.println("Ange mängd som vill sättas in på kontot ");
					double deposit = scan.nextDouble();

					if (b.findByNumber(accNr) != null) {
						if (deposit >= 0) {
							b.findByNumber(accNr).deposit(deposit);
						} else {
							System.out.println("Du kan inte sätta in negativa värden");
						}
					} else {
						System.out.println("Kontot du försöker sätta in pengar på finns ej");
					}
					System.out.println("amount: " + b.findByNumber(accNr).getAmount());
					break;

				case 4:
					// Withdraw
					System.out.println("Ange önskat kontos kontonummer att ta ut pengar från: ");
					int accNr1 = scan.nextInt();
					if(b.findByNumber(accNr1) == null){
						System.out.println("Detta kontot finns inte");
						break;
					}
					System.out.println("Ange mängd som skall tas ut från kontot: ");
					double withdraw = scan.nextDouble();
					if(withdraw > 0) {
						if(b.findByNumber(accNr1).getAmount() >= withdraw){
							b.findByNumber(accNr1).withdraw(withdraw);
							System.out.println(b.findByNumber(accNr1).toString());
						} else {
							System.out.println("För lite på kontot, finns bara " + b.findByNumber(accNr1).getAmount() + " kr på kontot");
						}
					} else {
						System.out.println("Du kan inte ta ut negativa mängder");
					}

					break;

				case 5:
					// Transfer
					System.out.println("Ange önskat sändarkontos kontonummer: ");
					int sender = scan.nextInt();
					System.out.println("Ange önskat mottagarkontos kontonummer: ");
					int reciver = scan.nextInt();
					System.out.println("Ange önskad mängd att överföra: ");
					double amount = scan.nextDouble();
					if(amount < 0){
						System.out.println("Du kan inte överföra negativa mängder");
					}

					if (b.findByNumber(sender) != null && b.findByNumber(reciver) != null) {
						boolean Newchoice = false;

						while (!Newchoice) {
							if (b.findByNumber(sender).getAmount() < amount) {
								System.out.println("Din sändare har för lite pengar för denna transaktion");
								System.out.println(
										"Tryck 1 för att ange ny mängd att överföra eller tryck 2 för att återgå till huvudmenyn");

								switch (scan.nextInt()) {

								case 1:
									System.out.println("Ny mängd att överföra: ");
									amount = scan.nextDouble();
									break;

								case 2:
									Newchoice = true;
									break;
								}
							} else {
								b.findByNumber(sender).withdraw(amount);
								b.findByNumber(reciver).deposit(amount);
								break;
							}
						}

					} else {
						System.out.println("Kontot finns tyvärr inte");
					}

					break;
				case 6:
					// Add account
					System.out.println("Ange det fullständiga namnet på den nya kontoägaren: ");
					scan.nextLine();
					String name2 = scan.nextLine();
					System.out.println("Ange personens id-nummer: ");
					long id1 = scan.nextLong();

					b.addAccount(name2, id1);

					break;
				case 7:
					// Remove account
					System.out.println("Ange kontonumret på kontot som du önskar att ta bort: ");
					int accNr2 = scan.nextInt();

					if (b.findByNumber(accNr2) != null) {
						b.removeAccount(accNr2);
						System.out.println("Kontot " + accNr2 + " Togs bort");
					} else {
						System.out.println("Kontot: " + accNr2 + " Finns ej");
					}

					break;
				case 8:
					
					// Print all accounts
					System.out.println("--------------------------------------------");
					//b.getAllAccounts().forEach(System.out::println);
					b.getAllAccounts();
					for(BankAccount bankacc : b.getAllAccounts()){
						System.out.println(bankacc);
					}

					break;
				case 9:
					System.out.println("Programmet avslutas");
					System.exit(0);
					break;

				default:
					System.out.println("Finns bara 9 saker att göra i denna banken så välj en av dem");
					break;
				}
			} catch (Exception e) {
				System.out.println("Fel vid inmatning!");
				System.out.println("Typ av fel: " + e.getMessage());
			}
		}
	}

	private static void meny() {
		System.out.println("----------------------------------------------------------------------------------" + '\n'
				+ "1. Hitta konto utifrån innehavare" + '\n' + "2. Sök kontoinnehavare utifrån namn" + '\n'
				+ "3. Sätt in" + '\n' + "4. Ta ut" + '\n' + "5. Överföring" + '\n' + "6. Skapa konto" + '\n'
				+ "7. Ta bort konto" + '\n' + "8. Skriv ut konton" + '\n' + "9. Avsluta");
	}

}
