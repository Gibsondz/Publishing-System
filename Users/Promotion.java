package Users;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Login.Account;
import Login.LoginServer;

public class Promotion implements ApplyPromotion{
	
	//Promotion List map <String:Aurthor_Name, double:Doscount_percentage> 
	private  Map<String, Double> PromotionList;  
	
	//Constructor for Promotion 
	public Promotion() {
		//CurrentlyJustImplementPromotionList
		implementAPromotionList(); 
		//FutureAllowAdminToChangePromotionList
		//createAPromotionlist();
	}
	
	//Just implement a promotion list
	public void implementAPromotionList() {
		PromotionList = createMap(); 
	}
	
	
	//Getter & Setter for creating a promotion list 
	///IteratorAndgetthediscountvalue for that Aruthor
	public double getdiscount(String name) {
		double promo = 0; 
		for (Entry<String, Double> entry : PromotionList.entrySet()) {
		    if (entry.getKey().startsWith(name)) {
		    	promo = entry.getValue(); 
		    }
		}
		return promo;
	}
	
	///IterateAndChangeDiscountValueforAruthor
	public void Changediscount(String name, double value) {
		for (Entry<String, Double> entry : PromotionList.entrySet()) {
		    if (entry.getKey().startsWith(name)) {
		    	 entry.setValue(value);
		    }
		}
	}
	
	
	//Display the PromotionList
	public void displayPromotionList(){
		//Iterate through the Promotion List
		createBorder();
		System.out.println("   " +"!!!!!PROMOTIONS!!!!!");
		System.out.println("   "+ "Weekly Discount: "+ WeeklyDiscount + "% off");
		for (Map.Entry<String, Double> entry : PromotionList.entrySet())
		{
			//Create Header 
		    System.out.println("   " + "All Books By: "+ entry.getKey() +" "+"are"+ " "+ entry.getValue()+ "% off");
		}
		createBorder();
	}
	
	//Create border
	private void createBorder(){
		int length = (PromotionList.size()+3) * 13; 
		for(int i = 0 ; i < length; i++) {
			System.out.print("#");
		}
		System.out.println(" ");
	}
    
	//ToCreateMap
    private  Map<String, Double> createMap()
    {
        Map<String,Double> myMap = new HashMap<String,Double>();
        myMap.put("Jimmy Bob", 5.0);
        myMap.put("Bonk Smonk", 15.0);
        return myMap;
    }
	
	//CheckIfPromotionListISEmptyOrNot 
    public boolean ifPromotionEmpty() {
    	if(PromotionList.isEmpty() ==true) {
    		return true;
    	}
    	return false; 
    }

}
