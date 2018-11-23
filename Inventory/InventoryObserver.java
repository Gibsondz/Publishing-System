package Inventory;
import java.util.ArrayList;

import Interfaces.Observer;

public class InventoryObserver implements Observer {
	
	private ArrayList<Document> data;
	
	public void update(ArrayList<Document> data) {
		this.data = data;
		display();
	}
	
	public void display()
	{
		for(int i = 0; i < data.size(); i++)
		{
			System.out.println(data.get(i));
		}
		System.out.println();
	}
	
}
