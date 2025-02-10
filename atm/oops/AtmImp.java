package atm.oops;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class AtmImp implements Atm{

	AtmModel atm= new AtmModel();
	
	Map<LocalDateTime, List<String>> hisAmt = new TreeMap<>();
	
	@Override
	public double viewBal() {
		if(atm.getBalance()>0) {
			System.out.println("you amount is :::: "+atm.getBalance());
		return atm.getBalance();
		}else {
			System.out.println("your amount is zero");
			return 0.0;
		}
	}

	@Override
	public double withdrawAmt(double withdrawAmount) {
		// check first balance should have before withdraw
		if(withdrawAmount<atm.getBalance()) {
			if(withdrawAmount%500==0) {// Ensure withdrawal is in multiples of 500 so any amount 200 and 300 can't be withdraw 
                atm.setBalance(atm.getBalance() - withdrawAmount);
				
                System.out.println("Your withdrawn amount is: " + withdrawAmount);
                
				   // Store transaction in history
                LocalDateTime today = LocalDateTime.now();
                hisAmt.computeIfAbsent(today, k -> new ArrayList<>()).add("Withdrawn: " + withdrawAmount);
                
			
			System.out.println("your withdraw amount is ::::  "+atm.getBalance());
			return atm.getDepositAmount();
			}
			else 
			{
				System.out.println("Enter the amount in terms of 500 you enter wrong :::: ");
				return withdrawAmount;
			}
		}else {
			System.out.println("Insufficient funds. :::: "+atm.getBalance());
			return atm.getBalance();	
		}
	}
	

	@Override
	public void depositAmt(double depositAmount) {
			if(depositAmount>0)
			{
			System.out.println("the amount you depositing is ::::"+depositAmount);
			
	        atm.setBalance(atm.getBalance()+depositAmount); // Fix: Add depositAmount instead of replacing
	       
	        // setting the data into the map for statement view
	        LocalDateTime now = LocalDateTime.now();
            hisAmt.computeIfAbsent(now, k -> new ArrayList<>()).add("Deposited: " + depositAmount);
			
            System.out.println("Remaining balance: " + atm.getBalance());
			}
			else 
			{
				System.out.println("Amount can't be nagative"+depositAmount);
			}
		}	
	

	@Override
	public void viewMiniStatement() {
        System.out.println("\nMini Statement:");
        for (Map.Entry<LocalDateTime, List<String>> entry : hisAmt.entrySet()) {
            for (String transaction : entry.getValue()) {
                System.out.println(entry.getKey() + " - " + transaction);
            }
}
}
}      
/*
Without computeIfAbsent (Old Way)

=============================================================================================
Before Java 8, you would write:
=============================================================================================
if (!hisAmt.containsKey(today)) {
    hisAmt.put(today, new ArrayList<>());  // Initialize if key doesn't exist
}
hisAmt.get(today).add("Withdrawn: " + WithdrawAmount); // Add transaction

This requires explicitly checking for the key and handling initialization.

=============================================================================================
With computeIfAbsent (New Way)
=============================================================================================
hisAmt.computeIfAbsent(today, k -> new ArrayList<>()).add("Withdrawn: " + WithdrawAmount);

    This eliminates manual checks and makes the code shorter and cleaner. 
    
 */
