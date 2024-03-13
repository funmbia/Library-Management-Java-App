package observer;
public class Demo {

	public static void main(String[] args){
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();

		new Faculty(subject);
		new Student(subject);
		new NonFaculty(subject);
		new Visitor(subject);
		subject.notifyAllObservers();
	}
}
