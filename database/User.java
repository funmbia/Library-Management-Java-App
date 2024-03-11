package database;

import java.util.List;

import builder.OrderBuilder;
import builder.RentalOrder;
import iterator.Book;
import iterator.BookCollection;
import iterator.Recommendation;

class Invoker {
	//dummy class
}

public class User{
	public List<RentalOrder> currentlyRenting;
	public OrderBuilder currentOrder;
	private BookCollection bookCollection;
	private Recommendation recommendation;
	public Invoker myInvoker;
	
	private String name;
	private String email;
	private String password;
	private String type;
	public int itemsOut;
	public int itemsOverdue;
	public double penaltyToPay;
	
 
    public User() {
    }

    public User(int itemsOverdue, int penaltyToPay, List<RentalOrder> currentlyRenting, OrderBuilder currentOrder,
                String email, String password, String type, Invoker myInvoker) {
        this.itemsOverdue = itemsOverdue;
        this.penaltyToPay = penaltyToPay;
        this.currentlyRenting = currentlyRenting;
        this.currentOrder = currentOrder;
        this.email = email;
        this.password = password;
        this.type = type;
        this.myInvoker = myInvoker;
    }
    
    public void setDatabaseAttributes(String name, String email, String password, String accountType, int itemsOut, int itemsOverdue, int penalty) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = accountType;
		this.itemsOut = itemsOut;
		this.itemsOverdue = itemsOverdue;
		this.penaltyToPay = penalty;
	}

	public double penaltyToPay() {
		  double penalty;
		  penalty=itemsOverdue * 0.5;
		  return penalty;
	}
	  
	public boolean hasBorrowingPrivileges() {
		  boolean privileges=true;
		  if (itemsOverdue>3){
		    privileges =false;
		  } 
		  return privileges;
	}
	 
	public String displayRentalWarnings(){
		 String warning="You have" + itemsOverdue +". Renting more then three books will result in loss of borrowing privileges ";
		 return warning;
	}


    public User(BookCollection bookCollection, Recommendation recommendation) {
        this.bookCollection = bookCollection;
        this.recommendation = recommendation;
    }

    public Book search(String bName) {
        return bookCollection.searchBookByName(bName);
    }

    public void showRecommendations() {
        List<Book> recommendedBooks = recommendation.getRecommendedBooks();

        if (recommendedBooks.isEmpty()) {
            System.out.println("No recommendations available.");
        } else {
            System.out.println("Recommended Books:");
            for (Book book : recommendedBooks) {
                System.out.println(book.getBookTitle());
            }
        }
    }
    
    
    
    //GETTERS AND SETTERS FOR DATABASE ITEMS
    public String getName() {
		return name;
	}
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
    
    public int getItemsOut() {
    	return itemsOut;
    }
    
    public int getItemsOverdue() {
        return itemsOverdue;
    }
    
    public void setItemsOverdue(int itemsOverdue) {
        this.itemsOverdue = itemsOverdue;
    }
    
    public double getPenaltyToPay() {
        return penaltyToPay;
    }
    
    public void setPenaltyToPay(double penaltyToPay) {
        this.penaltyToPay = penaltyToPay;
    }

    
    // OTHER GETTERS AND SETTERS
    public Invoker getMyInvoker() {
        return myInvoker;
    }

    public void setMyInvoker(Invoker myInvoker) {
        this.myInvoker = myInvoker;
    }
  
    public List<RentalOrder> getCurrentlyRenting() {
        return currentlyRenting;
    }

    public void setCurrentlyRenting(List<RentalOrder> currentlyRenting) {
        this.currentlyRenting = currentlyRenting;
    }
    
    public void addToRenting(RentalOrder order) {
		currentlyRenting.add(order);
	}
	
	public void removeFromRenting(RentalOrder order) {
		currentlyRenting.remove(order);
	}

    public OrderBuilder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderBuilder currentOrder) {
        this.currentOrder = currentOrder;
    }

}
