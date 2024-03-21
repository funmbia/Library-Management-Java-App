package Singleton;


import observer.User;

import java.util.ArrayList;

public class yorkMembers {
	//faculty, student, non-faculty

    public static ArrayList<User> members = new ArrayList<>(); 

    //check if registered member already exists
    public boolean validate(User user) {
    	if(! members.contains(user)) {
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
	
	public static void addUserToYorkMembers(User user) {
		members.add(user);
	}

}
