package observer;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import builder.RentalOrder;
import gui.ActionPage;

public class MaintainUser {
	public ArrayList<User> users = new ArrayList<>();
	
	public String path;

	public MaintainUser(String path) {
		this.path = path;
	}

	public void load() throws Exception {
		CsvReader reader = new CsvReader(path);
		reader.readHeaders();

		while (reader.readRecord()) {
			User user = new User();
			user.setDatabaseAttributes(reader.get("name"), reader.get("email"), reader.get("password"), reader.get("accountType"),
					parseInteger(reader.get("itemsOut")), parseInteger(reader.get("itemsOverdue")), Double.parseDouble(reader.get("penalty")));

			String[] rentingIds = reader.get("currentlyRenting").split(",");
			List<RentalOrder> rentingOrders = new ArrayList<>();
			for (String id : rentingIds) {
				if (!id.isEmpty()) { 
					RentalOrder order = getOrderById(id);
					if (order != null) {
						rentingOrders.add(order);
					}
				}
			}
			user.setCurrentlyRenting(rentingOrders);

			users.add(user);
		}
		reader.close();
	}

	public RentalOrder getOrderById(String id) {
		for (RentalOrder order : ActionPage.allRentalOrders) {
			if (order.getOrderID() == Integer.valueOf(id)) { 
				return order;
			}
		}
		return null;
	}

	public void update() throws Exception {
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

			for (User u : users) {
				csvOutput.write(u.getName());
				csvOutput.write(u.getEmail());
				csvOutput.write(u.getPassword());
				csvOutput.write(u.getAccountType());

				StringBuilder builder = new StringBuilder();
				for (RentalOrder r : u.getCurrentlyRenting()) {
					builder.append(r.getOrderID()).append(",");
				}
				if (builder.length() > 0) {
					builder = new StringBuilder(builder.substring(0, builder.length() - 1)); // Remove the last comma
				}
				csvOutput.write(builder.toString());

				csvOutput.write(String.valueOf(u.getItemsOut()));
				csvOutput.write(String.valueOf(u.getItemsOverdue()));
				csvOutput.write(String.valueOf(u.getPenalty()));
				csvOutput.endRecord();
			}
			csvOutput.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add a new user
	public void addUser(User user) {
		users.add(user);
	}

	// Update an existing user's information
	public void updateUserRenting(User user) {
		for (User userInDatabase : users) {
			if (userInDatabase.equals(user)) {
				userInDatabase.currentlyRenting = user.currentlyRenting;
			}
		}
	}

	// Remove a user
	public void deleteUser(User user) {
		users.remove(user);
	}

	// Getter for users
	public ArrayList<User> getUsers() {
		return users;
	}

	// Setter for path
	public void setPath(String path) {
		this.path = path;
	}

	// Method to parse integers from strings
	private int parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing integer: " + e.getMessage() + " (Input: " + value + ")");
			return -1; 
		}
	}

}
