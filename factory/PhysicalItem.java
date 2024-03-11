package factory;

public abstract class PhysicalItem extends LibraryItem {
	protected String location;
	protected boolean rentable;
	protected boolean purchaseable;
	protected double price;

	protected int copiesAvail = 20;
	
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

	public double getPrice() {
		return price;
	}
	
	
}

//PHYSICAL ITEMS


class HardcoverBook extends PhysicalItem {
	private String author;
	private String publisher;
	private String ISBN;
	
	public void setAttributes(String title, String author, String publisher, String ISBN, String location, boolean rentable, boolean purchaseable, double price) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
		this.price = price;
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
	
	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable for $" + price  : "not purchaseable";
		return "HARDCOVER BOOK SUMMARY:\n\tTitle: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher + ", ISBN: " + this.ISBN 
				+ ".\n\tLocation: " + location + ". This book is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available." ;
	}
}


class Magazine extends PhysicalItem {
	private String publisher;
	
	
	public void setAttributes(String title, String publisher, String location, boolean rentable, boolean purchaseable, double price) {
		this.title = title;
		this.publisher = publisher;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
		this.price = price;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable for $" + price  : "not purchaseable";
		return "MAGAZINE SUMMARY:\n\tTitle: " + this.title + ", Publisher: " + this.publisher + ", Location: " + location
				+ ".\n\tThis magazine is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available.";
	}
}


class CD extends PhysicalItem {
	private String location;
	private boolean rentable;
	private boolean purchaseable;
	private double price;
	
	private int copiesAvail = 20;
	
	public void setAttributes(String title, String location, boolean rentable, boolean purchaseable, double price) {
		this.title = title;
		this.location = location;
		this.rentable = rentable;
		this.purchaseable = purchaseable;
		this.price = price;
	}
	
	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable for $" + price  : "not purchaseable";
		return "CD SUMMARY:\n\tTitle: " + this.title + ", Location: " + location
				+ ".\n\tThis CD is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available.";
	}
}

