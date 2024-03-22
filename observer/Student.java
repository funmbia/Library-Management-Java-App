package observer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import factory.HardcoverBook;
import factory.PhysicalItem;


public class Student extends User {

	private Map<Courses, Textbook> virtualTextbooks = new HashMap<>();
	private List<Courses> courseEnrolledIn = new ArrayList<>();
	private static final String CSV_FILE_PATH = "userinfo.csv";
	public List<PhysicalItem> borrowedItems = new ArrayList<>();
	private static final double PENALTY_PER_DAY = 0.5;
	private static final int MAX_BORROWED_ITEMS = 10;
	private static final int MAX_DAYS = 30;
	private static final int MAX_OVERDUE_ITEMS = 3;
	private static final int BOOK_LOST_DAYS = 15;


	public Student(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}
	

	public Student (LibraryManagementSysInfo subject, User user) {
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
		this.currentlyRenting = user.currentlyRenting;
		this.currentRentalOrder = user.currentRentalOrder;
		this.currentPurchaseOrder = user.currentPurchaseOrder;
		this.bookCollection = user.bookCollection;
		this.recommendation = user.recommendation;
		this.myInvoker = user.myInvoker;
		this.name = user.name;
		this.email = user.email;
		this.password = user.password;
		this.type = user.type;
		this.itemsOut = user.itemsOut;
		this.itemsOverdue = user.itemsOverdue;
		this.penalty = user.penalty;
	}
	
	@Override
	public void update(){
		displayWarnings();
	}

	public List<PhysicalItem> getBorrowedHardcoverBooks() {
		List<PhysicalItem> borrowedHardcoverBooks = new ArrayList<>();

		for (PhysicalItem item : borrowedItems) {
			if (item instanceof HardcoverBook) {
				HardcoverBook book = (HardcoverBook) item;
				if (subject.isItemOverdue(book)) {
					borrowedHardcoverBooks.add(book);
				}
			}
		}
		return borrowedHardcoverBooks;
	}

	public void displayWarnings() {
		for (PhysicalItem item : borrowedItems) {
			if (subject.isItemOverdue(item)) {
				System.out.println("Warning: Item " + item.getTitle() + " is overdue!");
			} else {
				long timeUntilDue = item.getDueDate().getTime() - System.currentTimeMillis();
				long hoursUntilDue = timeUntilDue / (1000 * 60 * 60);
				if (hoursUntilDue <= 24 && hoursUntilDue >= 0) {
					System.out.println("Warning: Item " + item.getTitle() + " is due in less than 24 hours!");
				}
			}
		}
	}

	///needed for all users, moved to User.java
//    public void displayBorrowedHardcoverBooks() {
//        System.out.println("Borrowed Hardcover Books:");
//        for (PhysicalItem item : borrowedItems) {
//            if (item instanceof HardcoverBook) {
//                HardcoverBook book = (HardcoverBook) item;
//                System.out.println("Title: " + book.getTitle() + ", Due Date: " + book.getDueDate());
//            }
//        }
//    }

	private boolean canBorrowItem(PhysicalItem item) {
		return cntOverdueItems() < MAX_OVERDUE_ITEMS && calculateDaysOverdue(new Date(), item.getDueDate()) <= MAX_DAYS;
	}

	private int cntOverdueItems() {
		return itemsOverdue;
	}

	public boolean borrowPhysicalItem(PhysicalItem item) {
		if (borrowedItems.size() < MAX_BORROWED_ITEMS && item.isAvailable() && canBorrowItem(item)) {
			borrowedItems.add(item);
			item.setRentalStatus(true);
			item.decreaseAvailableCopies();
			subject.handleOverdueItems(item);
			return true;
		}
		return false;
	}

	public void returnPhysicalItem(PhysicalItem item) {
		borrowedItems.remove(item);
		item.setRentalStatus(false);
		item.increaseAvailableCopies();
		subject.handleReturnedItem(item);
	}

	///needed for all users, moved to User.java
//	public void penalityApplication() {
//		for (PhysicalItem item : borrowedItems) {
//			if (item.isOverdue()) {
//				long daysOverdue = calculateDaysOverdue(new Date(), item.getDueDate());
//				if (daysOverdue >= BOOK_LOST_DAYS) {
//					handleLostBook(item);
//				} else {
//					double penalty = daysOverdue * PENALTY_PER_DAY;
//					System.out.println("Penalty of $" + penalty + " applied for overdue item: " + item.getTitle());
//				}
//			}
//		}
//	}

	///needed for all users, moved to User.java
//	private void handleLostBook(PhysicalItem item) {
//		System.out.println("Please return overdue book " + item.getTitle());
//		System.out.println("Library Manangement System has been notified.");
//	}

	private long calculateDaysOverdue(Date currentDate, Date dueDate) {
		long diffM = Math.abs(currentDate.getTime() - dueDate.getTime());
		long diffDays = diffM / (1000 * 60 * 60 * 24);
		return diffDays;
	}

	
	
	
	public void enroll(Courses course){
		courseEnrolledIn.add(course);
	}

	public void withdraw(Courses course){
		courseEnrolledIn.remove(course);
		removeVirtualCopies(course);
	}

	public List<Courses> viewEnrolledCourses(){
		return courseEnrolledIn;
	}

	public void createVirtualCopies(Courses course, Textbook textbook){
		virtualTextbooks.put(course, textbook);
	}

	public void removeExtraCopies(Courses course){
		virtualTextbooks.remove(course);
	}

	public Textbook getTextbook(Courses course){
		return virtualTextbooks.get(course);
	}

    public void removeVirtualCopies(Courses course) {
        virtualTextbooks.remove(course);
    }

    
	public boolean validation(String email, String password) {
		if (!uniqueEmail(email)) {
			System.out.println("Email is already registered");
			return false;
		}

		if (!isStrongPassword(password)) {
			System.out.println("Password is not strong enough");
			return false;
		}

		return true;
	}

	public boolean uniqueEmail(String email) {
		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String storedEmail = parts[1].trim();
				if (email.equals(storedEmail)) {
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean isStrongPassword(String password) {

		if (password.length() < 8) {
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
}
