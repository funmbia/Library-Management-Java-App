package factory;
import java.util.Date;

public class PhysicalItem extends LibraryItem {
	protected String location;
	protected boolean rentable;
	protected boolean purchaseable;
	protected double price;
    public Date dueDate;
	private boolean canBeRented = true;

	protected int copiesAvail = 20;
	
	public PhysicalItem() {
		super();
	}
	
	public boolean getRentable() {
		if (copiesAvail<=0) rentable = false;
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
//
//    	public String getTitle() {
//        	return title;
//    	}

    	public void setTitle(String title) {
        	this.title = title;
    	}

	public boolean isOverdue(Date dueDate) {
        	if (dueDate != null) {
            	Date currentDate = new Date();
            	return currentDate.after(dueDate);
        	}
        return false;
    }

	public Date getDueDate() {
		return dueDate;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setRentable(boolean rentable) {
		this.rentable = rentable;
	}

	public void setPurchaseable(boolean purchaseable) {
		this.purchaseable = purchaseable;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
