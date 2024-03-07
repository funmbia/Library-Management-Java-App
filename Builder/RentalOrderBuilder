public class RentalOrderBuilder extends OrderBuilder{

	private boolean canBorrow;
	RentalOrder order;
	User user;

	public String addToOrder(rentPhysicalItem r){
		if (canBorrow==true) {
		order.items.add(r.itemToRent);}
	}
	
	public void updateUserHistory() {
		user.currentlyRenting.add(order);
	}
	
	public RentalOrder returnOrder() {
		return order;
	}
	
	/*should we add interface so the diff classes can acess same list?*/
	public  void updateInventory(LibraryItem item) {
		item.copiesAvail=-1;
	  }

	    public  String getLocations(){
	    	return  order.locations;
	    }
}
