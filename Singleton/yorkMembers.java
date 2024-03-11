package Singleton;


import java.util.HashMap;
import java.util.Map;

public class yorkMembers {

    public static Map<String,String> members = new HashMap<String,String>();

    //check if registered member already exists
    public boolean validate(String email) {
        return !members.containsKey(email);
    }

}
