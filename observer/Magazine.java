package observer;

import factory.PhysicalItem;

public class Magazine extends PhysicalItem {

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
