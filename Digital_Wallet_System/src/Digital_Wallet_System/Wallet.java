package Digital_Wallet_System;
import java.time.LocalDateTime;
import java.util.*;

public class Wallet {
    private String walletId;
    private double balance;
    private User user;
    private List<StatementEntry> statement;
    
    public Wallet(String walletId, User user) {
        this.walletId = walletId;
        this.user = user;
        this.balance = 0.0;
        this.statement = new ArrayList<>();
    }
    
    // Getters
    public String getWalletId() { return walletId; }
    public double getBalance() { return balance; }
    public User getUser() { return user; }
    public List<StatementEntry> getStatement() { return statement; }
    
    // Business methods
    public void credit(double amount, String description) {
        this.balance += amount;
        addStatementEntry(description, amount, this.balance);
    }
    
    public boolean debit(double amount, String description) {
        if (amount <= balance) {
            this.balance -= amount;
            addStatementEntry(description, -amount, this.balance);
            return true;
        }
        return false;
    }
    
    private void addStatementEntry(String description, double amount, double balanceAfter) {
        StatementEntry entry = new StatementEntry(
            "SE" + System.currentTimeMillis() + statement.size(),
            LocalDateTime.now(),
            description,
            amount,
            balanceAfter
        );
        statement.add(entry);
    }
}