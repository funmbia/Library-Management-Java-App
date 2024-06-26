package builder;

import java.util.ArrayList;
import java.util.Date;
import factory.LibraryItem;
import factory.PhysicalItem;
import observer.User;

public abstract class OrderBuilder {
    private String userEmail;
    public ArrayList<PhysicalItem> items; //can only purchase physical items
    private int itemCount;
    public Date dueDate;
    public User user;

    // Constructor with userEmail parameter
    public OrderBuilder(User user) {
    	this.user = user;
        this.userEmail = user.getEmail();
        this.items = null; 
        this.itemCount = 0;
    }

    // Abstract method to be implemented by subclasses

    // Getter method for userEmail
    public String getUserEmail() {
        return userEmail;
    }

    // Derived getter method for itemCount
    public int getItemCount() {
    	
        return (items == null) ? 0 : items.size();
    }

    // Setter method for userEmail
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter and setter methods for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

