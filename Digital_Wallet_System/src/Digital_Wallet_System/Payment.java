package Digital_Wallet_System;

public class Payment extends Transaction {
    private Wallet wallet;
    private String merchant;
    
    public Payment(String transactionId, double amount, Wallet wallet, String merchant) {
        super(transactionId, amount);
        this.wallet = wallet;
        this.merchant = merchant;
    }
    
    @Override
    public boolean execute() {
        if (amount <= 0) {
            setStatus("FAILED: Invalid amount");
            return false;
        }
        
        if (wallet == null) {
            setStatus("FAILED: Wallet not found");
            return false;
        }
        
        if (wallet.debit(amount, "Payment to " + merchant)) {
            setStatus("COMPLETED");
            return true;
        } else {
            setStatus("FAILED: Insufficient funds");
            return false;
        }
    }
    
    public String getReceipt() {
        double currentBalance = (wallet != null) ? wallet.getBalance() : 0.0;
        return String.format("Payment Receipt:\nID: %s\nAmount: $%.2f\nMerchant: %s\nStatus: %s\nBalance: $%.2f",
                transactionId, amount, merchant, status, currentBalance);
    }
    
    public String getMerchant() { 
        return merchant; 
    }
    
    // Getter for wallet (useful for refund processing)
    public Wallet getWallet() {
        return wallet;
    }
}