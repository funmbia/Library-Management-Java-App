public class PurchaseOrderBuilder extends OrderBuilder{

	public int discount;
	PurchaseOrder order;
	
	
	
public String addToOrder(purchasePhysicalItem p){
		order.items.add(p.itemToBuy);
		applyDiscounts(p.itemToBuy);
		
	}
	
	public void applyDiscounts(LibraryItem item) {
	   if(item.rentable == false) {
		   item.price=-discount;
	   }
	}
	
	public PurchaseOrder returnOrder() {
		return order;
		
	}
  public  void updateInventory(LibraryItem item) {
	  item.copiesAvail=-1;
  }

    public  String getLocations();{
    	return order.locations
    }
}
