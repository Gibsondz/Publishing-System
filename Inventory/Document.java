package Inventory;


public class Document {
	private String title;
	private String author;
	private String ID;
	private String price;
	
	public Document(String a, String b, String c, String d)
	{
		title = a;
		author = b;
		setID(c);
		setPrice(d);
	}
	
	public String toString()
	{
		return title + " By: " + author;
	}

	public String getID() {
		return ID;
	}

	public String getTitle() {return title;}

	public void setID(String iD) {
		ID = iD;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
}
