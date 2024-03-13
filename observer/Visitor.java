package observer;
public class Visitor extends RegisteredClient {

	public Visitor(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}

	@Override
	public void update(){
	    System.out.println("Visitor's info is updated.");
	}
}

