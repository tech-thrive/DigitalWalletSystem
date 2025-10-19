package Digital_Wallet_System;
import java.util.*;

public class DigitalWalletSystem {
	 private static Scanner scanner = new Scanner(System.in);
	    private static Map<String, User> users = new HashMap<>();
	    private static Map<String, Wallet> wallets = new HashMap<>();
	    private static List<Payment> payments = new ArrayList<>();
	    private static int transactionCounter = 1;
	    
	    public static void main(String[] args) {
	        System.out.println("=== Digital Wallet System ===");
	        System.out.println("Welcome to your Digital Wallet Management System!");
	        
	        // Pre-populate with some sample data for testing
	        initializeSampleData();
	        
	        while (true) {
	            showMainMenu();
	            int choice = getIntInput("Enter your choice (1-8): ");
	            
	            switch (choice) {
	                case 1: addUser(); break;
	                case 2: createWallet(); break;
	                case 3: topUpWallet(); break;
	                case 4: transferMoney(); break;
	                case 5: payMerchant(); break;
	                case 6: processRefund(); break;
	                case 7: viewStatement(); break;
	                case 8: 
	                    System.out.println("Thank you for using Digital Wallet System!");
	                    System.out.println("Goodbye! 👋");
	                    return;
	                default: 
	                    System.out.println("❌ Invalid choice! Please enter a number between 1-8.");
	            }
	            
	            System.out.println("\nPress Enter to continue...");
	            scanner.nextLine();
	        }
	    }
	    
	    private static void initializeSampleData() {
	        // Create sample users and wallets for testing
	        User user1 = new User("U001", "DHEERAN", "dheeran@email.com");
	        User user2 = new User("U002", "SAHITH", "sahith@email.com");
	        
	        Wallet wallet1 = new Wallet("W001", user1);
	        Wallet wallet2 = new Wallet("W002", user2);
	        
	        user1.setWallet(wallet1);
	        user2.setWallet(wallet2);
	        
	        users.put("U001", user1);
	        users.put("U002", user2);
	        wallets.put("W001", wallet1);
	        wallets.put("W002", wallet2);
	        
	        System.out.println("Sample data initialized with 2 users and wallets.");
	    }
	    
	    private static void showMainMenu() {
	        System.out.println("\n" + "=".repeat(50));
	        System.out.println("           DIGITAL WALLET MAIN MENU");
	        System.out.println("=".repeat(50));
	        System.out.println("1. Add New User");
	        System.out.println("2. Create Wallet");
	        System.out.println("3. Top-up Wallet");
	        System.out.println("4. Transfer Money");
	        System.out.println("5. Pay Merchant");
	        System.out.println("6. Process Refund");
	        System.out.println("7. View Statement");
	        System.out.println("8. Exit System");
	        System.out.println("-".repeat(50));
	    }
	    
	    private static void addUser() {
	        System.out.println("\n--- Add New User ---");
	        String userId = getStringInput("Enter User ID: ");
	        
	        if (users.containsKey(userId)) {
	            System.out.println("Error: User ID already exists!");
	            return;
	        }
	        
	        String name = getStringInput("Enter Name: ");
	        String email = getStringInput("Enter Email: ");
	        
	        // Basic validation
	        if (userId.isEmpty() || name.isEmpty() || email.isEmpty()) {
	            System.out.println("Error: All fields are required!");
	            return;
	        }
	        
	        User user = new User(userId, name, email);
	        users.put(userId, user);
	        System.out.println("User added successfully!");
	        System.out.println("User Details: " + user);
	    }
	    
	    private static void createWallet() {
	        System.out.println("\n--- Create Wallet ---");
	        String userId = getStringInput("Enter User ID: ");
	        
	        User user = users.get(userId);
	        if (user == null) {
	            System.out.println("❌ Error: User not found! Please add user first.");
	            return;
	        }
	        
	        if (user.getWallet() != null) {
	            System.out.println("❌ Error: User already has a wallet!");
	            return;
	        }
	        
	        String walletId = "W" + System.currentTimeMillis();
	        Wallet wallet = new Wallet(walletId, user);
	        user.setWallet(wallet);
	        wallets.put(walletId, wallet);
	        
	        System.out.println("Wallet created successfully!");
	        System.out.println("Wallet ID: " + walletId);
	        System.out.println("For User: " + user.getName());
	        System.out.println("Initial Balance: $0.00");
	    }
	    
