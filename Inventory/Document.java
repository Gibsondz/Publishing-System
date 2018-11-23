package Inventory;


public class Document {
	private String title;
	private String author;
	private String ID;
	
	public Document(String a, String b, String c)
	{
		title = a;
		author = b;
		setID(c);
	}
	
	public String toString()
	{
		return title + " By: " + author;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
