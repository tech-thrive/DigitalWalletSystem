package Digital_Wallet_System;
import java.time.LocalDateTime;

public abstract class Transaction {
	protected String transactionId;
    protected double amount;
    protected LocalDateTime date;
    protected String status;
    
    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.status = "PENDING";
    }
    
    // Abstract method (Polymorphism)
    public abstract boolean execute();
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
    public String getStatus() { return status; }
    
    protected void setStatus(String status) { this.status = status; }

}
