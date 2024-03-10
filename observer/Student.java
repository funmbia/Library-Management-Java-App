import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends RegisteredClient {

	private Map<Courses, List<Textbook>> virtualTextbooks = new HashMap<>();
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

	@Override
	public void update(){
		displayWarnings();
	}

	public List<HardcoverBooks> getBorrowedHardcoverBooks() {
		List<HardcoverBooks> borrowedHardcoverBooks = new ArrayList<>();

		for (PhysicalItem item : borrowedItems) {
			if (item instanceof HardcoverBooks) {
				HardcoverBooks book = (HardcoverBooks) item;
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

    public void displayBorrowedHardcoverBooks() {
        System.out.println("Borrowed Hardcover Books:");
        for (PhysicalItem item : borrowedItems) {
            if (item instanceof HardcoverBooks) {
                HardcoverBooks book = (HardcoverBooks) item;
                System.out.println("Title: " + book.getTitle() + ", Due Date: " + book.getDueDate());
            }
        }
    }

	private boolean canBorrowItem(PhysicalItem item) {
		return cntOverdueItems() < MAX_OVERDUE_ITEMS && calculateDaysOverdue(new Date(), item.getDueDate()) <= MAX_DAYS;
	}

	private int cntOverdueItems() {
		int overdueCnt = 0;
		for (PhysicalItem item : borrowedItems) {
			if (item.isOverdue()) {
				overdueCnt++;
			}
		}
		return overdueCnt;
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

	public void penalityApplication() {
		for (PhysicalItem item : borrowedItems) {
			if (item.isOverdue()) {
				long daysOverdue = calculateDaysOverdue(new Date(), item.getDueDate());
				if (daysOverdue >= BOOK_LOST_DAYS) {
					handleLostBook(item);
				} else {
					double penalty = daysOverdue * PENALTY_PER_DAY;
					System.out.println("Penalty of $" + penalty + " applied for overdue item: " + item.getTitle());
				}
			}
		}
	}

	private void handleLostBook(PhysicalItem item) {
		System.out.println("Please return overdue book " + item.getTitle());
		System.out.println("Library Manangement System has been notified.");
	}

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
	}

	public List<Courses> viewEnrolledCourses(){
		return courseEnrolledIn;
	}

	public void createVirtualCopies(Courses course, Textbook textbook){
		if (!virtualTextbooks.containsKey(course)){
			virtualTextbooks.put(course, new ArrayList<>());
		}
		virtualTextbooks.get(course).add(textbook);
	}

	public void removeExtraCopies(Courses course){
		virtualTextbooks.remove(course);
	}

	public List<Textbook> getTextbooks(Courses course){
		return virtualTextbooks.getOrDefault(course, new ArrayList<>());
	}

    public void makeVirtualCopies(Courses course, Textbook textbook) {
        if (!virtualTextbooks.containsKey(course)) {
            virtualTextbooks.put(course, new ArrayList<>());
        }
        virtualTextbooks.get(course).add(textbook);
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
