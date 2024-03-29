package builder;
import command.PurchasePhysicalItem;
import factory.PhysicalItem;
import observer.User;

public class PurchaseOrderBuilder extends OrderBuilder{

	public PurchaseOrderBuilder(User user) {
		super(user);
		order = new PurchaseOrder();
		order.userEmail = user.getEmail();
	}
	
	public double discount = 0.05;
	//old code: PurchaseOrder order;
	//made public
	public PurchaseOrder order;
	
	
	
	public String addToOrder(PurchasePhysicalItem p){
		order.items.add(p.itemToBuy);
		applyDiscounts(p.itemToBuy); //add price of this item (including discounts) to order
		updateInventory(p.itemToBuy); 
		
		return p.itemToBuy.getTitle() + " is added to your order!";	
	}
	
	public void applyDiscounts(PhysicalItem item) {
	   if( item.getPurchaseable() ) {
		   order.price += item.getPrice() - (item.getPrice() * discount) ; //every order initially has a 10% discount
	   }
	}
	
	public PurchaseOrder returnOrder() {
		return order;
	}
	
	public  void updateInventory(PhysicalItem item) {
		item.setCopiesAvail(item.getCopiesAvail()-1);
	}
    
    public void setDiscount (double discountPercent) {
    	discount = discountPercent; //in CAD
    }
    
}
