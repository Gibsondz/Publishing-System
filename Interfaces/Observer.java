package Interfaces;
import java.util.ArrayList;

import Inventory.Document;

public interface Observer {
	public void update(ArrayList<Document> data);
}
