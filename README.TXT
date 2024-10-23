------------------------------------------------------------------------
Bank Transaction Management System
------------------------------------------------------------------------
 
   Overview
 
This Java project simulates a simple Bank Transaction Management System. The system allows a customer to interact with their bank account, performing various operations such as deposits, withdrawals, and transfers. It includes error handling for banking exceptions and stores information persistently using a database handler.
 
   Features
 
-   Customer Management  : Handles customer creation and management.
-   Bank Transactions  : Supports deposits, withdrawals, and transfers between accounts.
-   Exception Handling  : Includes robust error handling for common banking issues such as insufficient funds.
-   Database Integration  : Stores transaction records and customer data using a  DatabaseHandler .
 
   Classes
 
-   Main  : The entry point of the application where the system is initialized.
-   Bank  : Represents the bank which holds accounts and performs transactions.
-   Customer  : Represents a customer with a bank account.
-   Transaction  : A class that models transactions (deposits, withdrawals, transfers).
-   TransactionHandler  : Handles the logic behind processing and validating transactions.
-   DatabaseHandler  : Manages the interaction between the system and a database where records are stored.
-   BankAccount  : Represents a customer's account, stores balance, and manages deposit/withdrawal operations.
-   BankException  : Handles specific exceptions related to banking transactions, such as insufficient funds.