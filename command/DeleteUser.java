public class DeleteUserCommand implements Command {
    private MaintainUser maintainUser;
    private User user;

    public DeleteUserCommand(MaintainUser maintainUser, User user) {
        this.maintainUser = maintainUser;
        this.user = user;
    }

    @Override
    public void execute() {
        maintainUser.getUsers().remove(user);
    }
}
