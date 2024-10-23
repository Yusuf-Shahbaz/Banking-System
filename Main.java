import java.util.logging.Logger;
import java.util.logging.Level;
 
public class Main {
    public static void main() {
        try {
            // Create a new Bank instance
            Bank bank = new Bank();
            
            // Create a new bank account with an initial balance of 500.0
            BankAccount account = bank.createAccount("John Doe", 500.0);
            
            // Create a deposit transaction for 100.0
            Transaction deposit = new Transaction(account.getAccountNumber(), 100.0, "Deposit");
            
            // Create a withdrawal transaction for 50.0
            Transaction withdrawal = new Transaction(account.getAccountNumber(), 50.0, "Withdrawal");
            
            // Execute the transactions concurrently
            TransactionHandler.executeConcurrentTransactions(bank, deposit, withdrawal);
        } catch (BankException e) {
            // Print any exceptions that occur during execution
            System.err.println(e.getMessage());
        }
        
        // Log the program's execution
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, "Program executed successfully");
    }
}

