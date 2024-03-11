package Singleton;

import database.UserInfo;

public class registerUser extends yorkMembers {

    UserInfo user = new UserInfo();

    public registerUser(String name, String email, String password, String accountT){
        user.setAttributes(name,email,password,accountT,0,0,0);
        members.put(email, password);
    }

    public void createAccount(){

    }

    private boolean validateNonVisitor(){
        return validate(user.getEmail());
    }

    private void toLogin(){
        
    }
}
