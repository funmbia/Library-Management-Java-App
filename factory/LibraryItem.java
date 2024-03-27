package factory;

public abstract class LibraryItem {
	protected String title;
	protected int id;
	protected static int increment = 0;
	
	
	protected LibraryItem() {
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
	
	public abstract String toString();
} 