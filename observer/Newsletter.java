public class Newsletter {

    private boolean isSubscribed;
    private String name;

    public Newsletter(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public boolean isAvailable() {
        return isSubscribed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
