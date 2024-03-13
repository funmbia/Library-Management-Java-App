package observer;
import factory.LibraryItem;
import factory.Newsletter;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainNewsletter {
    public ArrayList<Newsletter> newsletters = new ArrayList<Newsletter>();

    public void load(String path) throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();

        while (reader.readRecord()) {
            Newsletter n = new Newsletter();
            n.setAttributes(reader.get("title"), reader.get("organization"), reader.get("URL"));
            n.setSubscribed(Boolean.parseBoolean(reader.get("isSubscribed"))); 
            newsletters.add(n);
        }
    }

    public void update(String path) throws Exception {
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

            csvOutput.write("title");
            csvOutput.write("organization");
            csvOutput.write("URL");
            csvOutput.write("isSubscribed");
            csvOutput.endRecord();
            
            for (Newsletter n : newsletters) {
                csvOutput.write(n.getTitle());
                csvOutput.write(n.getOrganization());
                csvOutput.write(n.getURL());
                csvOutput.write(String.valueOf(n.isSubscribed())); 
                csvOutput.endRecord();
            }
            csvOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
