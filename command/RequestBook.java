package command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestBook {
	public static List<String> allRequests = new ArrayList<>();
	
	private String requestType;
	public String bookToRequest;
	public String priority;
	private int numInLine;
	
	public RequestBook(RequestPhysicalBook rpb) {
		this.requestType = rpb.requestType;
		this.bookToRequest = rpb.bookToRequest;
		priority = "";
		numInLine = 0;
	}
	
	private void calculatePriority() {
		if (this.requestType == "course teaching") {
			priority = "HIGH";
			allRequests.add(bookToRequest);
			RequestPhysicalBook.numberOfRequests[0] ++;
			numInLine = RequestPhysicalBook.numberOfRequests[0];
		}
		else {
			priority = "LOW";
			allRequests.add(bookToRequest);
			RequestPhysicalBook.numberOfRequests[1] ++;
			numInLine = RequestPhysicalBook.numberOfRequests[0] + RequestPhysicalBook.numberOfRequests[1];
		}
	}
	
	public String getSummary() {
		calculatePriority();

		StringBuilder build = new StringBuilder("Your Book Request for '" + bookToRequest + "' has been filed.\n");
		build.append("Your Request is of " + priority + " priority and you are #" +numInLine + " in line.");
		return build.toString();
	} 
}
