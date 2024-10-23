import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
 
public class DatabaseHandler {
    private Connection connection;
 
    public DatabaseHandler() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String password = "password";
        connection = DriverManager.getConnection(url, user, password);
        Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
        logger.log(Level.INFO, "Database connection established");
    }
 
    public void createAccount(BankAccount account) throws SQLException {
        String query = "INSERT INTO accounts (accountNumber, balance, holder) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getAccountHolder());
            stmt.executeUpdate();
            Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
            logger.log(Level.INFO, "Account created: {0}", account.getAccountNumber());
        }
    }
 
    public BankAccount getAccount(String accountNumber) throws SQLException {
        String query = "SELECT * FROM accounts WHERE accountNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    BankAccount account = new BankAccount(
                            resultSet.getString("accountNumber"),
                            resultSet.getString("holder"),
                            resultSet.getDouble("balance"));
                    return account;
                }
            }
        }
        Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
        logger.log(Level.WARNING, "Account not found: {0}", accountNumber);
        return null;
    }
 
    public void updateAccount(BankAccount account) throws SQLException {
        String query = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getAccountNumber());
            stmt.executeUpdate();
            Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
            logger.log(Level.INFO, "Account updated: {0}", account.getAccountNumber());
        }
    }
 
    public void deleteAccount(String accountNumber) throws SQLException {
        String query = "DELETE FROM accounts WHERE accountNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            stmt.executeUpdate();
            Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
            logger.log(Level.INFO, "Account deleted: {0}", accountNumber);
        }
    }
 
    public void createTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (transactionId, accountNumber, amount, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getAccountNumber());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getType());
            stmt.executeUpdate();
            Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
            logger.log(Level.INFO, "Transaction created: {0}", transaction.getTransactionId());
        }
    }
 
    public List<Transaction> getTransactions(String accountNumber) throws SQLException {
        String query = "SELECT * FROM transactions WHERE accountNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            try (ResultSet resultSet = stmt.executeQuery()) {
                List<Transaction> transactions = new ArrayList<>();
                while (resultSet.next()) {
                    Transaction transaction = new Transaction(
                            resultSet.getString("transactionId"),
                            resultSet.getString("accountNumber"),
                            resultSet.getDouble("amount"),
                            resultSet.getString("type"));
                    transactions.add(transaction);
                }
                return transactions;
            }
        }
    }
}

