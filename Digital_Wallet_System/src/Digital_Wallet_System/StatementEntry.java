package Digital_Wallet_System;
import java.time.LocalDateTime;

public class StatementEntry {
	private String entryId;
    private LocalDateTime date;
    private String description;
    private double amount;
    private double balanceAfter;
    
    public StatementEntry(String entryId, LocalDateTime date, String description, 
                         double amount, double balanceAfter) {
        this.entryId = entryId;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }
    
    // Getters
    public String getEntryId() { return entryId; }
    public LocalDateTime getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    
    @Override
    public String toString() {
        return String.format("%s | %s | %s | $%.2f | $%.2f", 
                date, entryId, description, amount, balanceAfter);
    }

}
