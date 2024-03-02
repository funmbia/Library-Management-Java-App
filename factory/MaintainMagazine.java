package factory;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainMagazine {
	public ArrayList<Magazine> magazines = new ArrayList<Magazine>();
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			Magazine m = new Magazine();
			m.setAttributes(reader.get("title"), reader.get("publisher"), reader.get("location"), 
					Boolean.valueOf(reader.get("rentable")), Boolean.valueOf(reader.get("purchaseable"))) ;
			m.setCopiesAvail(Integer.valueOf(reader.get("copiesAvail")));
			magazines.add(m);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
			
				//headers
				csvOutput.write("title");
				csvOutput.write("publisher");
				csvOutput.write("location");
		    	csvOutput.write("rentable");
		    	csvOutput.write("purchaseable");
		    	csvOutput.write("copiesAvail");
				csvOutput.endRecord();
				//content
				for(Magazine x: magazines){
					csvOutput.write(x.getTitle());
					csvOutput.write(x.getPublisher());
					csvOutput.write(x.getLocation());
					csvOutput.write(String.valueOf(x.getRentable()));
					csvOutput.write(String.valueOf(x.getPurchaseable()));
					csvOutput.write(String.valueOf(x.getCopiesAvail()));
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
