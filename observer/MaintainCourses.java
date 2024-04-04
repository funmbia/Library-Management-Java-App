package observer;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainCourses {

	public ArrayList<Courses> courses = new ArrayList<>();
	public String path;

	public MaintainCourses(String path) {
		this.path = path;
	}

	public void load() throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			throw new Exception("Error loading courses from CSV: File " + path + " does not exist.");
		}

		CsvReader reader = null;
		try {
			reader = new CsvReader(path);
			reader.readHeaders();

			while (reader.readRecord()) {
				String courseName = reader.get("courseName");
				Faculty faculty = new Faculty(reader.get("faculty"));
				String courseID = reader.get("courseID");
				if (courseID == null || courseID.isEmpty()) {
					throw new Exception("Invalid CSV data: Course ID is missing or empty");
				}
				Courses course = new Courses(courseName, faculty, courseID);
				course.add(course);
				courses.add(course); 
			}
		} catch (Exception e) {
			throw new Exception("Error loading courses from CSV: " + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public void update() throws Exception {
		CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

		csvOutput.write("courseName");
		csvOutput.write("faculty");
		csvOutput.write("courseID");
		csvOutput.endRecord();

		for (Courses course : courses) {
			csvOutput.write(course.getCourseName());
			csvOutput.write(course.getFaculty().toString());
			csvOutput.write(course.getCourseID());
			csvOutput.endRecord();
		}

		csvOutput.close();
	}

}
