package Singleton;


import observer.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class yorkMembers {
	//faculty, student, non-faculty

    public static List<User> members = new ArrayList(); 
	public static Set<String> emails = new HashSet();

    //check if registered member already exists
    public static boolean validate(User user) {
		if (! emails.contains(user.getEmail())) {
			addUserToYorkMembers(user);
			emails.add(user.getEmail());
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
