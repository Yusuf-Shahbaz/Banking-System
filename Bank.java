import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;
 
public class Bank {
    // Private field to store accounts
    private Map<String, BankAccount> accounts = new HashMap<>();
 
    // Method to create a new bank account
    public BankAccount createAccount(String accountHolder, double initialDeposit) throws BankException {
        // Generate unique account number
        String accountNumber = "ACC-" + UUID.randomUUID().toString();
        // Create new bank account object
        BankAccount account = new BankAccount(accountNumber, accountHolder, initialDeposit);
        // Store account in the map
        accounts.put(accountNumber, account);
        return account;
    }
 
    // Method to retrieve a bank account by account number
    public BankAccount getAccount(String accountNumber) throws BankException {
        // Check if account exists
        if (!accounts.containsKey(accountNumber)) {
            throw new BankException("Account not found");
        }
        return accounts.get(accountNumber);
    }
 
    // Method to process a transaction
    public void processTransaction(Transaction transaction) throws BankException {
        // Retrieve the account
        BankAccount account = getAccount(transaction.getAccountNumber());
        // Process the transaction based on the type
        switch (transaction.getType()) {
            case "Deposit":
                account.deposit(transaction.getAmount());
                break;
            case "Withdrawal":
                account.withdraw(transaction.getAmount());
                break;
            // Add more cases for other transaction types
        }
        // Log the transaction
        Logger logger = Logger.getLogger(Bank.class.getName());
        logger.log(Level.INFO, "Transaction processed: {0}", transaction.toString());
    }
 
    // Additional method to get all accounts
    public Map<String, BankAccount> getAllAccounts() {
        return accounts;
    }
 
    // Additional method to format dates
    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}

