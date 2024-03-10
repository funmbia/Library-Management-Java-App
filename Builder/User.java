public class User{
public int itemsOverdue;
public int penaltyToPay;
public List<RentalOrder> currentlyRenting;
public OrderBuilder currentOrder;
public int itemsOut;
private BookCollection bookCollection;
private Recommendation recommendation;

 
    public User() {
    }

    
    public User(int itemsOverdue, int penaltyToPay, List<RentalOrder> currentlyRenting, OrderBuilder currentOrder) {
        this.itemsOverdue = itemsOverdue;
        this.penaltyToPay = penaltyToPay;
        this.currentlyRenting = currentlyRenting;
        this.currentOrder = currentOrder;
    }


    public int getItemsOverdue() {
        return itemsOverdue;
    }

    public void setItemsOverdue(int itemsOverdue) {
        this.itemsOverdue = itemsOverdue;
    }

  
    public int getPenaltyToPay() {
        return penaltyToPay;
    }

    public void setPenaltyToPay(int penaltyToPay) {
        this.penaltyToPay = penaltyToPay;
    }

  
    public List<RentalOrder> getCurrentlyRenting() {
        return currentlyRenting;
    }

    public void setCurrentlyRenting(List<RentalOrder> currentlyRenting) {
        this.currentlyRenting = currentlyRenting;
    }


    public OrderBuilder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderBuilder currentOrder) {
        this.currentOrder = currentOrder;
    }

 public double penaltyToPay(){
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
}
