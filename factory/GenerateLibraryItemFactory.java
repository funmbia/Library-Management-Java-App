package factory;

public class GenerateLibraryItemFactory {

	
	
	public LibraryItem createLibraryItem (String type) {
		if (type.toLowerCase().equals("newsletter") ) return new Newsletter();
		else if (type.toLowerCase().equals("onlinebook")) return new OnlineBook();
		else if (type.toLowerCase().equals("hardcoverbook")) return new HardcoverBook();
		else if (type.toLowerCase().equals("magazine")) return new Magazine();
		else if (type.toLowerCase().equals("cd")) return new CD();
		else return null;
	}
	
}
