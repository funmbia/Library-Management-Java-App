package factory;

public class GenerateLibraryItemFactory {

	
	
	public LibraryItem createItemType (String type) {
		if (type.toLowerCase().equals("newsletter") ) return new Newsletter();
		else if (type.toLowerCase().equals("onlinebook")) return new OnlineBook();
		else if (type.toLowerCase().equals("hardcoverbook")) return new HardcoverBook();
		else if (type.toLowerCase().equals("magazine")) return new Magazine();
		else return new CD();
	}
	
	
	//QUICK TEST OF FACTORY (done for every item type)
	public static void main(String[] args) {
		GenerateLibraryItemFactory fact = new GenerateLibraryItemFactory();
		CD result = (CD) fact.createItemType("cd");
		
		result.setAttributes("Life of the Johnsons", "1st Florr; Shelf G5",  true, true);
		
		System.out.println(result.toString());
	}
	
}
