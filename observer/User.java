package observer;

import factory.PhysicalItem;
import factory.Newsletter;
import factory.OnlineBook;
import java.util.ArrayList;
import java.util.List;
import builder.OrderBuilder;
import builder.RentalOrder;
import iterator.Book;
import iterator.BookCollection;
import iterator.Recommendation;
//import singleton.*; import once email and password methods added there

public class User {

	public LibraryManagementSysInfo subject;
	public List<RentalOrder> currentlyRenting;
	public OrderBuilder currentOrder;
	private BookCollection bookCollection;
	private Recommendation recommendation;
	public Invoker myInvoker;

	private String name;
	private String email;
	private String password;
	private String type;
	public int itemsOut;
	public int itemsOverdue;
	public double penaltyToPay;
	private String penalty;

	public void update() {
	}

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

	public String getAccountType() {
		return "";
	}

	public char[] getPenalty() {
		return null;
	}

	// New methods added from UserInfo class
	class Invoker {
		//dummy class
	}

	public User() {
	}

	public User(int itemsOverdue, int penaltyToPay, List<RentalOrder> currentlyRenting, OrderBuilder currentOrder,
			String email, String password, String type, Invoker myInvoker) {
		this.itemsOverdue = itemsOverdue;
		this.penaltyToPay = penaltyToPay;
		this.currentlyRenting = currentlyRenting;
		this.currentOrder = currentOrder;
		this.email = email;
		this.password = password;
		this.type = type;
		this.myInvoker = myInvoker;
	}

	public void setDatabaseAttributes(String name, String email, String password, String accountType, int itemsOut, int itemsOverdue, int penalty) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = accountType;
		this.itemsOut = itemsOut;
		this.itemsOverdue = itemsOverdue;
		this.penaltyToPay = penalty;
	}

	public double penaltyToPay() {
		double penalty;
		penalty=itemsOverdue * 0.5;
		return penalty;
	}

	public boolean hasBorrowingPrivileges() {
		boolean privileges=true;
		if (itemsOverdue>3){
			privileges =false;
		} 
		return privileges;
	}

	public String displayRentalWarnings(){
		String warning="You have" + itemsOverdue +". Renting more then three books will result in loss of borrowing privileges ";
		return warning;
	}


	public User(BookCollection bookCollection, Recommendation recommendation) {
		this.bookCollection = bookCollection;
		this.recommendation = recommendation;
	}

	public Book search(String bName) {
		return bookCollection.searchBookByName(bName);
	}

	public void showRecommendations() {
		List<Book> recommendedBooks = recommendation.getRecommendedBooks();

		if (recommendedBooks.isEmpty()) {
			System.out.println("No recommendations available.");
		} else {
			System.out.println("Recommended Books:");
			for (Book book : recommendedBooks) {
				System.out.println(book.getBookTitle());
			}
		}
	}



	//GETTERS AND SETTERS FOR DATABASE ITEMS
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public int getItemsOut() {
		return itemsOut;
	}

	public int getItemsOverdue() {
		return itemsOverdue;
	}

	public void setItemsOverdue(int itemsOverdue) {
		this.itemsOverdue = itemsOverdue;
	}

	public double getPenaltyToPay() {
		return penaltyToPay;
	}

	public void setPenaltyToPay(double penaltyToPay) {
		this.penaltyToPay = penaltyToPay;
	}


	// OTHER GETTERS AND SETTERS
	public Invoker getMyInvoker() {
		return myInvoker;
	}

	public void setMyInvoker(Invoker myInvoker) {
		this.myInvoker = myInvoker;
	}

	public List<RentalOrder> getCurrentlyRenting() {
		return currentlyRenting;
	}

	public void setCurrentlyRenting(List<RentalOrder> currentlyRenting) {
		this.currentlyRenting = currentlyRenting;
	}

	public void addToRenting(RentalOrder order) {
		currentlyRenting.add(order);
	}

	public void removeFromRenting(RentalOrder order) {
		currentlyRenting.remove(order);
	}

	public OrderBuilder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(OrderBuilder currentOrder) {
		this.currentOrder = currentOrder;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "] \n" +
				"\t[This User has " + itemsOut + ", " + itemsOverdue + " of which are overdue. User is owing $" + penalty;
	}
}
