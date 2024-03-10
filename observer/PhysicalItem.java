import java.util.Date;

public class PhysicalItem {

    private int copiesAvailable = 20;
    private boolean canBeRented = true;
    private String title;
    private Date dueDate;

    public boolean isAvailable() {
        return copiesAvailable > 0 && canBeRented;
    }

    public void setRentalStatus(boolean status) {
        this.canBeRented = status;
    }

    public void decreaseAvailableCopies() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void increaseAvailableCopies() {
        copiesAvailable++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public boolean isOverdue() {
        if (dueDate != null) {
            Date currentDate = new Date();
            return currentDate.after(dueDate);
        }
        return false;
    }

	public Date getDueDate() {
		return dueDate;
	}
}