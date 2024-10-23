import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;
 
public class TransactionHandler extends Thread {
    // Private fields to store transaction and bank details
    private Transaction transaction;
    private Bank bank;
 
    // Constructor to initialize transaction handler
    public TransactionHandler(Transaction transaction, Bank bank) {
        this.transaction = transaction;
        this.bank = bank;
    }
 
    // Method to process transactions
    @Override
    public void run() {
        try {
            // Process transaction
            bank.processTransaction(transaction);
            // Log successful transaction
            Logger logger = Logger.getLogger(TransactionHandler.class.getName());
            logger.log(Level.INFO, "Transaction processed: {0}", transaction.toString());
        } catch (BankException e) {
            // Log failed transaction
            System.err.println("Transaction failed: " + e.getMessage());
        }
    }
 
    // Method to handle concurrent transactions
    public static void executeConcurrentTransactions(Bank bank, Transaction... transactions) {
        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            // Submit transactions for execution
            for (Transaction txn : transactions) {
                executor.submit(new TransactionHandler(txn, bank));
            }
            // Shut down the executor service
            executor.shutdown();
            // Wait for all transactions to complete
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            // Log interrupted exception
            System.err.println("Transaction execution interrupted: " + e.getMessage());
        }
    }
}