	    private static void topUpWallet() {
	        System.out.println("\n--- Top-up Wallet ---");
	        String walletId = getStringInput("Enter Wallet ID: ");
	        
	        Wallet wallet = wallets.get(walletId);
	        if (wallet == null) {
	            System.out.println("Error: Wallet not found!");
	            return;
	        }
	        
	        double amount = getDoubleInput("Enter top-up amount: $");
	        if (amount <= 0) {
	            System.out.println("Error: Amount must be positive!");
	            return;
	        }
	        
	        TopUp topUp = new TopUp("T" + transactionCounter++, amount, wallet);
	        if (topUp.execute()) {
	            System.out.println("Top-up successful!");
	            System.out.println(topUp.getReceipt());
	        } else {
	            System.out.println("Top-up failed: " + topUp.getStatus());
	        }
	    }
	    
	    private static void transferMoney() {
	        System.out.println("\n--- Transfer Money ---");
	        String fromWalletId = getStringInput("Enter your Wallet ID: ");
	        Wallet fromWallet = wallets.get(fromWalletId);
	        
	        if (fromWallet == null) {
	            System.out.println("Error: Source wallet not found!");
	            return;
	        }
	        
	        String toWalletId = getStringInput("Enter recipient Wallet ID: ");
	        Wallet toWallet = wallets.get(toWalletId);
	        
	        if (toWallet == null) {
	            System.out.println("Error: Destination wallet not found!");
	            return;	        }
	        
	        if (fromWalletId.equals(toWalletId)) {
	            System.out.println("Error: Cannot transfer to same wallet!");
	            return;
	        }
	        
	        System.out.println("From: " + fromWallet.getUser().getName());
	        System.out.println("To: " + toWallet.getUser().getName());
	        System.out.println("Current Balance: $" + fromWallet.getBalance());
	        
	        double amount = getDoubleInput("Enter transfer amount: $");
	        if (amount <= 0) {
	            System.out.println("Error: Amount must be positive!");
	            return;
	        }
	        
	        Transfer transfer = new Transfer("TR" + transactionCounter++, amount, fromWallet, toWallet);
	        if (transfer.execute()) {
	            System.out.println("Transfer successful!");
	            System.out.println(transfer.getReceipt());
	        } else {
	            System.out.println("Transfer failed: " + transfer.getStatus());
	        }
	    }
	    
	    private static void payMerchant() {
	        System.out.println("\n--- Pay Merchant ---");
	        String walletId = getStringInput("Enter your Wallet ID: ");
	        Wallet wallet = wallets.get(walletId);
	        
	        if (wallet == null) {
	            System.out.println("Error: Wallet not found!");
	            return;
	        }
	        
	        String merchant = getStringInput("Enter merchant name: ");
	        double amount = getDoubleInput("Enter payment amount: $");
	        
	        if (amount <= 0) {
	            System.out.println("Error: Amount must be positive!");
	            return;
	        }
	        
	        Payment payment = new Payment("P" + transactionCounter++, amount, wallet, merchant);
	        payments.add(payment);
	        
	        if (payment.execute()) {
	            System.out.println("Payment successful!");
	            System.out.println(payment.getReceipt());
	        } else {
	            System.out.println("Payment failed: " + payment.getStatus());
	        }
	    }
	    
