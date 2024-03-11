package database;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import builder.RentalOrder;

public class MaintainUser {
	public ArrayList<User> users = new ArrayList<User>();
	public String path;
	
	//reads the file & sets up the users array-list accordingly
	public void load(String path) throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			User user = new User();
			user.setDatabaseAttributes(reader.get("name"), reader.get("email"), reader.get("password"), reader.get("accountType"), 
					Integer.valueOf(reader.get("itemsOut")), Integer.valueOf(reader.get("itemsOverdue")), Integer.valueOf(reader.get("penalty")));
			
			String[] rentingIds = reader.get("currentlyRenting").split(",");
			List<RentalOrder> rentingOrders = new ArrayList<>();
			for (String id : rentingIds) {
			    RentalOrder order = getOrderById(id);
			    if (order != null) {
			        rentingOrders.add(order);
			    }
			}
			user.setCurrentlyRenting(rentingOrders);

			users.add(user);
		}
	}
	
	private RentalOrder getOrderById(String id) {
		for (RentalOrder order : RentalOrder.allOrders) {
			if (order.getOrderID() == Integer.valueOf(id)) {
				return order;
			}
		}
		return null;
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
				
				for(User u: users){
					csvOutput.write(u.getName());
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getType());
					
					String builder = "";
					for (RentalOrder r : u.getCurrentlyRenting()) {
						builder += r.getOrderID() + ",";
					}
					if (builder.length() > 0) {
					    builder = builder.substring(0, builder.length() - 1); //remove the last comma
					}
					csvOutput.write(builder);
					
					
					csvOutput.write(String.valueOf(u.getItemsOut()));
					csvOutput.write(String.valueOf(u.getItemsOverdue()));
					csvOutput.write(String.valueOf(u.getPenaltyToPay()));	
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
