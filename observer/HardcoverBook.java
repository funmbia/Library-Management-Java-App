package observer;

import factory.PhysicalItem;

public class HardcoverBook extends PhysicalItem {
	private String author;
	private String publisher;
	private String ISBN;
	private String title;
	private boolean isRented = false;
	
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

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean rented) {
		isRented = rented;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public boolean isHardcover() {
		return true;
	}
	
	@Override
	public String toString() {
		String r = rentable ? "rentable" : "not rentable";
		String p = purchaseable ? "purchaseable for $" + price  : "not purchaseable";
		return "HARDCOVER BOOK SUMMARY:\n\tTitle: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher + ", ISBN: " + this.ISBN 
				+ ".\n\tLocation: " + location + ". This book is currently " + r + " ; " + p + " ; and there are " + copiesAvail + " copies available." ;
	}
}