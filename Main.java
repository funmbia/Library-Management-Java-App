public class Main {
    public static void main(String[] args) throws Exception {
        MaintainUser maintainUser = new MaintainUser();
        User newUser = new User();
        Command addUser = new AddUserCommand(maintainUser, newUser);

        addUser.execute();
    }
}
