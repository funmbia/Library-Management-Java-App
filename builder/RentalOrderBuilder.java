package builder;

import observer.User;
import command.RentPhysicalItem;
import factory.PhysicalItem;

public class RentalOrderBuilder extends OrderBuilder{

	public RentalOrderBuilder(User user) {
		super(user);
		order = new RentalOrder();
		this.user = user;
		order.user = this.user;
	}

	private boolean canBorrow;
	private RentalOrder order;
	public User user;

	public String addToOrder(RentPhysicalItem r){
		if (checkIfCanBorrow()) {
			order.items.add(r.itemToRent);
			updateInventory(r.itemToRent);
			order.locations += " " + r.itemToRent.getLocation() + "; ";
			return "'" + r.itemToRent.getTitle() + "' is added to your order!";
		}
		else return "You cannot borrow any more items right now";
	}
	
	private boolean checkIfCanBorrow() {
		if (user.hasBorrowingPrivileges() && (user.itemsOut + order.items.size()) < 10) 
			canBorrow = true;
		else canBorrow = false;
		return canBorrow;
	}
	
	public void updateUserHistory() {
		user.currentlyRenting.add(order);
	}
	
	public RentalOrder returnOrder() {
		updateUserHistory();
		return order;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public  void updateInventory(PhysicalItem item) {
		item.setCopiesAvail(item.getCopiesAvail()-1);
	}

	public  String getLocations(){
    	return  order.locations;
    }
}
