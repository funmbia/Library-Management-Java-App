package observer;

import factory.PhysicalItem;
import factory.CD;
import factory.HardcoverBook;
import factory.Magazine;
import factory.Newsletter;
import factory.OnlineBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import builder.OrderBuilder;
import builder.PurchaseOrder;
import builder.PurchaseOrderBuilder;
import builder.RentalOrder;
import builder.RentalOrderBuilder;
import command.Invoker;
import command.OpenNewsletter;
import command.PurchasePhysicalItem;
import command.RentPhysicalItem;
import command.RequestPhysicalBook;
import iterator.Book;
import iterator.BookCollection;
import iterator.Recommendation;
//import singleton.*; import once email and password methods added there

public class User {

	public LibraryManagementSysInfo subject;
	public List<RentalOrder> currentlyRenting;
	public RentalOrderBuilder currentRentalOrder;
	public PurchaseOrderBuilder currentPurchaseOrder;
	protected BookCollection bookCollection;
	protected Recommendation recommendation;
	public Invoker myInvoker;

	protected String name;
	protected String email;
	protected String password;
	protected String type;
	public int itemsOut;
	public int itemsOverdue;
	public double penalty;
	
/* Constructors */
	public User() {
		currentlyRenting = new ArrayList<>();
		myInvoker = new Invoker();
	}

	public User(int itemsOverdue, int penalty, List<RentalOrder> currentlyRenting, RentalOrderBuilder rentalOrder, 
			PurchaseOrderBuilder purchaseOrder, String name, String email, String password, String type, Invoker myInvoker) {
		this.itemsOverdue = itemsOverdue;
		this.penalty = penalty;
		this.currentlyRenting = currentlyRenting;
		this.currentRentalOrder = rentalOrder;
		this.currentPurchaseOrder = purchaseOrder;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.myInvoker = myInvoker;
	}
	
	public User(BookCollection bookCollection, Recommendation recommendation) {
		currentlyRenting = new ArrayList<>();
		this.bookCollection = bookCollection;
		this.recommendation = recommendation;
	} 

	public void setDatabaseAttributes(String name, String email, String password, String accountType, int itemsOut, int itemsOverdue, int penalty) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = accountType;
		this.itemsOut = itemsOut;
		this.itemsOverdue = itemsOverdue;
		this.penalty = penalty; 
	}
/* Constructors */
	

	public void update() {
	}

	
/* COMMANDS */
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
	
	//OPEN NEWSLETTER
	public String openNewsletter(Newsletter newsletter) {
		if (myInvoker == null) myInvoker = new Invoker();
		OpenNewsletter openNews = new OpenNewsletter(newsletter);
		myInvoker.setCommand(openNews);
		return myInvoker.executeCommand();
	}
	
	//REQUEST
	public String request (String bookName, String requestType) {
		if (myInvoker == null) myInvoker = new Invoker();
		RequestPhysicalBook command = new RequestPhysicalBook(bookName, requestType);
		myInvoker.setCommand(command);
		return myInvoker.executeCommand();
	}

	
	//RENT
	public String rent(PhysicalItem physicalItem) {
		if (myInvoker == null) myInvoker = new Invoker();
		if (currentRentalOrder == null) {
			currentRentalOrder = new RentalOrderBuilder(this);
		}
		RentPhysicalItem rentItem = new RentPhysicalItem(this, physicalItem, currentRentalOrder);
		myInvoker.setCommand(rentItem);
		return myInvoker.executeCommand();
	}
	
	public String getCurrentRentalOrderSummary() {//notification when user presses any button to leave the rent page (back/logout)
		RentalOrder ro = currentRentalOrder.returnOrder();
		return "Order ID" + ro.getOrderID() 
				+ "\nLocations: " + ro.getLocations() 
				+ "\nDue Date: " + ro.getDueDate().toString();
	}

	public void setCurrentRentalOrder(RentalOrderBuilder currentOrder) {
		this.currentRentalOrder = currentOrder;
	}
	
	
	//PURCHASE
	public String purchase(PhysicalItem physicalItem, double discountPercent) {
		if (myInvoker == null) myInvoker = new Invoker();
		if (currentPurchaseOrder == null) {
			currentPurchaseOrder = new PurchaseOrderBuilder(this);
		}
		currentPurchaseOrder.setDiscount(discountPercent);
		PurchasePhysicalItem purchaseItem = new PurchasePhysicalItem(this, physicalItem, currentPurchaseOrder);
		myInvoker.setCommand(purchaseItem);
		return myInvoker.executeCommand();
	}
	
	public String getPurchaseOrderSummaryAndPay(String method) {//notification when user presses the pay button
		PurchaseOrder po = currentPurchaseOrder.returnOrder();
		String summary = "Order " + po.getOrderID() + "for " + po.getUserEmail() + ":\n" 
				+ "Price: " + po.getPrice();
		summary += "\n" + po.pay(method);
		return summary;
	}
	
	public void setCurrentPurchaseOrder(PurchaseOrderBuilder currentOrder) {
		this.currentPurchaseOrder = currentOrder;
	}

