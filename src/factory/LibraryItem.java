package factory;

public class LibraryItem {
	protected String title;
	protected int id;
	protected static int increment = 0;
	
	
	public LibraryItem() {
		increment ++;
		this.id = increment;
	}
	
	public String getTitle() {
		return title; 
	}
	
	public int getID() {
		return id;
	}
	
	public static void resetIncrement() {
		increment = 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
} 