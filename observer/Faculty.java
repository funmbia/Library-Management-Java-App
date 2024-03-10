import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faculty extends RegisteredClient {

	private List<Courses> coursesTeaching= new ArrayList<>();
	private List<Textbook> usedTextbooks= new ArrayList<>();
	private Map<String, List<String>> coursesAndTextbooks = new HashMap<>();

	public Faculty(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}

	public Faculty(String string) {
		//default constructor
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

