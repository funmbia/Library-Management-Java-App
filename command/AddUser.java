
public class AddUserCommand implements Command {
    private MaintainUser maintainUser;
    private User user;

    public AddUserCommand(MaintainUser maintainUser, User user) {
        this.maintainUser = maintainUser;
        this.user = user;
    }

    @Override
    public void execute() {
        maintainUser.getUsers().add(user);
    }
}
