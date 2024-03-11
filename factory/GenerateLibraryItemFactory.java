package factory;

public class GenerateLibraryItemFactory {

	
	
	public LibraryItem createLibraryItem (String type) {
		if (type.toLowerCase().equals("newsletter") ) return new Newsletter();
		else if (type.toLowerCase().equals("onlinebook")) return new OnlineBook();
		else if (type.toLowerCase().equals("hardcoverbook")) return new HardcoverBook();
		else if (type.toLowerCase().equals("magazine")) return new Magazine();
		else return new CD();
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		GenerateLibraryItemFactory fact = new GenerateLibraryItemFactory();
		CD result = (CD) fact.createLibraryItem("cd");		
		result.setAttributes("Life of the Johnsons", "1st Florr; Shelf G5",  true, true, 12);		
		System.out.println(result.toString());
		
		String path = "/Users/funmbia/Desktop/csv files/newsletters.csv";
		MaintainNewsletter m1 = new MaintainNewsletter();			
		m1.load(path);
		for(LibraryItem x: m1.newsletters){
			System.out.println(x.toString());
		}
		System.out.println("-------------------------------");
		
		path = "/Users/funmbia/Desktop/csv files/onlinebooks.csv";
		MaintainOnlineBook m2 = new MaintainOnlineBook();			
		m2.load(path);
		for(LibraryItem x: m2.onlinebooks){
			System.out.println(x.toString());
		}
		System.out.println("-------------------------------");
		
		path = "/Users/funmbia/Desktop/csv files/hardcoverbooks.csv";
		MaintainHardcoverBook m3 = new MaintainHardcoverBook();			
		m3.load(path);
		for(LibraryItem x: m3.hardcoverbooks){
			System.out.println(x.toString());
		}
		System.out.println("-------------------------------");
		
		path = "/Users/funmbia/Desktop/csv files/magazines.csv";
		MaintainMagazine m4 = new MaintainMagazine();			
		m4.load(path);
		for(LibraryItem x: m4.magazines){
			System.out.println(x.toString());
		}
		System.out.println("-------------------------------");
		
		path = "/Users/funmbia/Desktop/csv files/CDs.csv";
		MaintainCD m5 = new MaintainCD();			
		m5.load(path);
		for(LibraryItem x: m5.cds){
			System.out.println(x.toString());
		}
		System.out.println("-------------------------------");
		
		System.out.println(m5.cds.get(0).getID());
		
//		//FOR ADDING NEW ITEMS
//		GenerateLibraryItemFactory fact = new GenerateLibraryItemFactory();	
//		Newsletter newNewsletter = (Newsletter) fact.createLibraryItem("newsletter");
//		newNewsletter.setAttributes("TLDR", "N/A", "https://tldr.tech/");
//		
//		MaintainNewsletter maintain = new MaintainNewsletter();
//		maintain.load("/Users/funmbia/Desktop/csv files/newsletters.csv");
//		maintain.newsletters.add(newNewsletter);
//		maintain.update("/Users/funmbia/Desktop/csv files/newsletters.csv");
//		
//		for(LibraryItem x: maintain.newsletters){
//			System.out.println(x.toString());
//		}
		
		
	}
	
}
