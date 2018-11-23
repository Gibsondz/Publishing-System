package Users;

import Inventory.Inventory;
import java.util.Scanner;



public class UnregisteredBuyer {
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public UnregisteredBuyer()
    {
        wantsToRegister();
    }

    public void selectDocument() {
        System.out.println("Current Document Selection: ");
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
            //Include a payment system here
        }
        selectDocument();
    }

    private void wantsToRegister(){
        System.out.println("Would you like to register an account?\n Yes or No");
        String answer = input.nextLine();
        if(answer.equals("Yes")){
            //System.exit(1);
            registerAccount();
        }
    }

    private void registerAccount(){

    }

    private void populateInventory()
    {
        inventory = new Inventory();
    }
}
