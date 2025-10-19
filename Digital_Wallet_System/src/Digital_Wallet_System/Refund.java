package Digital_Wallet_System;

public class Refund extends Transaction{
	private Wallet wallet;
    private Payment originalPayment;
    
    public Refund(String transactionId, double amount, Wallet wallet, Payment originalPayment) {
        super(transactionId, amount);
        this.wallet = wallet;
        this.originalPayment = originalPayment;
    }
    
    @Override
    public boolean execute() {
        if (amount <= 0 || amount > originalPayment.getAmount()) {
            setStatus("FAILED: Invalid refund amount");
            return false;
        }
        
        wallet.credit(amount, "Refund for payment: " + originalPayment.getTransactionId());
        setStatus("COMPLETED");
        return true;
    }
    
    public String getReceipt() {
        return String.format("Refund Receipt:\nID: %s\nAmount: $%.2f\nStatus: %s\nNew Balance: $%.2f",
                transactionId, amount, status, wallet.getBalance());
    }

}
