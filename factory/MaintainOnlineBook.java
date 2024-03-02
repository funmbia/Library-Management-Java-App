package factory;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainOnlineBook {
	public ArrayList<OnlineBook> onlinebooks = new ArrayList<OnlineBook>();
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			OnlineBook o = new OnlineBook();
			o.setAttributes(reader.get("title"), reader.get("author"), reader.get("publisher"), reader.get("URL"), reader.get("ISBN"));
			onlinebooks.add(o);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
			
				//headers
				csvOutput.write("title");
				csvOutput.write("author");
				csvOutput.write("publisher");
		    	csvOutput.write("URL");
		    	csvOutput.write("ISBN");
				csvOutput.endRecord();
				//content
				for(OnlineBook o: onlinebooks){
					csvOutput.write(o.getTitle());
					csvOutput.write(o.getAuthor());
					csvOutput.write(o.getPublisher());
					csvOutput.write(o.getURL());
					csvOutput.write(o.getISBN());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
