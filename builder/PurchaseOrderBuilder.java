package builder;
import factory.PhysicalItem;

class purchasePhysicalItem {
    public PhysicalItem itemToBuy;
    //dummy class

}

public class PurchaseOrderBuilder extends OrderBuilder{

    public PurchaseOrderBuilder(String email) {
        super(email);
        order = new PurchaseOrder();
    }

    public int discount;
    PurchaseOrder order;


    public String addToOrder(purchasePhysicalItem p){
        order.items.add(p.itemToBuy);
        applyDiscounts(p.itemToBuy); //add price of this item (including discounts) to order
        updateInventory(p.itemToBuy);

        return p.itemToBuy.getTitle() + " is added to your order!";
    }

    public void applyDiscounts(PhysicalItem item) {
        if( item.getPurchaseable() ) {
            order.price += item.getPrice() - discount ; //every order has the same discount?
        }
    }

    public PurchaseOrder returnOrder() {
        return order;
    }

    public  void updateInventory(PhysicalItem item) {
        item.setCopiesAvail(item.getCopiesAvail()-1);
    }

    public void setDiscount (int discountAmount) {
        discount = discountAmount; //in CAD
    }

}
