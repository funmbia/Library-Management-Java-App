package Singleton;


import observer.User;

import java.util.ArrayList;

public class yorkMembers {

    public static ArrayList<User> members = new ArrayList<>();

    //check if registered member already exists
    public boolean validate(User user) {
        return !members.contains(user);
    }

}
