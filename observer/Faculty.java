package observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faculty extends User {

	private List<Courses> coursesTeaching= new ArrayList<>();
	private List<Textbook> usedTextbooks= new ArrayList<>();
	private Map<String, List<String>> coursesAndTextbooks = new HashMap<>();
	private Map<Courses, Textbook> CandT = new HashMap<>();

	public Faculty(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}
	
	public Faculty(String string) {
		//default constructor
		this.name = string;
	}
	
	public Faculty (LibraryManagementSysInfo subject, User user) {
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
		this.currentlyRenting = user.currentlyRenting;
		this.currentRentalOrder = user.currentRentalOrder;
		this.currentPurchaseOrder = user.currentPurchaseOrder;
		this.bookCollection = user.bookCollection;
		this.recommendation = user.recommendation;
		this.myInvoker = user.myInvoker;
		this.name = user.name;
		this.email = user.email;
		this.password = user.password;
		this.type = user.type;
		this.itemsOut = user.itemsOut;
		this.itemsOverdue = user.itemsOverdue;
		this.penalty = user.penalty;
	}

	@Override
	public void update(){
		for (Textbook textbook : usedTextbooks) {
			if (textbook.isNewEditionAvailable()){
				createNotification(textbook);
			}
			if (!textbook.isAvailable()) {
				notifyOfUnavailability(textbook);
			}
		}
	}

	public void teachCourse(Courses course){
		coursesTeaching.add(course);
		System.out.println("You are teaching " + course.getCourseName());
	}

	public void stopTeaching(Courses course){
		coursesTeaching.remove(course);
		System.out.println("You have stopped teaching " + course.getCourseName());
	}

	public List<Courses> getCoursesTeaching(){
		return coursesTeaching;

	}

	public void setUsedTextbooks(List<Textbook> textbooks){
		this.usedTextbooks = textbooks;
	}
	
	public void setTextbook (Courses course, Textbook textbook) {
		this.CandT.put(course, textbook);
	}
	
	public Textbook getTextbook (Courses course ) {
		return CandT.get(course);
	}

	public List <Textbook> getTextbooks (Courses course){
		List<Textbook> textbooksForCourse = new ArrayList<>();
		for (Textbook textbook : usedTextbooks) {
			if (textbook.getCourse().equals(course)) {
				textbooksForCourse.add(textbook);
			}
		}
		return textbooksForCourse;
	}

	public void createNotification(Textbook textbook){
		System.out.println("Creating notification for textbook new edition " + textbook.getTextName());
	}

	public void notifyOfUnavailability(Textbook textbook){
		System.out.println("Creating notification for textbook unavailability " + textbook.getTextName());
	}

	public void trackCourseAndTextbook(String course, String textbook) {
        if (coursesAndTextbooks.containsKey(course)) {
            List<String> textbooks = coursesAndTextbooks.get(course);
            if (!textbooks.contains(textbook)) {
                textbooks.add(textbook);
            }
        } else {
            List<String> textbooks = new ArrayList<>();
            textbooks.add(textbook);
            coursesAndTextbooks.put(course, textbooks);
        }
    }

    public void notifyNewTextbookEdition(String course, String textbook, String newEdition) {
        if (coursesAndTextbooks.containsKey(course)) {
            List<String> textbooks = coursesAndTextbooks.get(course);
            if (textbooks.contains(textbook)) {
                System.out.println("New edition of " + textbook + " available for course " + course + ": " + newEdition);
            }
        }
    }
}

