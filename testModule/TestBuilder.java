package testModule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import factory.PhysicalItem;
import org.junit.jupiter.api.Test;

import command.Invoker;
import command.PurchasePhysicalItem;
import builder.RentalOrder;
import builder.RentalOrderBuilder;
import builder.PurchaseOrder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

import observer.User;
import command.RentPhysicalItem;

 public  class TestBuilder {
	

//purchase order done
	     @Test
	     public void testGetOrderID() {
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", new ArrayList<>(), 50);
	         assertEquals(123, purchaseOrder.getOrderID());
	     }

	     @Test
	     public void testGetUserEmail() {
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", new ArrayList<>(), 50);
	         assertEquals("example@example.com", purchaseOrder.getUserEmail());
	     }

	     @Test
	     public void testSetUserEmail() {
	         PurchaseOrder purchaseOrder = new PurchaseOrder();
	         purchaseOrder.setUserEmail("newemail@example.com");
	         assertEquals("newemail@example.com", purchaseOrder.getUserEmail());
	     }

	     @Test
	     public void testGetItems() {
	         List<PhysicalItem> items = new ArrayList<>();
	         items.add(new PhysicalItem());
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", items, 50);
	         assertEquals(items, purchaseOrder.getItems());
	     }

	     @Test
	     public void testSetItems() {
	         List<PhysicalItem> items = new ArrayList<>();
	         items.add(new PhysicalItem());
	         PurchaseOrder purchaseOrder = new PurchaseOrder();
	         purchaseOrder.setItems(items);
	         assertEquals(items, purchaseOrder.getItems());
	     }

	  
		@Test
	     public void testGetPrice() {
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", new ArrayList<>(), 50);
	         assertEquals(50, purchaseOrder.getPrice(),0);
	     }

		@Test
	     public void testSetPrice() {
	         PurchaseOrder purchaseOrder = new PurchaseOrder();
	         purchaseOrder.setPrice(60);
	         assertEquals(60, purchaseOrder.getPrice(),0);
	     }

	     @Test
	     public void testPayWithValidMethod() {
	         List<PhysicalItem> items = new ArrayList<>();
	         items.add(new PhysicalItem());
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", items, 50);
	         assertEquals("Payment successful! You can pick up your items from the library front desk. Thank You!", purchaseOrder.pay("debit"));
	         assertEquals(0, purchaseOrder.getItems().size()); // Check if items list is cleared after payment
	     }

	     @Test
	     public void testPayWithInvalidMethod() {
	         List<PhysicalItem> items = new ArrayList<>();
	         items.add(new PhysicalItem());
	         PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", items, 50);
	         assertEquals("Not an applicable payment method, please try again.", purchaseOrder.pay("paypal"));
	         assertEquals(1, purchaseOrder.getItems().size()); // Check if items list is unchanged after invalid payment
	     }
	     
	    

	         @Test
	         public void testPayWithValidMethodItemsCleared() {
	             // Create a purchase order with one item and a price of 50
	             List<PhysicalItem> items = new ArrayList<>();
	             items.add(new PhysicalItem());
	             PurchaseOrder purchaseOrder = new PurchaseOrder(123, "example@example.com", items, 50);

	             // Make payment with a valid method
	             purchaseOrder.pay("debit");

	             // Check if items list is cleared after payment
	             assertEquals(0, purchaseOrder.getItems().size());
	         }
	     


	
// rental order done

	         @Test
	         public void testSetDatabaseAttributes() {
	             // Create a RentalOrder instance
	        	 User user=new User(0, 0, null, null, null, "test","test@example.com", null, null, null);
	             RentalOrder order = new RentalOrder(0, null, null, null, null, user);

	             // Define values for the attributes
	             int orderID = 123;
	             String userEmail = "test@example.com";
	             List<PhysicalItem> items = new ArrayList<>();
	             items.add(new PhysicalItem());  // Add a dummy item for testing
	             String locations = "Library";
	             Date dueDate = new Date();
	             boolean closed = true;
	             
                 
	             // Call setDatabaseAttributes method
	             order.setDatabaseAttributes(orderID, userEmail, items, locations, dueDate, closed);

	             // Verify that attributes are correctly set
	             assertEquals(orderID, order.getOrderID());
	             assertEquals(items, order.getItems());
	             assertEquals(locations, order.getLocations());
	             assertEquals(dueDate, order.getDueDate());
	             assertEquals(closed, order.getClosed());
	           assertEquals(userEmail, order.getUserEmail());
	         }
	     
	         @Test
	         public void testGetOrderIDrentalOrder() {
	             // Test getOrderID method
	             RentalOrder order = new RentalOrder();
	             int orderId = order.getOrderID();
	             assertTrue(orderId > 0);
	         }

	         @Test
	         public void testGetUserEmailrentalOrder() {
	             // Test getUserEmail method
	             User user = new User(0,0, null, null, null, "test","test@example.com", null, null, null);
	             RentalOrder order = new RentalOrder(100, "test@example.com", new ArrayList<>(), "Location1", new Date(), user);
	             assertEquals("test@example.com", order.getUserEmail());
	         }

	         @Test
	         public void testGetItemsrentalOrder() {
	             // Test getItems method
	             List<PhysicalItem> items = new ArrayList<>();
	             items.add(new PhysicalItem());
	             items.add(new PhysicalItem());
	             User user = new User();
	             RentalOrder order = new RentalOrder(100, "test@example.com", items, "Location1", new Date(), user);
	             assertEquals(items, order.getItems());
	         }

	         @Test
	         public void testGetLocationsrentalOrder() {
	             // Test getLocations method
	             User user = new User();
	             RentalOrder order = new RentalOrder(100, "test@example.com", new ArrayList<>(), "Location1", new Date(), user);
	             assertEquals("Location1", order.getLocations());
	         }

	         @Test
	         public void testGetDueDate() {
	             // Test getDueDate method
	             Date dueDate = new Date();
	             User user = new User();
	             RentalOrder order = new RentalOrder(100, "test@example.com", new ArrayList<>(), "Location1", dueDate, user);
	             assertEquals(dueDate, order.getDueDate());
	         }
	        
	       
	         @Test
	         public void testGetClosed() {
	             // Test getClosed method
	             User user = new User();
	             RentalOrder order = new RentalOrder(100, "test@example.com", new ArrayList<>(), "Location1", new Date(), user);
	             assertFalse(order.getClosed());
	         }

	         @Test
	         public void testSetUserEmailRentalOrder() {
	             // Test setUserEmail method
	        	 User user=new User(1,6, null, null, null, null, "newemail@example.com", null, null, null);
	        	
	             RentalOrder order = new RentalOrder(1,"",null, null, null,  user);
	             order.setUserEmail("newemail@example.com");
	             assertEquals("newemail@example.com", order.getUserEmail());
	         }

	         @Test
	         public void testSetItemsRentalOrder() {
	             // Test setItems method
	             List<PhysicalItem> items = new ArrayList<>();
	             items.add(new PhysicalItem());
	             items.add(new PhysicalItem());
	             RentalOrder order = new RentalOrder();
	             order.setItems(items);
	             assertEquals(items, order.getItems());
	         }

	         @Test
	         public void testSetDueDate() {
	             // Test setDueDate method
	             Date dueDate = new Date();
	             RentalOrder order = new RentalOrder();
	             order.setDueDate(dueDate);
	             assertEquals(dueDate, order.getDueDate());
	         }

	     
	         @Test
	         public void testIsOverdueWithNullDueDate() {
	             // Create a RentalOrder instance with null due date
	             RentalOrder order = new RentalOrder();
	             order.setDueDate(null);

	             // Check if the order is overdue
	             assertFalse(order.isOverdue());
	         }

	         @Test
	         public void testIsOverdueWithFutureDueDate() {
	             // Test isOverdue method with future due date
	             Calendar calendar = Calendar.getInstance();
	             calendar.add(Calendar.DAY_OF_MONTH, 1); // Add 1 day to the current date
	             Date futureDate = calendar.getTime();
	             RentalOrder order = new RentalOrder();
	             order.setDueDate(futureDate);
	             assertFalse(order.isOverdue());
	         }

	         @Test
	         public void testIsOverdueWithPastDueDate() {
	             // Test isOverdue method with past due date
	             Calendar calendar = Calendar.getInstance();
	             calendar.add(Calendar.DAY_OF_MONTH, -1); // Subtract 1 day from the current date
	             Date pastDate = calendar.getTime();
	             RentalOrder order = new RentalOrder();
	             order.setDueDate(pastDate);
	             assertTrue(order.isOverdue());
	         }

	         @Test
	         public void testClose() {
	             // Test close method
	        	 List<PhysicalItem> itemsc = new ArrayList<>();
	             User user = new User();
	             PhysicalItem item1 = new PhysicalItem();
	             PhysicalItem item2 = new PhysicalItem();
	             
	             itemsc.add(item1);
	             itemsc.add(item2);
	             RentalOrder order = new RentalOrder(100, "test@example.com", itemsc, "Location1", new Date(), user);
	             order.close();

	             assertTrue(order.getClosed());
	             assertEquals(0, user.getCurrentlyRenting().size());
	             assertEquals(21, item1.getCopiesAvail());
	             assertEquals(21, item2.getCopiesAvail());
	             assertEquals(-2, user.getItemsOut());
	             assertTrue(order.getItems().isEmpty());
	         }


	         
//test for rentalorderbuilder 3 more 
	         
	         @Test
	         public void testAddToOrder() {
	             // Test adding an item to the order
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             PhysicalItem item = new PhysicalItem();
	             RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, item, builder);
	             rentPhysicalItem.itemToRent.setTitle("Item1");
	             String result = builder.addToOrder(rentPhysicalItem);

	             assertEquals("'Item1' is added to your order!", result);
	           
	         }
	         @Test
	         public void testAddToOrderWhenCannotBorrow() {
	             // Test addToOrder method when user cannot borrow more items
	             User user = new User(4, 0, null, null, null, null, null, null, null, null);
	            // user.setBorrowingPrivileges(false); // Set borrowing privileges to false
	             
	             // Create a rental order builder
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             
	             // Create a physical item to rent
	             PhysicalItem itemToRent = new PhysicalItem();
	             itemToRent.setTitle("Item1");
	             itemToRent.setLocation("Location1");
	            
	             // Create a rent physical item command
	             RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, builder);
	             
	             // Add item to order
	             String result = builder.addToOrder(rentPhysicalItem);

	             // Verify that item is not added to the order and appropriate message is returned
	             assertEquals("You cannot borrow any more items right now", result);
	             // Ensure no items are added to the order
	         }
	         //set user is kind of redundant?
	         @Test
	         public void testSetUser() {
	             // Test setUser method
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user); // Create a builder with null user initially

	             // Set user using setUser method
	             builder.setUser(user);

	             // Verify that the user attribute is correctly set
	             assertEquals(user, builder.user);
	         }  

	         @Test
	         public void testAddToOrderWithEmptyLocation() {
	             // Test adding an item to the order with empty location
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             PhysicalItem item = new PhysicalItem();
	             RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, item, builder);
	             rentPhysicalItem.itemToRent.setTitle("Item1");
	             rentPhysicalItem.itemToRent.setLocation(""); // Set location to empty string

	             String result = builder.addToOrder(rentPhysicalItem);

	             assertEquals("'Item1' is added to your order!", result); // Expecting item to be added despite empty location
	            
	         }

	         @Test
	         public void testAddToOrderWithMultipleItems() {
	             // Test adding multiple items to the order
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             PhysicalItem item1 = new PhysicalItem();
	             PhysicalItem item2 = new PhysicalItem();
	             RentPhysicalItem rentPhysicalItem1 = new RentPhysicalItem(user, item1, builder);
	             RentPhysicalItem rentPhysicalItem2 = new RentPhysicalItem(user, item2, builder);
	             rentPhysicalItem1.itemToRent.setTitle("Item1");
	             rentPhysicalItem2.itemToRent.setTitle("Item2");

	             String result1=builder.addToOrder(rentPhysicalItem1);
	            String result2= builder.addToOrder(rentPhysicalItem2);

	            assertEquals("'Item1' is added to your order!", result1); 
	            assertEquals("'Item2' is added to your order!", result2); // Expecting both items to be added to the order
	         }

	   
	       
	         @Test
	         public void testUpdateInventory() {
	             // Test updating inventory
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             PhysicalItem item = new PhysicalItem();
	             item.setCopiesAvail(2);

	             builder.updateInventory(item);

	             assertEquals(1, item.getCopiesAvail());
	         }

	         @Test
	         public void testGetLocations() {
	             // Test getting locations
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);
	             PhysicalItem item1 = new PhysicalItem();
	             PhysicalItem item2 = new PhysicalItem();
	             RentPhysicalItem r = new RentPhysicalItem(user, item1, builder);
	             RentPhysicalItem s=new RentPhysicalItem(user, item2, builder);
	             r.itemToRent.setLocation("Location1");
	             s.itemToRent.setLocation("Location2");
	             builder.addToOrder(r);
	             builder.addToOrder(s);

	             assertEquals(" Location1;  Location2; ", builder.getLocations());
	         }
	         @Test
	         public void testGetLocationsWithNoItems() {
	             // Test getting locations with no items in the order
	             User user = new User();
	             RentalOrderBuilder builder = new RentalOrderBuilder(user);

	             String locations = builder.getLocations();

	             assertEquals("", locations); // Ensure empty string is returned when no items are in the order
	         }
	         @Test
	         public void testUpdateInventoryWithNonExistentItem() {
	        	    // Test updating inventory with non-existent item
	        	    User user = new User();
	        	    RentalOrderBuilder builder = new RentalOrderBuilder(user);
	        	    PhysicalItem item = new PhysicalItem(); // Non-existent item, not added to order

	        	    builder.updateInventory(item);

	        	    // Ensure no error occurs when updating the inventory of a non-existent item
	        	    // This test checks if the method gracefully handles such a scenario
	        	}
	         @Test
	         public void testAddToOrderWhenMaxItemsBorrowed() {
	        	    // Test addToOrder method when user has already borrowed the maximum number of items
	        	    User user = new User(10, 0, null, null, null, null, null, null, null, null); // Assuming user already has 6 items borrowed
	        	    RentalOrderBuilder builder = new RentalOrderBuilder(user);
	        	    PhysicalItem item = new PhysicalItem();
	        	    RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, item, builder);
	        	    
	        	    String result = builder.addToOrder(rentPhysicalItem);

	        	    assertEquals("You cannot borrow any more items right now", result);
	        	}

	         /*@Test
		        public void testUpdateUserHistory() throws Exception {
		             // Test updating user history
		             User user = new User();
		             RentalOrderBuilder builder = new RentalOrderBuilder(user);
		             PhysicalItem item = new PhysicalItem();
		             RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, item, builder);
		             builder.addToOrder(rentPhysicalItem);
		             builder.updateUserHistory();

		             assertEquals(1, user.getCurrentlyRenting().size());
		         }

		     

		        	   @Test
		        	    public void testReturnOrder() throws Exception {
		        	        // Test returning the order
		        	        User user = new User();
		        	        RentalOrderBuilder builder = new RentalOrderBuilder(user);
		        	        PhysicalItem item = new PhysicalItem();
		        	        RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, item, builder);
		        	        builder.addToOrder(rentPhysicalItem);

		        	        // Return the order
		        	        RentalOrder returnedOrder = builder.returnOrder();

		        	        // Verify that the returned order is closed and removed from user's currentlyRenting list
		        	        assertTrue(returnedOrder.getClosed()); // Check if order is closed
		        	        assertEquals(0, user.getCurrentlyRenting().size()); // Check if currentlyRenting list is empty
		        	    }*/
	         
	         
	         //test case for purchase order builder done 
	         
	       

	             @Test
	             public void testAddToOrderPOB() {
	                 // Test addToOrder method
	                 User user = new User(0, 0, null, null,null, "test", "test@.com", null, null, null);
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, item, builder);
	                 purchasePhysicalItem.itemToBuy.setTitle("Item1");
	                  builder.order.setPrice(95.0);
	                 purchasePhysicalItem.itemToBuy.setPrice(95.0);
	                
	                 builder.applyDiscounts(purchasePhysicalItem.itemToBuy);
	       
	                String result = builder.addToOrder(purchasePhysicalItem);
	                 assertEquals("Item1 is added to your order!",result);
	                 assertEquals(95.0, builder.order.getPrice(), 0.001); // Expected price after discount
	                 assertEquals(1, builder.order.getItems().size());
	             }
	         
	          
	             @Test
	             public void testApplyDiscountsWithPurchaseableItem() {
	                 // Test applyDiscounts method with purchaseable item
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 item.setPurchaseable(true);
	                 item.setPrice(95);
	                 builder.setDiscount(0.05);
	                 PurchasePhysicalItem item1=new PurchasePhysicalItem(user,item, builder);
	                 builder.addToOrder(item1);

	                 assertEquals(90.25, builder.order.getPrice(), 0.001); // Expected price after discount
	             }
	             @Test
	             public void testApplyDiscountsWithPurchaseableItemAndNoDiscount() {
	                 // Test applyDiscounts method with purchaseable item and no discount
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 
	                 // Create a purchasable item with price and set to purchaseable
	                 PhysicalItem item = new PhysicalItem();
	                 item.setPurchaseable(true);
	                 item.setPrice(100); // Original price
	                 builder.setDiscount(0.0); // No discount

	                 // Add the item to the order and apply discount
	                 PurchasePhysicalItem purchaseItem = new PurchasePhysicalItem(user, item, builder);
	                 builder.addToOrder(purchaseItem);

	                 // Verify the price remains unchanged without discount
	                 assertEquals(100.0, builder.order.getPrice(), 0.001); // Expected price remains the same
	             }

	             @Test
	             public void testApplyDiscountsWithNonPurchaseableItem() {
	                 // Test applyDiscounts method with non-purchaseable item
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 item.setPrice(95.0);
	               
	                 item.setPurchaseable(false);
	                 builder.setDiscount(0.05);
	                 
	                 builder.applyDiscounts(item);

	                 assertEquals(0.0, builder.order.getPrice(), 0.001); // Expected price unchanged
	             }

	             @Test
	             public void testReturnOrderPOB() {
	                 // Test returnOrder method
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, item, builder);
	                 builder.addToOrder(purchasePhysicalItem);
	                 
	                 PurchaseOrder returnedOrder = builder.returnOrder();

	                 assertEquals(builder.order, returnedOrder);
	             }

	             @Test
	             public void testUpdateInventoryPOB() {
	                 // Test updateInventory method
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 item.setCopiesAvail(2);

	                 builder.updateInventory(item);

	                 assertEquals(1, item.getCopiesAvail());
	             }
	            
	             @Test
	             public void testSetDiscount() {
	                 // Test setDiscount method
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);

	                 builder.setDiscount(0.1);

	                 assertEquals(0.1, builder.discount, 0.001); // Expected discount value
	             }

	           

	             @Test
	             public void testApplyDiscountsWithZeroDiscount() {
	                 // Test applyDiscounts method with zero discount
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 item.setPurchaseable(true);
	                 item.setPrice(100.0);
	                 builder.setDiscount(0.0);
	                 PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, item, builder);

	                 builder.addToOrder(purchasePhysicalItem);

	                 assertEquals(100.0, builder.order.getPrice(), 0.001); // Expected price remains unchanged (no discount applied)
	             }

	             @Test
	             public void testUpdateInventoryWithNegativeCopiesAvail() {
	                 // Test updateInventory method with negative copies available
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);
	                 PhysicalItem item = new PhysicalItem();
	                 item.setCopiesAvail(0); // Ensure initial copies available is 0

	                 builder.updateInventory(item);

	                 assertEquals(-1, item.getCopiesAvail()); // Expected copies available should be -1
	             }

	             @Test
	             public void testSetDiscountWithNegativeDiscount() {
	                 // Test setDiscount method with negative discount
	                 User user = new User();
	                 PurchaseOrderBuilder builder = new PurchaseOrderBuilder(user);

	                 builder.setDiscount(-0.1);

	                 assertEquals(-0.1, builder.discount, 0.001); // Expected discount value should be -0.1
	             }
	         

	        
	             
	             //test case for order builder
	     

	                 @Test
	                 public void testGetUserEmailOB() {
	                     // Test getUserEmail method
	                     User user = new User(0, 0, null, null, null,  "Test User", "test@example.com", null, null, null);
	                     OrderBuilder builder = new RentalOrderBuilder(user);

	                     assertEquals("test@example.com", builder.getUserEmail());
	                 }

	                 @Test
	                 public void testGetItemCountWithNullItemsOB() {
	                     // Test getItemCount method with null items
	                     User user = new User(0, 0, null, null, null,  "Test User", "test@example.com", null, null, null);
	                     OrderBuilder builder = new RentalOrderBuilder(user);

	                     assertEquals(0, builder.getItemCount());
	                 }

	                 @Test
	                 public void testGetItemCountWithNonEmptyItemsOB() {
	                     // Test getItemCount method with non-empty items
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     builder.items = new ArrayList<>();
	                     builder.items.add(new PhysicalItem());
	                     builder.items.add(new PhysicalItem());

	                     assertEquals(2, builder.getItemCount());
	                 }

	                 @Test
	                 public void testSetUserEmailOB() {
	                     // Test setUserEmail method
	                     User user = new User(0, 0, null, null, null,  "Test User", "test@example.com", null, null, null);
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     builder.setUserEmail("newemail@example.com");

	                     assertEquals("newemail@example.com", builder.getUserEmail());
	                 }

	                 @Test
	                 public void testGetDueDateOB() {
	                     // Test getDueDate method
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     Date dueDate = new Date();
	                     builder.setDueDate(dueDate);

	                     assertEquals(dueDate, builder.getDueDate());
	                 }

	                 @Test
	                 public void testSetDueDateOB() {
	                     // Test setDueDate method
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     Date dueDate = new Date();
	                     builder.setDueDate(dueDate);

	                     assertEquals(dueDate, builder.getDueDate());
	                 }

	                 @Test
	                 public void testGetUserEmailWithNullUser() {
	                     // Test getUserEmail method with null user
	                     User user = new User(0, 0, null, null, null, null, null, null, null, null);
	                     OrderBuilder builder = new RentalOrderBuilder(user);

	                     assertNull(builder.getUserEmail());
	                 }

	                 @Test
	                 public void testGetItemCountWithEmptyItems() {
	                     // Test getItemCount method with empty items list
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     builder.items = new ArrayList<>();

	                     assertEquals(0, builder.getItemCount());
	                 }

	                 @Test
	                 public void testGetItemCountWithNonEmptyItems() {
	                     // Test getItemCount method with non-empty items
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     builder.items = new ArrayList<>();
	                     builder.items.add(new PhysicalItem());
	                     builder.items.add(new PhysicalItem());

	                     assertEquals(2, builder.getItemCount());
	                 }

	                 @Test
	                 public void testSetDueDateWithNull() {
	                     // Test setDueDate method with null due date
	                     User user = new User();
	                     OrderBuilder builder = new RentalOrderBuilder(user);
	                     Date dueDate = null;
	                     builder.setDueDate(dueDate);

	                     assertNull(builder.getDueDate());
	                 }
	             }


	     
 

	 




