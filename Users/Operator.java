package Users;

import Inventory.Inventory;
import Login.Account;

public class Operator {
	private Account user;
	private Inventory inventory;
	
	public Operator(Account user)
	{
		this.user = user;
		System.out.println("Current Document Selection: ");
		populateInventory();
	}
	
	private void populateInventory()
	{
		inventory = new Inventory();
	}

}
