package command;

import builder.RentalOrderBuilder;
import factory.PhysicalItem;
import observer.User;

public class RentPhysicalItem implements Command {
	public String userEmail;
	public User user;
	public PhysicalItem itemToRent;
	RentalOrderBuilder currentRentalOrder;
	
	public RentPhysicalItem(User user, PhysicalItem itemToRent, RentalOrderBuilder currentRentalOrder) {
		this.user = user;
		this.userEmail = user.getEmail();
		this.itemToRent = itemToRent;
		this.currentRentalOrder = currentRentalOrder;
	}
	
	public String execute() {
		return currentRentalOrder.addToOrder(this);
	}
	
	
}
