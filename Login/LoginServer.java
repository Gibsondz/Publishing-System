package Login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * LoginServer class to help validate accounts and log people in.
 * Uses a local text file to populate account list
 * File lines must be in the form of 
 * 
 * USERNAME PASSWORD TYPE\n
 * 
 * @author Gibson Dziwinski
 * @version 1.0
 * @since November 22, 2018
 *
 */
public class LoginServer {
	
	//List of accounts the loginserver has
	private ArrayList<Account> accounts;
	
	/**
	 * populates the loginserver with all accounts from the file "accounts.txt"
	 */
	public LoginServer()
	{
		populate();
		/*
		for(int i = 0; i < accounts.size(); i++)
		{
			System.out.println(accounts.get(i).getUsername() + " " + accounts.get(i).getPassword() + " " + accounts.get(i).getType());
		}
		*/
	}
	
	/**
	 * populates the accounts arraylist with everything in the accounts.txt file
	 * has a small amount of error checking as it checks how many datum are on each line.
	 */
	private void populate()
	{
		accounts = new ArrayList<Account>();
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
			String currentline;
			
			while((currentline = br.readLine()) != null)
			{
				String [] split = currentline.split(" ");
				
				if(split.length != 3)
				{
					System.out.println("Invalid amount of variables. Check your accounts.txt file");
					System.exit(-1);
				}
				Account a = new Account(split[0], split[1], split[2]);
				accounts.add(a);
			}
			br.close();
		} 
		catch (IOException e)
		{
			System.out.println("Error populating LoginServer");
			e.printStackTrace();
		}
	}
	
	/**
	 * checks to see if a user and password are in database
	 * @param user
	 * @param pass
	 * @return the account from the database
	 */
	public Account validate(String user, String pass)
	{
		for(int i = 0; i < accounts.size(); i++)
		{
			if(user.equals(accounts.get(i).getUsername()) && pass.equals(accounts.get(i).getPassword()))
			{
				return accounts.get(i);
			}
		}
		return null;
	}

	public void createAccount(String user, String pass, String type){
		Account newaccount = new Account(user,pass,type);
		if(!accounts.add(newaccount)){
			System.out.println("ERROR CREATING ACCOUNT");
		}
		try
		{
			String tofile = "\n" + user + " " + pass + " " + type;
			Files.write(Paths.get("accounts.txt"), tofile.getBytes(), StandardOpenOption.APPEND);
		}
		catch (IOException e)
		{
			System.out.println("Error populating LoginServer");
			e.printStackTrace();
		}
	}
	
}
