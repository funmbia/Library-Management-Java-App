package observer;
import java.util.ArrayList;
import factory.PhysicalItem;
import factory.HardcoverBook;
import factory.Newsletter;
import factory.OnlineBook;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagementSysInfo {

	public List<User> users = new ArrayList<>();
	public List<PhysicalItem> physicalItems = new ArrayList<>();
	public List<OnlineBook> onlineBooks = new ArrayList<>();
	public List<Newsletter> newsletter = new ArrayList<>();
	public List<HardcoverBook> hardcover = new ArrayList<>();
	private Map<String, Integer> overdueItemsCnt = new HashMap<>();
	private Map<Textbook, Date> dueDates = new HashMap<>();

	public void attachRegisteredClient(User observer) {
		users.add(observer);
	}

	public void deatachRegisteredClient(User observer) {
		users.remove(observer);
	}

	public List<HardcoverBook> getAlreadyRented(){
		List<HardcoverBook> rentedBooks = new ArrayList<>();
		for (HardcoverBook book : hardcover){
			if (book.isRented()){
				rentedBooks.add(book);
			}
		}
		return rentedBooks;
	}

	public List<PhysicalItem> getAvailPhysicalItems(){
		List<PhysicalItem> availableItems = new ArrayList<>();
		for (PhysicalItem item: physicalItems){
			if (item.isAvailable()){
				availableItems.add(item);
			}
		}
		return availableItems;

	}

	public List<OnlineBook> getAvailOnlineBooks(){
		List<OnlineBook> availableBooks = new ArrayList<>();
		for (OnlineBook book : onlineBooks){
			if (book.isAvailable()){
				availableBooks.add(book);
			}
		}
		return availableBooks;
	}

	public List<Newsletter> getAvailNewsletter(){
		List<Newsletter> availableNewsletters = new ArrayList<>();
		for (Newsletter newsletter : newsletter){
			if (newsletter.isAvailable()){
				availableNewsletters.add(newsletter);
			}
		}
		return availableNewsletters;
	}

	public void notifyAllObservers(){
		for (User observer : users){
			observer.update();
		}
	}

	public void handleOverdueItems(PhysicalItem item) {
		String title = item.getTitle();
		int overdueCnt = overdueItemsCnt.getOrDefault(title, 0);
		overdueItemsCnt.put(title, overdueCnt + 1);
	}

	public void handleReturnedItem(PhysicalItem item) {
		String title = item.getTitle();
		int overdueCount = overdueItemsCnt.getOrDefault(title, 0);
		if (overdueCount > 0) {
			overdueItemsCnt.put(title, overdueCount - 1);
		}
	}

	public boolean isItemOverdue(PhysicalItem item) {
		String title = item.getTitle();
		return overdueItemsCnt.getOrDefault(title, 0) > 0;
	}

	public void setDueDateForTextbook(Textbook textbook, Date dueDate) {
		dueDates.put(textbook, dueDate);
	}

	public Date getDueDateForTextbook(Textbook textbook) {
		return dueDates.get(textbook);
	}
}
