package observer;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainTextbook {

	public ArrayList<Textbook> textbooks = new ArrayList<>();
	public String path;

	public MaintainTextbook(String path) {
		this.path = path;
	}

	public void load() throws Exception {
		CsvReader reader = new CsvReader(path);
		reader.readHeaders();

		while (reader.readRecord()) {
			String textName = reader.get("textName");
			String ISBN = reader.get("ISBN");
			String edition = reader.get("edition");
			String url = reader.get("url");
			Textbook textbook = new Textbook(textName, ISBN, edition, url);
			textbooks.add(textbook);
		}

		reader.close();
	}

	public void update() throws Exception {
		CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

		csvOutput.write("textName");
		csvOutput.write("ISBN");
		csvOutput.write("edition");
		csvOutput.endRecord();

		for (Textbook textbook : textbooks) {
			csvOutput.write(textbook.getTextName());
			csvOutput.write(textbook.getISBN());
			csvOutput.write(textbook.getEdition());
			csvOutput.write(textbook.getURL());
			csvOutput.endRecord();
		}

		csvOutput.close();
	}

	public String findNewestEd() {
		String newestEd = "0";

		for (Textbook textbook : textbooks) {
			if (textbook.getEdition().compareTo(newestEd)> 0) {
				newestEd = textbook.getEdition();
			}
		}
		return newestEd;
	}
}
