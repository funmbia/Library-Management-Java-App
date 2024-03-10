import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Textbook {

	private static List<Textbook> textbookList = new ArrayList<>();
	String ISBN;
	String textName;
	String edition;
	private Courses course;
	private Date dueDate;
    private static int numberOfAvailableCopies = 0;

	public Textbook(String textName, String ISBN, String edition) {
		this.ISBN = ISBN;
		this.textName = textName;
		this.edition = edition;
		numberOfAvailableCopies++;
	}

	public Textbook(){
	}

	String getEdition(){
		return edition;
	}

	String getTextName(){
		return textName;
	}

	String getISBN(){
		return ISBN;
	}

	public void add(Textbook textbook) {
		textbookList.add(textbook);

	}

	public boolean isNewEditionAvailable() {
        String newestEd = findNewestEd();
        return !edition.equals(newestEd);
    }

    private String findNewestEd() {
        String newestEd = "0";
        for (Textbook textbook : textbookList) {
            if (textbook.getEdition().compareTo(newestEd) > 0) {
            	newestEd = textbook.getEdition();
            }
        }
        return newestEd;
    }

	public boolean isAvailable() {
		return numberOfAvailableCopies > 0;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
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