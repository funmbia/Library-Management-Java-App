package database;

import java.util.ArrayList;
import java.util.List;

//ALSO NEED RENTALORDERS, PURCHASEORDERS csv sheets

//this class is used to populate the User class that actually performs actions
public class UserInfo {
	private String name;
	private String email;
	private String password;
	private String accountType;
	private ArrayList<Integer> currentlyRenting = new ArrayList<>(); //saved in database as numbers with spaces
	private int itemsOut;
	private int itemsOverdue;
	
	public void setAttributes(String name, String email, String password, String accountType, int itemsOut, int itemsOverdue, int penalty) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.accountType = accountType;
		this.itemsOut = itemsOut;
		this.itemsOverdue = itemsOverdue;
		this.penalty = penalty;
	}
	
	public void setCurrentlyRenting(String renting) { 
		int space;
		while (renting.length() > 0) {
		    space = renting.indexOf(' ');
		    if (space == -1) {
		        space = renting.length();
		    }
		    currentlyRenting.add(Integer.valueOf(renting.substring(0, space)));
		    renting = renting.substring(space).trim();
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public String getCurrentlyRenting() {
		String ans = "";
		for (Integer x : currentlyRenting)
			ans += x + " ";
		return ans;
	}
	
	public int getItemsOut() {
		return itemsOut;
	}
	
	public int getItemsOverdue() {
		return itemsOverdue;
	}
	
	public int getPenalty() {
		return penalty;
	}
	
	public void addToRenting(int rentalOrderID) {
		currentlyRenting.add(rentalOrderID);
	}
	
	public void removeFromRenting(int rentalOrderID) {
		currentlyRenting.remove(rentalOrderID);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "] \n" +
				"\t[This User has " + itemsOut + ", " + itemsOverdue + " of which are overdue. User is owing $" + penalty;
	}
	
}
