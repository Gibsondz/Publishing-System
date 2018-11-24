package Inventory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			
				if(split.length != 4 && split.length != 1)
				{
					System.out.println("split.length = " + split.length);
					System.out.println("Invalid amount of variables. Check your documents.txt file");
					System.exit(-1);
				}
				if(split.length != 1)
				{
					Document a = new Document(split[0], split[1], split[2], split[3]);
					inventory.add(a);
				}
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


	public void addDocument(Document toAdd) {
		try
		{
			FileWriter fw = new FileWriter("documents.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			out.println("\n" + toAdd.getTitle() + ";" + toAdd.getAuthor() + ";" + toAdd.getID() + ";" + toAdd.getPrice());
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Failed to write to documents.txt");
			e.printStackTrace();
		}
		inventory.add(toAdd);
		notifyAllObservers();
	}
	
	public void removeDocument(String title)
	{
		try {
			
			File inputFile = new File("documents.txt");
			File tempFile = new File("temp.txt");
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String currentLine;
	
			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    String [] splits = currentLine.split(";");
			    if(splits[0].contains(title) || !trimmedLine.contains(";")) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			
			if(!inputFile.delete())
			{
				System.out.print("Could not delete old document file");
				System.exit(-1);
			}
			boolean successful = tempFile.renameTo(inputFile);
			if(!successful)
			{
				System.out.println("Error deleting document from document.txt");
				System.exit(-1);
			}
		}
		catch(IOException e)
		{
			System.out.println("Error deleting document from document.txt");
			e.printStackTrace();
		}
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i).getTitle().equals(title))
			{
				inventory.remove(i);
				break;
			}
		}
		notifyAllObservers();
	}


	public boolean checkExistance(String title) {
		
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i).getTitle().equals(title))
			{
				return true;
			}
		}
		return false;
	}


	public void EditDocument(String title, String edit, String input) {
		int i = 0;
		for(i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i).getTitle().equals(title))
			{
				break;
			}
		}

		if(edit.equals("1"))
		{
			inventory.get(i).setTitle(input);
		}
		else if(edit.equals("2"))
		{
			inventory.get(i).setAuthor(input);
		}
		else if(edit.equals("3"))
		{
			inventory.get(i).setID(input);
		}
		else if(edit.equals("4"))
		{
			inventory.get(i).setPrice(input);
		}
		
		try
		{
			
			File f = new File("documents.txt");
			if(!f.delete())
			{
				System.out.println("Failed to delete file");
				System.exit(-1);
			}
			
			FileWriter writer = new FileWriter("documents.txt"); 
			for(Document d: inventory) {
			  writer.write(d.getTitle()+ ";" + d.getAuthor() + ";" + d.getID() + ";" + d.getPrice()+"\n");
			}
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Error in editing a document");
			e.printStackTrace();
		}
		
		notifyAllObservers();
	}

}
