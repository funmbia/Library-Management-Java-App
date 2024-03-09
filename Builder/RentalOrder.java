
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalOrder {
    public int orderID;
    public String userEmail;
    public static List<LibraryItem> items;
    public String locations;
    public Date dueDate;

    // Default constructor
    public RentalOrder() {
        // Initialize the items list in the constructor
        items = new ArrayList<>();
    }

    // Constructor with parameters
    public RentalOrder(int orderID, String userEmail, List<LibraryItem> items, String locations, Date dueDate) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.items = items;
        this.locations = locations;
        this.dueDate = dueDate;
    }

    // Getter and setter methods for orderID
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    // Getter and setter methods for userEmail
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter and setter methods for items
    public static List<LibraryItem> getItems() {
        return items;
    }

    public static void setItems(List<LibraryItem> items) {
       this.items = items;
    }

    // Getter and setter methods for locations
    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    // Getter and setter methods for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public void close() {
    	items.clear();
    	
    }
}
