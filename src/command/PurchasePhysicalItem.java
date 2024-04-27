package command;

import builder.PurchaseOrderBuilder;
import factory.PhysicalItem;
import observer.User;

public class PurchasePhysicalItem implements Command {
	public String userEmail;
	public PhysicalItem itemToBuy;
	public User user;
	public PurchaseOrderBuilder currentPurchaseOrder;
	
	public PurchasePhysicalItem(User user, PhysicalItem itemToBuy, PurchaseOrderBuilder currentPurchaseOrder) {
		this.user = user;
		this.userEmail = user.getEmail();
		this.itemToBuy = itemToBuy;
		this.currentPurchaseOrder = currentPurchaseOrder;
	}
	
	public String execute() {
		return currentPurchaseOrder.addToOrder(this);
	}
	
	
}
