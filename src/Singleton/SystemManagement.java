package Singleton;
 
import observer.MaintainUser;
import observer.User;
import factory.LibraryItem;
import factory.PhysicalItem;

import java.util.ArrayList;

public class SystemManagement extends yorkMembers{

	 
	 private String csvFilePath = "Library-Management-Java-App/csv files/userInfo.csv";
    public static ArrayList<User> SystemMembers = new ArrayList<>(); //all users
    public static ArrayList<LibraryItem> itemList = new ArrayList<>();
    private static SystemManagement systemInstance;
   

    public static SystemManagement getSystemInstance(){
    	if (systemInstance == null) {
    		systemInstance = new SystemManagement();
    	}
        return systemInstance;
    }

    private SystemManagement(){}

    public void createAccount(User u){
        SystemMembers.add(u);
    }
    
    public static User getAccount(String email) {	
		for(int i=0; i<SystemMembers.size(); i++) {
			if(SystemMembers.get(i).getEmail().equals(email)) {
				return SystemMembers.get(i);
			}
		}
		return null;
	}
 
    public User loginUser(String email, String password) throws Exception{
    	MaintainUser maintain = new MaintainUser(csvFilePath);
    	maintain.load();
    	SystemMembers = maintain.getUsers();
    	
    	for (User u : SystemMembers) {
    		if (u.getEmail().equals(email.trim()) && u.getPassword().equals(password.trim())) return SystemMembers.get(SystemMembers.indexOf(u));
    	}
    	return null;
    }

    public void additem(LibraryItem item){
    	//TODO add to database
        itemList.add(item);
    }
    

    public void enableItem(PhysicalItem item){
    	item.setRentable(true);
        if(!itemList.contains(item)){
            itemList.add(item);
        }
        
    }

    public void disableItem(PhysicalItem item){
    	item.setRentable(false);
        itemList.remove(item);
    }


}
