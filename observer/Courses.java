import java.util.ArrayList;
import java.util.List;

public class Courses {

	private static List<Courses> coursesList = new ArrayList<>();
	String courseID;
	String courseName;
	Faculty faculty;

	public Courses(String courseName, Faculty faculty, String courseID) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.faculty = faculty;
	}

	public Courses() {
	//default constructor
	}

	String getCourseID(){
		return courseID;
	}

	String getCourseName(){
		return courseName;
	}

	Faculty getFaculty(){
		return faculty;
	}

	public void add(Courses course) {
		coursesList.add(course);
	}
}