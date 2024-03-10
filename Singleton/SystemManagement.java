package Singleton;

import database.UserInfo;
import factory.LibraryItem;

public class SystemManagement {
    private static final SystemManagement systemInstance = new SystemManagement();

    public static SystemManagement getSystemInstance(){
        return systemInstance;
    }

    private SystemManagement(){}

    public void createAccount(String type){

    }

    public UserInfo loginUser(String email, String password){
        return null;
    }

    public void additem(LibraryItem item){

    }

    public void enableItem(LibraryItem item){

    }

    public void disableItem(LibraryItem item){

    }

    public String providePaymentOptions(){
        return "";
    }

}
