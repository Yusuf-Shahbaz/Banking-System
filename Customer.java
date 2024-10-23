import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
 
public class Customer {
    // Private fields to store customer information
    private String name;
    private String email;
    private String phoneNumber;
    private String encryptedSSN;
 
    // Constructor to initialize customer details
    public Customer(String name, String email, String phoneNumber, String ssn) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        // Encrypt SSN before storing
        this.encryptedSSN = encryptSSN(ssn);
    }
 
    // Private method to encrypt SSN
    private String encryptSSN(String ssn) {
        // Implement simple encryption logic (or use existing libraries)
        try {
            // Use SHA-256 encryption
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(ssn.getBytes(StandardCharsets.UTF_8));
            // Convert to hexadecimal string
            String encryptedSSN = String.format("%064x", new BigInteger(1, hashBytes));
            return encryptedSSN;
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return ssn; // Placeholder for encryption
        }
    }
 
    // Getters and setters
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    // Additional method to validate email address
    public boolean validateEmail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+@(?:\\." +
                "[a-zA-Z0-9-]+)+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
 
    // Additional method to validate phone number
    public boolean validatePhoneNumber() {
        String phoneRegex = "^\\d{3}-\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}

