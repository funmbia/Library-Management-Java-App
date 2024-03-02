package factory;

public abstract class LibraryItem {
	protected String title;
	protected int id;
	protected static int increment;
	
	
	public LibraryItem() {
		increment ++;
		this.id = increment;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getID() {
		return id;
	}
	
	public abstract String toString();
}


class Newsletter extends LibraryItem {
	private String organization;
	private String URL;
	
	public void setAttributes(String title, String organization, String URL) {
		this.title = title;
		this.organization = organization;
		this.URL = URL;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public String getURL() {
		return URL;
	}

	@Override
	public String toString() {
		return "NEWSLETTER SUMMARY:\n\tTitle: " + this.title + ", Organization: " + this.organization + ", URL: " + this.URL;
	}
}


class OnlineBook extends LibraryItem {
	private String author;
	private String publisher;
	private String URL;
	private String ISBN;
	
	public void setAttributes(String title, String author, String publisher, String URL, String ISBN) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.URL = URL;
		this.ISBN = ISBN;
	}

	
	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public String getURL() {
		return URL;
	}

	public String getISBN() {
		return ISBN;
	}

	@Override
	public String toString() {
		return "ONLINE BOOK SUMMARY:\n\tTitle: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher 
				+ ", \n\tURL: " + this.URL + ", ISBN: " + this.ISBN;
	}
}


	//PHYSICAL ITEMS


class HardcoverBook extends LibraryItem {
	private String author;
	private String publisher;
	private String ISBN;
	private String location;
	private boolean rentable;
	private boolean purchaseable;

	private int copiesAvail = 20;
	
	public void setAttributes(String title, String author, String publisher, String ISBN, String location, boolean rentable, boolean purchaseable) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getISBN() {
		return ISBN;
	}
	
	public boolean getRentable() {
		return rentable;
	}

	public boolean getPurchaseable() {
		return purchaseable;
	}
	
	public void setCopiesAvail(int num) {
		copiesAvail = num;
	}
	
	public int getCopiesAvail() {
		return copiesAvail;
	}
	
	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable" : "not purchaseable";
		return "HARDCOVER BOOK SUMMARY:\n\tTitle: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher + ", ISBN: " + this.ISBN 
				+ ".\n\tLocation: " + location + ". This book is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available." ;
	}
}


class Magazine extends LibraryItem {
	private String publisher;
	private String location;
	private boolean rentable;
	private boolean purchaseable;

	private int copiesAvail = 20;
	
	public void setAttributes(String title, String publisher, String location, boolean rentable, boolean purchaseable) {
		this.title = title;
		this.publisher = publisher;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
	}

	public String getPublisher() {
		return publisher;
	}

	public boolean getRentable() {
		return rentable;
	}

	public boolean getPurchaseable() {
		return purchaseable;
	}
	
	public void setCopiesAvail(int num) {
		copiesAvail = num;
	}
	
	public int getCopiesAvail() {
		return copiesAvail;
	}
	
	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable" : "not purchaseable";
		return "MAGAZINE SUMMARY:\n\tTitle: " + this.title + ", Publisher: " + this.publisher + ", Location: " + location
				+ ".\n\tThis magazine is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available.";
	}
}


class CD extends LibraryItem {
	private String location;
	private boolean rentable;
	private boolean purchaseable;
	
	private int copiesAvail = 20;
	
	public void setAttributes(String title, String location, boolean rentable, boolean purchaseable) {
		this.title = title;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
	}
	
	public boolean getRentable() {
		return rentable;
	}

	public boolean getPurchaseable() {
		return purchaseable;
	}
	
	public void setCopiesAvail(int num) {
		copiesAvail = num;
	}
	
	public int getCopiesAvail() {
		return copiesAvail;
	}
	
	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable" : "not purchaseable";
		return "CD SUMMARY:\n\tTitle: " + this.title + ", Location: " + location
				+ ".\n\tThis CD is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available.";
	}
}
