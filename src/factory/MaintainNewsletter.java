package factory;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainNewsletter {
	public ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();
	
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			Newsletter nl = new Newsletter();
			nl.setAttributes(reader.get("title"), reader.get("organization"), reader.get("URL") );
			newsletters.add(nl);
		}
	}
	
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
			
				//headers
				csvOutput.write("title");
				csvOutput.write("organization");
		    	csvOutput.write("URL");
				csvOutput.endRecord();
				//content
				for(Newsletter n: newsletters){
					csvOutput.write(n.getTitle());
					csvOutput.write(n.getOrganization());
					csvOutput.write(n.getURL());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
