package factory;

public class OnlineBook extends LibraryItem {
	private String author;
	private String publisher;
	private String URL;
	private String ISBN;
	private boolean isAvailable;
    	private boolean isSubscribed;
	
	public void setAttributes(String title, String author, String publisher, String URL, String ISBN) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.URL = URL;
		this.ISBN = ISBN;
	}

	public OnlineBook() {
		super(); 
	}
	
	public OnlineBook(boolean isAvailable, boolean isSubscribed) {
        	this.isAvailable = isAvailable;
        	this.isSubscribed = isSubscribed;
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

	public boolean isAvailable() {
        	return isAvailable;
    	}

   	public boolean isSubscribed() {
        	return isSubscribed;
    	}

    	public String getTitle() {
        	return title;
    	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;	
	}

    	public void setTitle(String title) {
        	this.title = title;
    	}

	@Override
	public String toString() {
		return "ONLINE BOOK SUMMARY:\n\tTitle: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher 
				+ ", \n\tURL: " + this.URL + ", ISBN: " + this.ISBN;
	}
}
