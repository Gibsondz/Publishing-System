//RegisteredBuyer.java// 
package Users; 

import Inventory.Inventory;
import Users.Promotion;

import java.io.IOException;
import java.util.Scanner;
import Login.Account;
import Login.LoginServer; 

public class RegisteredBuyer extends UnregisteredBuyer implements ApplyPromotion  {
 	private Inventory inventory;
 	private Scanner input = new Scanner(System.in);
 	private Account User; 
 	private Promotion promotion; 
 	
 	private double DiscountPrice; 

 	//Constructor 
 	public RegisteredBuyer(LoginServer login, Account CurrentUser){
 		super(login);
 		User = CurrentUser;
 		populatePromotions();
 		try {
			ChangeAccount(login);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

 	//Selecting Document 
 	public void selectDocument(){
 		//Modify Evan's selectoDocument code 
 		promotion.displayPromotionList();
 		System.out.println("\nCurrent Document Selection: ");
        populateInventory();
        System.out.println("Please select a document by entering the documents title or enter EXIT to leave");
        String docname = input.nextLine();
        if(docname.equals("EXIT")) System.exit(1);

        int docIndex = inventory.searchInventory(docname);
        if (docIndex == -1){
            System.out.println("Im sorry we could not find the document you requested\n");
        } else {
            System.out.println("Would you like to purchase:");
            System.out.println(inventory.getInventory().get(docIndex).toString());
            System.out.print("Original Price: $");
            System.out.println(inventory.getInventory().get(docIndex).getPrice());
            //Applying promotion 
            System.out.print("Price after Discount: $ ");
            applyPromotion((inventory.getInventory().get(docIndex).getAuthor()),(Double.parseDouble(inventory.getInventory().get(docIndex).getPrice())));
            System.out.println(DiscountPrice);  
            System.out.println(" ");
            System.out.println(" Yes or No?");
            String purchase = input.nextLine();
            if(purchase.equals("Yes")){
                super.paymentSystem(docIndex);
            }
        }
        selectDocument();
 	}


 	//UnregisterAccount 
 	protected void ChangeAccount(LoginServer login) throws IOException{
 		System.out.println("Currently You Have Register Account Type would like to change this?\n Yes or No");
        String answer = input.nextLine();
        if(answer.equals("Yes")){
            UnregisterAccount(login);
        }
 	}
 	
 	protected void UnregisterAccount(LoginServer login) throws IOException{
 		//Three tries to put the correct password and username 
 		int numberOfTries = 0;
 		int triesleft = 2; 
 		//To Verify current user inorder to delete account
        Account verify;
        while(numberOfTries < 3 ){
     		System.out.println("Please enter your Password and Username to permanently delete Account: ");
     		System.out.println("Please enter a username");
            String user = input.nextLine();
            System.out.println("Please enter a password");
            String pass = input.nextLine();
             verify = login.validate(user, pass);
	        if(verify != null) {
	        	login.deleteAccount(User.getUsername(), User.getPassword(), User.getType());
	        	System.out.println("Thank you for Using Our Service, this account has been successfully deleted and the system will terminate");
	        	System.exit(0);
	        }else {
	        	numberOfTries++;
	        	System.out.println("Please try again:Invalid Password And UserName. " + "You have: "+ triesleft + " " + "more tries left");
	        	triesleft--;
	        	if(numberOfTries == 3 && (triesleft == -1)) {
	        		System.out.println("You Have Reached your limit the program will terminate now ");
		        	System.exit(0);
		        }
	        }
        }
 	}

 	 public void applyPromotion(String CheckAruthor, double orginalPrice){
 		 //applythediscount
 		double temp = promotion.getdiscount(CheckAruthor);
 		 if(temp == 0) {
 			DiscountPrice = orginalPrice - (((orginalPrice)*(WeeklyDiscount/100)));
 		 }else {
 			DiscountPrice = orginalPrice - (((orginalPrice)*(temp/100)));
 		 }
 	}
 	 
 	//Populate Promotions 
 	protected void populatePromotions() {
 		promotion = new Promotion(); 
 	}
 	 
 	//Populate Inventory 
 	protected void populateInventory(){
 		inventory = new Inventory();
 	}
 	

}