package command;

public class RequestPhysicalBook implements Command {
    private String bookToRequest;
    private String requestType;

    // Constructor
    public RequestPhysicalBook(String bookToRequest, String requestType) {
        this.bookToRequest = bookToRequest;
        this.requestType = requestType;
    }

    // Getter method for bookToRequest
    public String getBookToRequest() {
        return bookToRequest;
    }

    // Setter method for bookToRequest
    public void setBookToRequest(String bookToRequest) {
        this.bookToRequest = bookToRequest;
    }

    // Getter method for requestType
    public String getRequestType() {
        return requestType;
    }

    // Setter method for requestType
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    public String execute() {
        // Perform execution logic here
        return "Request Physical Book - " + bookToRequest + ", Request Type - " + requestType;
    }
