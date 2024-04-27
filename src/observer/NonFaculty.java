package observer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NonFaculty extends User {

	private static final String CSV_FILE_PATH = "userinfo.csv";

	public NonFaculty() {
	    // Constructor 
	}

	public NonFaculty(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}

	@Override
	public void update(){

	}
}

