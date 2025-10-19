package Digital_Wallet_System;

public class TopUp extends Transaction{
private Wallet wallet;
    
    public TopUp(String transactionId, double amount, Wallet wallet) {
        super(transactionId, amount);
        this.wallet = wallet;
    }
    
    @Override
    public boolean execute() {
        if (amount <= 0) {
            setStatus("FAILED: Invalid amount");
            return false;
        }
        
        wallet.credit(amount, "Top-Up: " + transactionId);
        setStatus("COMPLETED");
        return true;
    }
    
    public String getReceipt() {
        return String.format("TopUp Receipt:\nID: %s\nAmount: $%.2f\nStatus: %s\nNew Balance: $%.2f",
                transactionId, amount, status, wallet.getBalance());
    }

}
