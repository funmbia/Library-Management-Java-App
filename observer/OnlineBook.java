public class OnlineBook {

    private boolean isAvailable;
    private boolean isSubscribed;
    private String title;

    public OnlineBook(boolean isAvailable, boolean isSubscribed) {
        this.isAvailable = isAvailable;
        this.isSubscribed = isSubscribed;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
