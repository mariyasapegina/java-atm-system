import java.util.Random;
import java.util.Scanner;


public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        //prompt user for account creation
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();


        System.out.print("Enter your starting credit card balance: ");
        double creditBalance = scanner.nextDouble();


        System.out.print("Enter your starting savings balance: ");
        double savingsBalance = scanner.nextDouble();


        System.out.print("Enter your starting checking balance: ");
        double checkingBalance = scanner.nextDouble();


        System.out.print("Enter your credit card APR: ");
        double apr = scanner.nextDouble();


        System.out.print("Enter your checking overdraft fee: ");
        double overdraftFee = scanner.nextDouble();


        System.out.print("Enter your savings interest rate: ");
        double interestRate = scanner.nextDouble();


        // Create accounts
        Credit creditAccount = new Credit(name, creditBalance, apr);
        Savings savingsAccount = new Savings(name, savingsBalance, interestRate);
        Checking checkingAccount = new Checking(name, checkingBalance, overdraftFee);


        // Display account information
        System.out.println("\nAccount Created!");
        System.out.println(creditAccount);
        System.out.println(savingsAccount);
        System.out.println(checkingAccount);


        // Main menu 
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Credit Card");
            System.out.println("2. Checking");
            System.out.println("3. Savings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            if (option == 1) {
                creditCardMenu(scanner, creditAccount);
            } else if (option == 2) {
                checkingMenu(scanner, checkingAccount);
            } else if (option == 3) {
                    savingsMenu(scanner, savingsAccount);
            } else if (option == 4) {
                break;
            }
        }
        scanner.close();
    }


    // Credit Card Menu
    private static void creditCardMenu(Scanner scanner, Credit creditAccount) {
        while (true) {
            System.out.println("\nCredit Card Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Make Purchase");
            System.out.println("3. Display Monthly Statement");
            System.out.println("4. Create Monthly Payments");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            if (option == 1) {
                System.out.println("Balance: $" + creditAccount.getBalance());
            } else if (option == 2) {
                System.out.print("Enter amount to purchase: ");
                double purchaseAmount = scanner.nextDouble();
                creditAccount.makePurchase(purchaseAmount);
            } else if (option == 3) {
                creditAccount.monthlyStatement();
            } else if (option == 4) {
                System.out.print("Enter number of months to pay off balance: ");
                int months = scanner.nextInt();
                double payment = creditAccount.monthsToPayOff(months);
                System.out.println("Monthly Payment: $" + payment);
            } else if (option == 5) {
                break;
            }
        }
    }


    // Checking Menu
    private static void checkingMenu(Scanner scanner, Checking checkingAccount) {
        while (true) {
            System.out.println("\nChecking Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            if (option == 1) {
                System.out.println("Balance: $" + checkingAccount.getBalance());
            } else if (option == 2) {
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                checkingAccount.withdraw(withdrawAmount);
            } else if (option == 3) {
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                checkingAccount.deposit(depositAmount);
            } else if (option == 4) {
                break;
            }
        }
    }


    // Savings Menu
    private static void savingsMenu(Scanner scanner, Savings savingsAccount) {
        while (true) {
            System.out.println("\nSavings Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Display Months until Goal");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            if (option == 1) {
                System.out.println("Balance: $" + savingsAccount.getBalance());
            } else if (option == 2) {
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                savingsAccount.withdraw(withdrawAmount);
            } else if (option == 3) {
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                savingsAccount.deposit(depositAmount);
            } else if (option == 4) {
                System.out.print("Enter goal amount: ");
                double goalAmount = scanner.nextDouble();
                double months = savingsAccount.amountInAccount(goalAmount);
                System.out.println("Months to reach goal: " + months);
            } else if (option == 5) {
                break;
            }
        }
    }
}


class Account {
private int accountId;
private double balance;


//assigns a random account ID and sets the balance to 0
    public Account() {
        Random rand = new Random();
        this.accountId = rand.nextInt(99999) + 1;  
        this.balance = 0.0;
    }


//assigns a random account ID and sets the balance to initial value
    public Account(double initialBalance) {
        Random rand = new Random();
        this.accountId = rand.nextInt(99999) + 1;  
        this.balance = initialBalance;
    }


    // Accessor method 
    public int getAccountId() {
        return accountId;
    }


    // Mutator method 
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    // Accessor method 
    public double getBalance() {
        return balance;
    }


    // Mutator method 
    public void setBalance(double balance) {
        this.balance = balance;
    }


    // toString method - returns a string of the account details
    @Override
    public String toString() {
        return "Account ID: " + accountId + ", Balance: $" + balance;
    }
}
//Bank class
class Bank extends Account {
    private String name;


    public Bank() {
        super();  
        this.name = "unknown";
    }


    public Bank(String name, double initialBalance) {
        super(initialBalance);  
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        return super.toString() + "\nAccount Holder: " + name;
    }


    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }


    public void withdraw(double amount) {
        setBalance(getBalance() - amount);
    }
}
class Credit extends Bank {
    private double apr;


    public Credit() {
        super();  
        this.apr = 0.0;
    }


    public Credit(String name, double initialBalance, double apr) {
        super(name, initialBalance);  
        this.apr = apr;
    }


    public double getApr() {
        return apr;
    }


    public void setApr(double apr) {
        this.apr = apr;
    }


    public void makePurchase(double amount) {
        setBalance(getBalance() + amount);
    }


    public void monthlyStatement() {
        double balance = getBalance();
        double interest = balance * apr / 12;
        System.out.println("Balance if paid on time: $" + balance);
        System.out.println("Balance if not paid (with interest): $" + (balance + interest));
    }


    public double monthsToPayOff(int months) {
        double balance = getBalance();
        double r = apr / 12;
        double A = balance * (r * Math.pow(1 + r, months)) / (Math.pow(1 + r, months) - 1);
        return A;
    }


    public String toString() {
        return super.toString() + "\nAPR: " + apr + "%";
    }
}
class Checking extends Bank {
    private double overdraftFee;


    public Checking() {
        super();  
        this.overdraftFee = 0.0;
    }


    public Checking(String name, double initialBalance, double overdraftFee) {
        super(name, initialBalance);  
        this.overdraftFee = overdraftFee;
    }


    public double getOverdraftFee() {
        return overdraftFee;
    }


    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }


    public void withdraw(double amount) {
        if (getBalance() - amount < 0) {
            setBalance(getBalance() - overdraftFee);  // apply overdraft fee
        }
        super.withdraw(amount);
    }


    public String toString() {
        return super.toString() + "\nOverdraft Fee: $" + overdraftFee;
    }
}
class Savings extends Bank {
    private double interestRate;


    public Savings() {
        super();  
        this.interestRate = 0.0;
    }


    public Savings(String name, double initialBalance, double interestRate) {
        super(name, initialBalance);  
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }


    public double amountInAccount(double goalAmount) {
        double b = getBalance();
        double r = interestRate;
        double t = Math.log(goalAmount / b) / (12 * Math.log(1 + r / 12));
        return Math.ceil(t);  // Round up to the next whole month
    }


    public void withdraw(double amount) {
        if (getBalance() - amount >= 0) {
            super.withdraw(amount);
        } else {
            System.out.println("Insufficient funds to withdraw " + amount);
        }
    }


    public String toString() {
        return super.toString() + "\nInterest Rate: " + interestRate + "%";
    }
}
