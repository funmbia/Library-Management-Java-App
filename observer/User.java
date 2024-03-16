package observer;

import factory.PhysicalItem;
import factory.Newsletter;
import factory.OnlineBook;
import java.util.ArrayList;
import java.util.List;
//import singleton.*; import once email and password methods added there

public class User {

	public LibraryManagementSysInfo subject;

	public void update() {
	}

	private String email;
	private String password;

	/* Remove the following once synchronized with singleton class*/
	public boolean login(String email, String password, String accountType){
		if(!isValidEmail(email) || isValidPassword(password)) {
			System.out.println("Invalid info");
			return false;
		}

		if (accountType.equals("student") || accountType.equals("faculty") || accountType.equals("staff")) {
			if (!additionalValidationForUserType(accountType)) {
				System.out.println("Additional validation failed for user type: " + accountType);
				return false;
			}
		}

		this.email = email;
		this.password = password;
		System.out.println("Successful registration.");
		return true;
	}

	private boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailSymb = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailSymb);
	}

	private boolean isValidPassword(String password) {
		if (password == null || password.isEmpty() || password.length() < 8) {
			return false;
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;
		String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else if (specialChars.contains(String.valueOf(c))) {
				hasSpecialChar = true;
			}
		}

		return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
	}

	private boolean additionalValidationForUserType(String userType) {

		if (userType.equals("student")) {
			System.out.println("You have now been validated as a student.");
		} else if (userType.equals("faculty")) {
			System.out.println("You have now been validated as a faculty.");
		} else if (userType.equals("staff")) {
			System.out.println("You have now been validated as a staff.");
		}
		return true;
	}
	/* Methods that may be removed end here*/

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
