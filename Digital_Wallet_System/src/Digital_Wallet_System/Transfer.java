package Digital_Wallet_System;

public class Transfer extends Transaction {
	private Wallet fromWallet;
    private Wallet toWallet;
    
    public Transfer(String transactionId, double amount, Wallet fromWallet, Wallet toWallet) {
        super(transactionId, amount);
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
    }
    
    @Override
    public boolean execute() {
        if (amount <= 0) {
            setStatus("FAILED: Invalid amount");
            return false;
        }
        
        if (fromWallet.debit(amount, "Transfer to " + toWallet.getUser().getName())) {
            toWallet.credit(amount, "Transfer from " + fromWallet.getUser().getName());
            setStatus("COMPLETED");
            return true;
        } else {
            setStatus("FAILED: Insufficient funds");
            return false;
        }
    }
    
    public String getReceipt() {
        return String.format("Transfer Receipt:\nID: %s\nAmount: $%.2f\nFrom: %s\nTo: %s\nStatus: %s",
                transactionId, amount, fromWallet.getUser().getName(), 
                toWallet.getUser().getName(), status);
    }

}
