import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainCourses {

    public List<Courses> courses = new ArrayList<>();
    public String path;

    public MaintainCourses(String path) {
    	this.path = path;
    }

    public void load() throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();

        while (reader.readRecord()) {
        	Courses courses = new Courses();
            String courseName = reader.get("courseName");
            Faculty faculty = new Faculty(reader.get("faculty"));
            String courseID = reader.get("courseID");
            Courses course = new Courses(courseName, faculty, courseID);
            course.add(course);
        }

        reader.close();
    }

    public void update() throws Exception {
        CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

        csvOutput.write("textName");
        csvOutput.write("ISBN");
        csvOutput.write("edition");
        csvOutput.endRecord();

        for (Courses course : courses) {
            csvOutput.write(course.getCourseName());
            csvOutput.write(course.getFaculty().toString());
            csvOutput.write(course.getCourseID());
            csvOutput.endRecord();
        }

        csvOutput.close();
        System.out.println("CSV file has updated.");
    }
}