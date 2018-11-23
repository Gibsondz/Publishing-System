package Inventory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Interfaces.Observer;
import Interfaces.Subject;

public class Inventory implements Subject {
	
	private ArrayList<Document> inventory;
	private ArrayList<Observer> observers;

	public void registerObserver(Observer o) {
		observers.add(o);
	}


	public void remove(Observer o) {
		observers.remove(o);
	}


	public void notifyAllObservers() {
		for(int i = 0; i < observers.size(); i++)
		{
			observers.get(i).update(inventory);
		}
	}
	
	public Inventory()
	{
		observers = new ArrayList<Observer>();
		populateInventory();
	}
	
	public void populateInventory()
	{
		inventory = new ArrayList<Document>();
		registerObserver(new InventoryObserver());
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("documents.txt"));
			String currentline;
			
			while((currentline = br.readLine()) != null)
			{
				String [] split = currentline.split(";");
				
				if(split.length != 3)
				{
					System.out.println("Invalid amount of variables. Check your documents.txt file");
					System.exit(-1);
				}
				Document a = new Document(split[0], split[1], split[2]);
				inventory.add(a);
			}
			br.close();
		}
		catch (IOException e)
		{
			System.out.println("Error populating LoginServer");
			e.printStackTrace();
		}
		notifyAllObservers();
	}

	public int searchInventory(String docname){
		for(int i = 0; i < inventory.size(); i++){
			if(docname.equals(inventory.get(i).getTitle())){
				return i;
			}
		}
		return -1;
	}

	public ArrayList getInventory(){
		return inventory;
	}

}
