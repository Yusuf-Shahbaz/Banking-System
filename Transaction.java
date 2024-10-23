import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
 
public class Transaction {
    // Private fields to store transaction details
    private String transactionId;
    private Date transactionDate;
    private String accountNumber;
    private double amount;
    private String type;
 
    // Constructor to initialize transaction details
    public Transaction(String accountNumber, double amount, String type) {
        // Generate unique transaction ID
        this.transactionId = generateTransactionId();
        // Set current date and time for transaction
        this.transactionDate = new Date();
        // Store account number, amount, and type
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
    }
 
    // Private method to generate unique transaction ID
    private String generateTransactionId() {
        // Use UUID for unique ID generation
        return "TXN-" + UUID.randomUUID().toString();
    }
 
    // Getters for transaction details
    public String getTransactionId() {
        return transactionId;
    }
 
    public Date getTransactionDate() {
        return transactionDate;
    }
 
    public String getAccountNumber() {
        return accountNumber;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public String getType() {
        return type;
    }
 
    // Method to format transaction date
    public String formatTransactionDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(transactionDate);
    }
 
    // toString method for transaction details
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionDate=" + formatTransactionDate() +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
    public Transaction (String transactionId, String accountNumber, double amount, String type){
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this. type= type;
    }
}

