package builder;

import database.User;
import factory.PhysicalItem;

class rentPhysicalItem {

	PhysicalItem itemToRent;
	//dummy class
}
public class RentalOrderBuilder extends OrderBuilder{

	public RentalOrderBuilder(String email) {
		super(email);
		order = new RentalOrder();
		order.user = this.user;
	}

	private boolean canBorrow;
	private RentalOrder order;
	public User user;

	public String addToOrder(rentPhysicalItem r){
		checkIfCanBorrow();
		if (canBorrow==true) {
			order.items.add(r.itemToRent);
			updateInventory(r.itemToRent);
			order.locations += " " + r.itemToRent.getLocation();
			return r.itemToRent.getTitle() + " is added to your order!";
		}
		else return "You cannot borrow any more items right now";
	}
	
	private void checkIfCanBorrow() {
		if (user.hasBorrowingPrivileges() && (user.itemsOut + order.items.size()) < 10) 
			canBorrow = true;
		else canBorrow = false;
		
	}
	
	public void updateUserHistory() {
		user.currentlyRenting.add(order);
	}
	
	public RentalOrder returnOrder() {
		//TODO add order to database
		updateUserHistory();
		return order;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/*should we add interface so the diff classes can access same list?*/
	public  void updateInventory(PhysicalItem item) {
		item.setCopiesAvail(item.getCopiesAvail()-1);
	}

	public  String getLocations(){
    	return  order.locations;
    }
}
