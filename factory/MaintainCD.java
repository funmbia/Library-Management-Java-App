package factory;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainCD {
	public ArrayList<CD> cds = new ArrayList<CD>();

	//get all the info on the csv file and load it to the arraylist
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			CD c = new CD();
			c.setAttributes(reader.get("title"), reader.get("location"), Boolean.valueOf(reader.get("rentable")), Boolean.valueOf(reader.get("purchaseable"))) ;
			c.setCopiesAvail(Integer.valueOf(reader.get("copiesAvail")));
			cds.add(c);
		}
	}

	//print everything in the arraylist to the file; only the content in arraylist will now be in the file
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
			
				//headers
				csvOutput.write("title");
				csvOutput.write("location");
		    	csvOutput.write("rentable");
		    	csvOutput.write("purchaseable");
		    	csvOutput.write("copiesAvail");
				csvOutput.endRecord();
				//content
				for(CD x: cds){
					csvOutput.write(x.getTitle());
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
