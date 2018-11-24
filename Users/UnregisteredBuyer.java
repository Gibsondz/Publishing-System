package Users;

import Inventory.Inventory;
import java.util.Scanner;
import Login.LoginServer;



public class UnregisteredBuyer {
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public UnregisteredBuyer(LoginServer login)
    {
        wantsToRegister(login);
    }

    public void selectDocument() {
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
            System.out.print("Price: $");
            System.out.println(inventory.getInventory().get(docIndex).getPrice());
            System.out.println(" Yes or No?");
            String purchase = input.nextLine();
            if(purchase.equals("Yes")){
                paymentSystem(docIndex);
            }
        }
        selectDocument();
    }

    private void paymentSystem(int docIndex){
        System.out.println("\n Please enter payment information");
        String paymentinfo = input.nextLine();
        System.out.println("Your order has been accepted, Thank you for using our service\n");
    }

    private void wantsToRegister(LoginServer login){
        System.out.println("Would you like to register an account and gain access to our promotions?\n Yes or No");
        String answer = input.nextLine();
        if(answer.equals("Yes")){
            registerAccount(login);
        }
    }

    private void registerAccount(LoginServer login){
        System.out.println("Please enter a username");
        String user = input.nextLine();
        System.out.println("Please enter a password");
        String pass = input.nextLine();
        System.out.println("Thank you for registering, to access your account restart and login");
        login.createAccount(user,pass,"B");

    }

    private void populateInventory()
    {
        inventory = new Inventory();
    }
}
