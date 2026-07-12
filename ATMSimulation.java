import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double balance = 10000;   // Initial balance
        int pin = 1234;             // Default PIN
        int attempts = 0;           // PIN attempts counter
        boolean accessGranted = false;

        System.out.println("===== Welcome to ATM =====");

        // PIN verification with max 3 attempts
        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            try {
                int enteredPin = sc.nextInt();
                if (enteredPin == pin) {
                    accessGranted = true;
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.next(); // Clear invalid input
            }
        }

        if (!accessGranted) {
            System.out.println("Access denied! Please try again later.");
            sc.close();
            return;
        }

        int choice = 0;

        // Menu-driven ATM operations
        do {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: Rs." + balance);
                    break;

                case 2:
                    double deposit;
                    do {
                        System.out.print("Enter deposit amount: ");
                        try {
                            deposit = sc.nextDouble();
                            if (deposit > 0) {
                                balance += deposit;
                                System.out.println("Deposit successful!");
                                break;
                            } else {
                                System.out.println("Amount must be positive. Try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Enter numbers only.");
                            sc.next();
                        }
                    } while (true);
                    break;

                case 3:
                    double withdraw;
                    do {
                        System.out.print("Enter withdrawal amount: ");
                        try {
                            withdraw = sc.nextDouble();
                            if (withdraw <= 0) {
                                System.out.println("Amount must be positive. Try again.");
                            } else if (withdraw > balance) {
                                System.out.println("Insufficient balance! Try again.");
                            } else {
                                balance -= withdraw;
                                System.out.println("Withdrawal successful!");
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Enter numbers only.");
                            sc.next();
                        }
                    } while (true);
                    break;

                case 4:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }

        } while (choice != 4);

        sc.close();
    }
}