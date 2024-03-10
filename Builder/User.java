public class User{
public int itemsOverdue;
public int penaltyToPay;
public List<RentalOrder> currentlyRenting;
public OrderBuilder currentOrder;
public int itemsOut;
private BookCollection bookCollection;
private Recommendation recommendation;

 
    public User() {
    }

    
    public User(int itemsOverdue, int penaltyToPay, List<RentalOrder> currentlyRenting, OrderBuilder currentOrder) {
        this.itemsOverdue = itemsOverdue;
        this.penaltyToPay = penaltyToPay;
        this.currentlyRenting = currentlyRenting;
        this.currentOrder = currentOrder;
    }


    public int getItemsOverdue() {
        return itemsOverdue;
    }

    public void setItemsOverdue(int itemsOverdue) {
        this.itemsOverdue = itemsOverdue;
    }

  
    public int getPenaltyToPay() {
        return penaltyToPay;
    }

    public void setPenaltyToPay(int penaltyToPay) {
        this.penaltyToPay = penaltyToPay;
    }

  
    public List<RentalOrder> getCurrentlyRenting() {
        return currentlyRenting;
    }

    public void setCurrentlyRenting(List<RentalOrder> currentlyRenting) {
        this.currentlyRenting = currentlyRenting;
    }


    public OrderBuilder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderBuilder currentOrder) {
        this.currentOrder = currentOrder;
    }
 public boolean hasBorrowingPrivileges() {
  boolean privileges=true;
   if (itemsOverdue>3){
    privileges =false;
   } 
  return privileges;
 }
 public String displayRentalWarnings(){
 }
}
