package command;

public class RequestBook {
	private String requestType;
	public String bookToRequest;
	public String priority;
	private int numInLine;
	
	public RequestBook(RequestPhysicalBook rpb) {
		this.requestType = rpb.requestType;
		this.bookToRequest = rpb.bookToRequest;
	}
	
	private void calculatePriority() {
		if (this.requestType.toLowerCase() == "course teaching") {
			priority = "HIGH";
			RequestPhysicalBook.numberOfRequests[0] ++;
			numInLine = RequestPhysicalBook.numberOfRequests[0];
		}
		else if (this.requestType.toLowerCase() == "self improvement") {
			priority = "LOW";
			RequestPhysicalBook.numberOfRequests[1] ++;
			numInLine = RequestPhysicalBook.numberOfRequests[0] + RequestPhysicalBook.numberOfRequests[1];
		}
		else {
			priority = "Not a valid request type! Please request for COURSE TEACHING or SELF IMPROVEMENT";
		}
	}
	
	public String getSummary() {
		calculatePriority();
		if (priority.length() > 4) {
			return priority;
		}
		
		StringBuilder build = new StringBuilder("Your Book Request for " + bookToRequest + "has been filed.\n");
		build.append("Your Request is of " + priority + "priority and you are " +numInLine + "in line.");
		return build.toString();
	}
}
