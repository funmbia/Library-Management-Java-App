package factory;

public class OnlineBook extends LibraryItem {
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
