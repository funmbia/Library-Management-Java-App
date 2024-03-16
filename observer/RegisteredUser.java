package observer;

import factory.PhysicalItem;
import factory.Newsletter;
import factory.OnlineBook;
import java.util.ArrayList;
import java.util.List;
import singleton.*;

public class RegisteredUser {

	public LibraryManagementSysInfo subject;

	public abstract void update();

	private String email;
	private String password;

	public User loginUser();
	
	private boolean isValidEmail(String email);

	private boolean isValidPassword(String password);

	private boolean additionalValidationForUserType(String userType);

	public Newsletter subscribeNewsletter (Newsletter newsletter){
		System.out.println("You are subscribed to " + newsletter.getName());
		return newsletter;
	}

	public Newsletter cancelNewsletter(Newsletter newsletter){
		System.out.println("You are unsubscribed to " + newsletter.getName());
		return newsletter;
	}

	public OnlineBook openBook(OnlineBook onlineBook){
		System.out.println("You have opened: " + onlineBook.getTitle());
		return onlineBook;
	}

	public PhysicalItem rentPhysicalItem(PhysicalItem physicalItem){
		System.out.println("You have rented: " + physicalItem.getTitle());
		return physicalItem;
	}

	// New methods added from UserInfo class

	private String name;
	private String accountType;
	private List<Integer> currentlyRenting = new ArrayList<>();
	private int itemsOut;
	private int itemsOverdue;
	private int penalty;

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
