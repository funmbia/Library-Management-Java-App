package Singleton;

import database.User;
import factory.LibraryItem;

import java.util.ArrayList;

public class SystemManagement extends yorkMembers{

    public static ArrayList<User> SystemMembers = new ArrayList<>();
    private static final SystemManagement systemInstance = new SystemManagement();

    public static SystemManagement getSystemInstance(){
        return systemInstance;
    }

    private SystemManagement(){}

    public void createAccount(User u){
        SystemMembers.add(u);
    }

    public User loginUser(User u){
        return SystemMembers.get(SystemMembers.indexOf(u));
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
