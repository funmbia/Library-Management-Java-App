package Singleton;


import observer.User;

import java.util.ArrayList;

public class yorkMembers {

    public static ArrayList<User> members = new ArrayList<>();

    //check if registered member already exists
    public boolean validate(User user) {
    	if(user.isValidEmail(user.getEmail())&&user.isValidPassword(user.getPassword())&& !members.contains(user)) {
    		return true;
    	}
        return false;
    }

	public static User getMember(String email) {	
		for(int i=0; i<members.size(); i++) {
			if(members.get(i).getEmail().equals(email)) {
				return members.get(i);
			}
		}
		return null;
	}

}
