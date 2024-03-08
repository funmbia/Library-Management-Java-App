package Singleton;


public class yorkMembers {

    public boolean validate(String accountT){
        return accountT.equals("Student") || accountT.equals("facultyM") || accountT.equals("nonFaculty");
    }
}
