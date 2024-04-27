package factory;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainHardcoverBook {
	public ArrayList<HardcoverBook> hardcoverbooks = new ArrayList<HardcoverBook>();
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			HardcoverBook h = new HardcoverBook();
			h.setAttributes(reader.get("title"), reader.get("author"), reader.get("publisher"), reader.get("ISBN"), reader.get("location"),
					Boolean.valueOf(reader.get("rentable")), Boolean.valueOf(reader.get("purchaseable")), Double.valueOf(reader.get("price"))) ;
			h.setCopiesAvail(Integer.valueOf(reader.get("copiesAvail")));
			hardcoverbooks.add(h);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
			
				//headers
				csvOutput.write("title");
				csvOutput.write("author");
				csvOutput.write("publisher");
		    	csvOutput.write("ISBN");
		    	csvOutput.write("location");
		    	csvOutput.write("rentable");
		    	csvOutput.write("purchaseable");
		    	csvOutput.write("price");
		    	csvOutput.write("copiesAvail");
				csvOutput.endRecord();
				//content
				for(HardcoverBook x: hardcoverbooks){
					csvOutput.write(x.getTitle());
					csvOutput.write(x.getAuthor());
					csvOutput.write(x.getPublisher());
					csvOutput.write(x.getISBN());
					csvOutput.write(x.getLocation());
					csvOutput.write(String.valueOf(x.getRentable()));
					csvOutput.write(String.valueOf(x.getPurchaseable()));
					csvOutput.write(String.valueOf(x.getPrice()));
					csvOutput.write(String.valueOf(x.getCopiesAvail()));
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
