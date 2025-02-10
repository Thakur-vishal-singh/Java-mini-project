package atm.oops;

import java.util.Scanner;

public class UserInterFace {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AtmImp imp = new AtmImp();
        
        // Default ATM details
        int atmNumber = 123456;
        int atmPin = 123;   

        // User authentication
        System.out.println("Enter The ATM Number:");
        int userNum = scan.nextInt();
        System.out.println("Enter the ATM Pin:");
        int userPin = scan.nextInt();

        // Validate user credentials
        if (userNum == atmNumber && userPin == atmPin) {
            while (true) {  // Keep looping until user chooses to exit
            	System.out.println("\n");
                System.out.println("\n=== ATM Menu ===");
                System.out.println("1. View Available Balance");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. View Mini Statement");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int select = scan.nextInt();

                switch (select) {
                    case 1:
                       imp.viewBal();
                        break;
                    case 2:
                        System.out.print("Enter the Withdraw Amount: ");
                        double withdrawAmount = scan.nextDouble();
                        imp.withdrawAmt(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the Deposit Amount: ");
                        double depositAmount = scan.nextDouble();
                        imp.depositAmt(depositAmount);
                        break;
                    case 4:
                        imp.viewMiniStatement();
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scan.close(); // Close scanner before exiting
                        System.exit(0); // Exit program
                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            }
        } else {
            System.out.println("Your PIN or ATM Number is incorrect.");
        }
        scan.close();
    }
}
