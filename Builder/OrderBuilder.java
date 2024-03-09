import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class OrderBuilder {
    private String userEmail;
    public List<LibraryItem> items; 
    private int itemCount;
    public Date dueDate;

    // Constructor with userEmail parameter
    public OrderBuilder(String email) {
        this.userEmail = email;
        this.items = null; 
        this.itemCount = 0;
    }

    // Abstract method to be implemented by subclasses
    private abstract void updateInventory(LibraryItem item);

    // Abstract method to be implemented by subclasses
    private abstract String getLocations();

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