/* COMMANDS */


	
   
	
/* Methods to do with renting/borrowing */
	private static final double PENALTY_PER_DAY = 0.5;
	private static final int MAX_BORROWED_ITEMS = 10;
	private static final int MAX_DAYS = 30;
	private static final int MAX_OVERDUE_ITEMS = 3;
	private static final int BOOK_LOST_DAYS = 15;
	
	public void addToRenting(RentalOrder order) {
		currentlyRenting.add(order);
	}

	public void removeFromRenting(RentalOrder order) {
		currentlyRenting.remove(order);
	}
	
	public boolean hasBorrowingPrivileges() {
		if (itemsOverdue > MAX_OVERDUE_ITEMS){
			return false;
		} 
		return true;
	}

	public String displayRentalWarnings(){
		penaltyApplication();
		return "<html>You have " + itemsOverdue + " item(s) overdue & a penalty of $" + penalty + ".<br>Please note that more than 3 Books overdue <br>will result in loss of borrowing privileges.</html>";
	}
	
	public Set<String> almostDue = new HashSet<>();
	public Set<String> overDue = new HashSet<>();
	public Set<String> notYetDue = new HashSet<>();
	
	public Map<List<PhysicalItem>, String> getBorrowedItems() {
		penaltyApplication();
		Map<List<PhysicalItem>, String> itemsAndDueDates = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (currentlyRenting != null) {
			for (RentalOrder order : currentlyRenting) {
				List<PhysicalItem> orderItems = new ArrayList<>();
				orderItems.addAll(order.getItems());
				long hoursUntilDue = hoursUntilDue(order.getDueDate());
				if (hoursUntilDue <= 0) {	
					for (PhysicalItem item : orderItems) {
						if (hoursUntilDue <= -360 && ! overDue.contains(order)) {
							double penalty = hoursUntilDue/24 * 0.5;
							this.penalty += penalty*-1;
							overDue.add(item.getTitle() + "; 15 days overdue; considered lost. Penalty = + $" + penalty*-1);
						} else {
							overDue.add(item.getTitle() + "; " + hoursUntilDue*-1 + "hours overdue");
						}
					}
				}
				else if (hoursUntilDue(order.getDueDate()) < 24 && hoursUntilDue(order.getDueDate()) > 0 && ! almostDue.contains(order)) {
					for (PhysicalItem item : orderItems) {
						almostDue.add(item.getTitle() + "; Due Soon in " + hoursUntilDue + " hours");
					}
				}
				else {
					for (PhysicalItem item : orderItems) {
						notYetDue.add(item.getTitle() + "; Due in " + hoursUntilDue/24 + " day(s)");
					}
				}
				itemsAndDueDates.put(orderItems, sdf.format(order.getDueDate()));				
			}
		}
		return itemsAndDueDates;
	}
	
	private void penaltyApplication() {
		this.penalty = 0;
		this.itemsOverdue = 0;
		if (currentlyRenting != null) {
			for (RentalOrder order : currentlyRenting) {
				for (PhysicalItem item : order.getItems()) {
					if (item.isOverdue(order.getDueDate())) { 
						itemsOverdue ++;
							long daysOverdue = calculateDaysOverdue(new Date(), order.getDueDate());
							if (daysOverdue >= BOOK_LOST_DAYS) {
								 handleLostBook(item);
							} else {
								double itemPenalty = daysOverdue * PENALTY_PER_DAY;
								System.out.println("Penalty of $" + itemPenalty + " applied for overdue item: " + item.getTitle());
								this.penalty += itemPenalty;
							}
						
					}
				}
			}
		}
		
	}
	
	private long hoursUntilDue(Date dueDate) {
		long timeUntilDue = dueDate.getTime() - System.currentTimeMillis();
		long hoursUntilDue = timeUntilDue / (1000 * 60 * 60);
		return hoursUntilDue;
	}
		
	private long calculateDaysOverdue(Date currentDate, Date dueDate) {
		long diffM = Math.abs(currentDate.getTime() - dueDate.getTime());
		long diffDays = diffM / (1000 * 60 * 60 * 24);
		return diffDays;
	}

	private String handleLostBook(PhysicalItem book) {
		return  book.getTitle() + " has been overdue for 15 days and is considered lost."
				+ "\nPlease return as soon as possible.\nLibrary Manangement has been notified.";
	}
/*Methods to do with renting/borrowing*/	
	

		
	
	
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
	
	public String getAccountType() {
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

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
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

	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "] \n" +
				"\t[This User has " + itemsOut + ", " + itemsOverdue + " of which are overdue. User is owing $" + penalty;
	}
}
