import java.util.logging.Logger;
import java.util.logging.Level;
 
public class BankException extends Exception {
    /**
     * Constructs a new BankException with the specified message.
     *
     * @param message the detail message
     */
    public BankException(String message) {
        super(message);
        // Log the exception
        Logger logger = Logger.getLogger(BankException.class.getName());
        logger.log(Level.SEVERE, message);
    }
}

