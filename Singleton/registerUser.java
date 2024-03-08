package Singleton;

public class registerUser {
    private String accountType;
    private String email;
    private String password;

    public registerUser(String accountT){
        this.accountType=accountT;
    }

    public void createAccount(){

    }

    private boolean validateNonVisitor(){
        return true;
    }

    private boolean enterEmail(String email){
        return true;
    }

    private boolean createPassword(String password){
        return true;
    }

    private void addAccount(String email, String password, String accountType){

    }
    private void toLogin(){
        
    }
}
