package factory;

import org.junit.jupiter.api.Test;

public class TestSuite {
	
	@Test
	public void testPrintMethods() throws Exception {
		// TEST for the system. Can we load(get) all the items on the csv files?
		//						Can we add new items? (using factory too)
		
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
		
		//FOR ADDING NEW ITEMS
//		GenerateLibraryItemFactory fact = new GenerateLibraryItemFactory();	
//		Newsletter newNewsletter = (Newsletter) fact.getItemType("newsletter");
//		newNewsletter.setAttributes("TLDR", "N/A", "https://tldr.tech/");
//		
//		maintain.newsletters.add(newNewsletter);
//		maintain.update(path);

	}

}
