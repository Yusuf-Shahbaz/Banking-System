
import java.util.logging.Logger;
import java.util.logging.Level;
import java.text.NumberFormat;
import java.util.Currency;
 
public class BankAccount {
    // Private fields to store account information
    private double balance;
    private String accountNumber;
    private String accountHolder;
 
    // Constructor to initialize account details
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        // Ensure initial balance is not negative
        this.balance = Math.max(0, initialBalance);
    }
 
    // Synchronized method to deposit funds
    public synchronized void deposit(double amount) throws BankException {
        // Validate deposit amount
        if (amount <= 0) {
            throw new BankException("Deposit amount must be positive");
        }
        // Update balance
        balance += amount;
        // Log transaction
        logTransaction("Deposit", amount);
    }
 
    // Synchronized method to withdraw funds
    public synchronized void withdraw(double amount) throws BankException {
        // Check sufficient funds
        if (amount > balance) {
            throw new BankException("Insufficient funds");
        }
        // Update balance
        balance -= amount;
        // Log transaction
        logTransaction("Withdrawal", amount);
    }
 
    // Method to retrieve current balance
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }
    
    public String getAccountHolder(){
        return accountHolder;
    }
 
    // Private method to log transactions
    private void logTransaction(String type, double amount) {
        // Implement logging logic to track all transactions
        Logger logger = Logger.getLogger(BankAccount.class.getName());
        logger.log(Level.INFO, "{0} of {1} processed.", new Object[]{type, NumberFormat.getCurrencyInstance().format(amount)});
    }
 
    // Method to apply interest (example of future scalability)
    public void applyInterest(double rate) {
        balance += balance * rate;
    }
}

