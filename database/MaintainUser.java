package database;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainUser {
	public ArrayList<UserInfo> users = new ArrayList<UserInfo>();
	public String path;
	
	//reads the file & sets up the users array-list accordingly
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			UserInfo user = new UserInfo();
			user.setAttributes(reader.get("name"), reader.get("email"), reader.get("password"), reader.get("accountType"), 
					Integer.valueOf(reader.get("itemsOut")), Integer.valueOf(reader.get("itemsOverdue")), Integer.valueOf(reader.get("penalty")));
			user.setCurrentlyRenting(reader.get("currentlyRenting"));
			users.add(user);
		}
	}
	
	//reprints everything in the users array-list to the file 
	public void update(String path) throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				csvOutput.write("name");
		    	csvOutput.write("email");
				csvOutput.write("password");
				csvOutput.write("accountType");
				csvOutput.write("currentlyRenting");
				csvOutput.write("itemsOut");
				csvOutput.write("itemsOverdue");
				csvOutput.write("penalty");
				csvOutput.endRecord();
				
				for(UserInfo u: users){
					csvOutput.write(u.getName());
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getAccountType());
					csvOutput.write(u.getCurrentlyRenting());
					csvOutput.write(String.valueOf(u.getItemsOut()));
					csvOutput.write(String.valueOf(u.getItemsOverdue()));
					csvOutput.write(String.valueOf(u.getPenalty()));	
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
