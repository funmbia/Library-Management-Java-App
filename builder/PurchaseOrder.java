package builder;
import java.util.ArrayList;
import java.util.List;

import factory.LibraryItem;
import factory.PhysicalItem;

public class PurchaseOrder {
    public static int idAllocater = 1;

    private int orderID;
    private String userEmail;
    public List<PhysicalItem> items;
    public double price;

    // Default constructor
    public PurchaseOrder() {
        items = new ArrayList<>();
        orderID = idAllocater;
        idAllocater ++;
    }

    // Constructor with parameters
    public PurchaseOrder(int orderID, String userEmail, List<PhysicalItem> items, int price) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.items = items;
        this.price = price;
    }

    // Getter for orderID
    public int getOrderID() {
        return orderID;
    }

    // Getter and setter methods for userEmail
    public String getUserEmail() {
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

    // Getter and setter methods for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double d) {
        this.price = d;
    }

    public String pay(String method) {
        if (method.equals("debit") || method.equals("credit") || method.equals("cash")) {
            items.clear();
            return "Payment successful! You can pick up your items from the library front desk. Thank You!";
        }
        else return "Not an applicable payment method, please try again.";
    }
}
