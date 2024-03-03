
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainDate {
    private ArrayList<Date> dates = new ArrayList<>();
    private String path;

    public void load(String path) throws Exception {
        CsvReader reader = new CsvReader(new FileReader(path));
        reader.readHeaders(); 

        while (reader.readRecord()) {
            Date date = parseDate(reader.get("date"));
            dates.add(date);
        }

        reader.close();
    }

    public void update(String path) throws Exception {
        CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

        csvOutput.write("date");
        csvOutput.endRecord();

        for (Date date : dates) {
            csvOutput.write(formatDate(date));
            csvOutput.endRecord();
        }

        csvOutput.close();
        System.out.println("CSV file updated successfully.");
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        return dateFormat.parse(dateString);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        return dateFormat.format(date);
    }

    public void add(Date date) {
        dates.add(date);
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}
