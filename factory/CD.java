package factory;

public class CD extends PhysicalItem {
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

