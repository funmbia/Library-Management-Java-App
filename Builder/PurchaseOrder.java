import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {
    private int orderID;
    private String userEmail;
    private List<LibraryItem> items;
    private String locations;
    private int price;

    // Default constructor
    public PurchaseOrder() {
    	 items = new ArrayList<>();
    }

    // Constructor with parameters
    public PurchaseOrder(int orderID, String userEmail, List<LibraryItem> items, String locations, int price) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.items = items;
        this.locations = locations;
        this.price = price;
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
    public List<LibraryItem> getItems() {
        return items;
    }

    public void setItems(List<LibraryItem> items) {
        this.items = items;
    }

    // Getter and setter methods for locations
    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    // Getter and setter methods for price
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void pay(String method) {
    	if (method.equals("debit") {
    		items.clear();
    	       }
    	if(method.equals("credit"){
            items.clear();
              }
    	 if(method.equals("cash"){
             items.clear();
           }
    }
}

