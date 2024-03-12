package factory;

public abstract class PhysicalItem extends LibraryItem {
	protected String location;
	protected boolean rentable;
	protected boolean purchaseable;
	protected double price;
	private String title;
        private Date dueDate;
	private boolean canBeRented = true;

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

    	public boolean isAvailable() {
        	return copiesAvail > 0 && canBeRented;
    	}

    	public void setRentalStatus(boolean status) {
        	this.canBeRented = status;
    	}

   	public void decreaseAvailableCopies() {
        	if (copiesAvail > 0) {
            		copiesAvail--;
        	}
    	}

    	public void increaseAvailableCopies() {
        	copiesAvail++;
    	}

    	public String getTitle() {
        	return title;
    	}

    	public void setTitle(String title) {
        	this.title = title;
    	}

	public boolean isOverdue() {
        	if (dueDate != null) {
            		Date currentDate = new Date();
            	return currentDate.after(dueDate);
        	}
        return false;
    }

	public Date getDueDate() {
		return dueDate;
	}
	
	
}

//PHYSICAL ITEMS


class HardcoverBook extends PhysicalItem {
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

