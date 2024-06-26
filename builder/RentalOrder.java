package builder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import observer.User;
import factory.PhysicalItem;
import gui.ActionPage;

public class RentalOrder {
	public static int idAllocater = 1; 
	//public static ArrayList<RentalOrder> allOrders = new ArrayList<>();
	
	private int orderID;
	private String userEmail;
    public List<PhysicalItem> items;
    public String locations;
    public Date dueDate;
    public User user;
    private boolean closed = false;

    // Default constructor
    public RentalOrder() {
        // Initialize the items list in the constructor
        items = new ArrayList<>();
        orderID = idAllocater;
    	idAllocater ++;
    	ActionPage.allRentalOrders.add(this);
    	locations = "";
    	LocalDate today = LocalDate.now();
    	LocalDate dueLocalDate = today.plusDays(30);
    	dueDate = Date.from(dueLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    // Constructor with parameters
    public RentalOrder(int orderID, String userEmail, List<PhysicalItem> items, String locations, Date dueDate, User user) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.items = items;
        this.locations = locations;
        this.dueDate = dueDate;
        this.user = user;
    }
    
    public void setDatabaseAttributes(int orderID, String userEmail, List<PhysicalItem> items, String locations, Date dueDate, boolean closed) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.items = items;
        this.locations = locations;
        this.dueDate = dueDate;
        this.closed = closed;
    }
	
    // Getter for orderID
    public int getOrderID() {
        return orderID;
    }

    // Getter and setter methods for userEmail
    public String getUserEmail() {
    	userEmail = user.getEmail();
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter and setter methods for items
    public List<PhysicalItem> getItems() {
        return items;
    }

    public void setItems(List<PhysicalItem> items) {
       this.items = items;
    }

    // Getter for locations
    public String getLocations() {
        return locations;
    }

    // Getter and setter methods for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public boolean getClosed() {
    	return closed;
    }
    
    public boolean isOverdue() {
    	if (dueDate != null) {
        	Date currentDate = new Date();
        	return currentDate.after(dueDate);
    	}
    return false;
}
    
    public void close() { //when the user returns the items
    	user.currentlyRenting.remove(this);
    	
    	for (PhysicalItem p : items) {
    		p.setCopiesAvail(p.getCopiesAvail()+1); //update inventory
    	}
    	
    	user.itemsOut =- this.items.size(); 
    	items.clear();
    	closed = true;
    }
}
