package Digital_Wallet_System;

public class User {
	private String userId;
    private String name;
    private String email;
    private Wallet wallet;
    
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    
    // Getters and setters (Encapsulation)
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Wallet getWallet() { return wallet; }
    public void setWallet(Wallet wallet) { this.wallet = wallet; }
    
    @Override
    public String toString() {
        return "User{id='" + userId + "', name='" + name + "', email='" + email + "'}";
    }
}
