# Digital Wallet System

## Problem Statement
Design and implement a Java console application for a Digital Wallet system that manages users, wallets, top-ups, transfers, payments, and statements. The application demonstrates object-oriented principles and ensures accurate balances.

### Class Requirements:
1. User
2. Wallet  
3. TopUp
4. Transfer
5. Payment
6. Refund
7. StatementEntry

### Business Rules:
1. Top-ups increase wallet balance after confirmation
2. Transfers require sufficient balance and update both source and destination
3. Payments to merchants reduce balance and create statement entries
4. Refunds credit the wallet against a valid payment
5. Every transaction must generate a statement entry

### Console Interface Requirements:
1. Menu-driven program with options for all operations
2. Input validations for all user entries
3. Encapsulation for all attributes

## Student Information
- **Name:** LINGESHKUMAR G
- **Roll No:** 717824P229
- **Course:** Java Programming
- 
## How to Compile and Run

### Method 1: Using Command Line

1. **Navigate to the src directory:**
   
   cd Digital-Wallet-System/src
   
2.**Compile all Java files:**

  javac Digital_Wallet_System/*.java
3.**Run the application:**

  java Digital_Wallet_System.DigitalWalletSystem

### Method 2 : Using an IDE
1.Import the project:

Open your IDE
Import existing project
Select the Digital-Wallet-System folder

2.Set up source folder:

Mark src as source directory
Ensure all Java files are in the Digital_Wallet_System package

3.Run the application:

Right-click on DigitalWalletSystem.java
Select "Run Java"

