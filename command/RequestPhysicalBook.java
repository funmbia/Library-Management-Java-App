package command;

public class RequestPhysicalBook implements Command {
	public static int[] numberOfRequests = new int[2]; //numberOfRequests[0]= high priority
    public String bookToRequest;
    public String requestType;

    // Constructor
    public RequestPhysicalBook(String bookToRequest, String requestType) {
        this.bookToRequest = bookToRequest;
        this.requestType = requestType;
    }

    public String execute() {
    	RequestBook process = new RequestBook(this);
    	return process.getSummary();
    }
}