	    private static void processRefund() {
	        System.out.println("\n--- Process Refund ---");
	        
	        if (payments.isEmpty()) {
	            System.out.println("No payments available for refund!");
	            return;
	        }
	        
	        // Show recent completed payments
	        System.out.println("Recent Completed Payments:");
	        System.out.println("-".repeat(60));
	        int validPaymentCount = 0;
	        List<Payment> validPayments = new ArrayList<>();
	        
	        for (int i = 0; i < payments.size(); i++) {
	            Payment p = payments.get(i);
	            if (p.getStatus().equals("COMPLETED")) {
	                System.out.printf("%d. %s | %s | $%.2f\n", 
	                    validPaymentCount + 1, p.getTransactionId(), p.getMerchant(), p.getAmount());
	                validPayments.add(p);
	                validPaymentCount++;
	            }
	        }
	        
	        if (validPaymentCount == 0) {
	            System.out.println("No completed payments available for refund!");
	            return;
	        }
	        
	        int paymentIndex = getIntInput("Select payment to refund (number): ") - 1;
	        if (paymentIndex < 0 || paymentIndex >= validPaymentCount) {
	            System.out.println("❌ Invalid payment selection!");
	            return;
	        }
	        
	        Payment originalPayment = validPayments.get(paymentIndex);
	        double maxRefund = originalPayment.getAmount();
	        double refundAmount = getDoubleInput("Enter refund amount (Max: $" + maxRefund + "): $");
	        
	        // Find the wallet that made this payment
	        Wallet wallet = findWalletByPayment(originalPayment);
	        if (wallet == null) {
	            System.out.println("Error: Could not find wallet for this payment!");
	            return;
	        }
	        
	        Refund refund = new Refund("R" + transactionCounter++, refundAmount, wallet, originalPayment);
	        
	        if (refund.execute()) {
	            System.out.println("Refund processed successfully!");
	            System.out.println(refund.getReceipt());
	        } else {
	            System.out.println("Refund failed: " + refund.getStatus());
	        }
	    }
	    
	    private static void viewStatement() {
	        System.out.println("\n--- View Statement ---");
	        String walletId = getStringInput("Enter Wallet ID: ");
	        
	        Wallet wallet = wallets.get(walletId);
	        if (wallet == null) {
	            System.out.println("Error: Wallet not found!");
	            return;
	        }
	        
	        List<StatementEntry> statement = wallet.getStatement();
	        if (statement.isEmpty()) {
	            System.out.println("No transactions found for this wallet!");
	            return;
	        }
	        
	        System.out.println("\n" + "=".repeat(80));
	        System.out.println("                      STATEMENT FOR WALLET: " + walletId);
	        System.out.println("=".repeat(80));
	        System.out.println("User: " + wallet.getUser().getName());
	        System.out.println("Current Balance: $" + String.format("%.2f", wallet.getBalance()));
	        System.out.println("-".repeat(80));
	        System.out.printf("%-20s %-12s %-25s %-10s %-12s\n", 
	                         "Date", "Entry ID", "Description", "Amount", "Balance After");
	        System.out.println("-".repeat(80));
	        
	        for (StatementEntry entry : statement) {
	            String amountStr = String.format("$%.2f", entry.getAmount());
	            String balanceStr = String.format("$%.2f", entry.getBalanceAfter());
	            System.out.printf("%-20s %-12s %-25s %-10s %-12s\n",
	                entry.getDate().toString().substring(0, 16),
	                entry.getEntryId(),
	                entry.getDescription().length() > 25 ? 
	                    entry.getDescription().substring(0, 22) + "..." : entry.getDescription(),
	                amountStr,
	                balanceStr);
	        }
	        System.out.println("=".repeat(80));
	    }
	    
	 // FIXED: Helper method to find wallet by payment
	    private static Wallet findWalletByPayment(Payment payment) {
	        // Direct approach - payment already has wallet reference
	        // Add a getWallet() method to Payment class first
	        try {
	            // Using reflection as a workaround since we don't have getWallet() in Payment
	            java.lang.reflect.Field walletField = Payment.class.getDeclaredField("wallet");
	            walletField.setAccessible(true);
	            return (Wallet) walletField.get(payment);
	        } catch (Exception e) {
	            // Fallback: search through all wallets
	            for (Wallet wallet : wallets.values()) {
	                for (StatementEntry entry : wallet.getStatement()) {
	                    if (entry.getDescription().contains("Payment to " + payment.getMerchant()) &&
	                        entry.getAmount() == -payment.getAmount()) {
	                        return wallet;
	                    }
	                }
	            }
	            return null;
	        }
	    }
	    
	    // Utility methods for input validation
	    private static String getStringInput(String prompt) {
	        System.out.print(prompt);
	        return scanner.nextLine().trim();
	    }
	    
	    private static int getIntInput(String prompt) {
	        while (true) {
	            try {
	                System.out.print(prompt);
	                return Integer.parseInt(scanner.nextLine().trim());
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input! Please enter a valid number.");
	            }
	        }
	    }
	    
	    private static double getDoubleInput(String prompt) {
	        while (true) {
	            try {
	                System.out.print(prompt);
	                return Double.parseDouble(scanner.nextLine().trim());
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input! Please enter a valid amount.");
	            }
	        }
	    }
}
