package factory;

public abstract class LibraryItem {
	protected String title;
	protected int id;
	protected static int increment;
	
	
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
	
	public abstract String toString();
}