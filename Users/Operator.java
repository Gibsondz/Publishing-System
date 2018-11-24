package Users;

import java.util.Scanner;

import Inventory.Document;
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
	
	public void run()
	{
		String menu = "Enter the number of what you would like to do:\n1: Add Document to Inventory\n2: Remove Document from Inventory\n3: Update a Document\n4: View Documents\n5: Logout";
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println(menu);
			String input = sc.nextLine();
			
			if(input.equals("1"))
			{
				addDocumentRoutine();
			}
			else if(input.equals("2"))
			{
				removeDocumentRoutine();
			}
			else if(input.equals("3"))
			{
				updateDocumentRoutine();
			}
			else if(input.equals("4"))
			{
				System.out.println("Documents in Inventory: ");
				inventory.notifyAllObservers();
			}
			else if(input.equals("5"))
			{
				break;
			}
			else
			{
				System.out.println("Invalid input please try again");
			}
		}
	}
	
	private void updateDocumentRoutine()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("Please enter document title you wish to edit:");
		input = sc.nextLine();
		String title = input;
		if(!inventory.checkExistance(input))
		{
			System.out.println("Invalid book title.");
			return;
		}
		
		System.out.println("What would you like to edit:\n1: Title\n2: Author\n3: ID\n4: Price");
		input = sc.nextLine();
		String edit = input;
		if(input.equals("1"))
		{
			System.out.println("What would you like to change the Title to?");
			input = sc.nextLine();
			inventory.EditDocument(title, edit, input);
		}
		else if(input.equals("2"))
		{
			System.out.println("What would you like to change the Author to?");
			input = sc.nextLine();
			inventory.EditDocument(title, edit, input);
		}
		else if(input.equals("3"))
		{
			System.out.println("What would you like to change the ID to?");
			input = sc.nextLine();
			inventory.EditDocument(title, edit, input);
		}
		else if(input.equals("4"))
		{
			System.out.println("What would you like to change the Price to?");
			input = sc.nextLine();
			if(!checkPriceForm(input))
			{
				System.out.println("Invalid input");
				return;
			}
			inventory.EditDocument(title, edit, input);
		}
		else
		{
			System.out.println("Invalid input");
			return;
		}
		
	}


	private void addDocumentRoutine()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("Please Enter Document Title: ");
		input = sc.nextLine();
		
		if(input.contains(";"))
		{
			System.out.println("Invalid input");
			return;
		}
		
		String title = input;
		
		System.out.println("Please Enter Author: ");
		input = sc.nextLine();
		
		if(input.contains(";"))
		{
			System.out.println("Invalid input");
			return;
		}
		
		String author = input;
		
		System.out.print("Please Enter Document ID: ");
		input = sc.nextLine();
		
		if(input.contains(";"))
		{
			System.out.println("Invalid input");
			return;
		}
		
		String ID = input;
		
		System.out.print("Please Enter Price: ");
		input = sc.nextLine();
		
		if(input.contains(";") || !checkPriceForm(input))
		{
			System.out.println("Invalid input");
			return;
		}
		
		String price = input;
		
		Document toAdd = new Document(title, author, ID, price);
		inventory.addDocument(toAdd);
	}
	
	private boolean checkPriceForm(String input)
	{
		if(input.charAt(0) != '$')
			return false;
		for(int i = 1; i < input.length(); i++)
		{
			if((input.charAt(i) < 48 || input.charAt(i) > 57) && input.charAt(i) != '.')
			{
				return false;
			}
		}
		return true;
	}

	private void removeDocumentRoutine()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("Please enter document title you wish to delete:");
		input = sc.nextLine();
		
		if(!inventory.checkExistance(input))
		{
			System.out.println("Invalid book title.");
			return;
		}
		inventory.removeDocument(input);
		
	}

}
