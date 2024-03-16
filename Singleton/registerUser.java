package Singleton;

import observer.User;

public class registerUser extends yorkMembers {

    User user = new User();

    public registerUser(String name, String email, String password, String accountT){
        user.setAttributes(name,email,password,accountT,0,0,0);
        members.add(user);
    }

    public void createAccount(){

    }

    private boolean validateNonVisitor(){
        return validate(user);
    }

    private void toLogin(){
        
    }
}
