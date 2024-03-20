package command;

import factory.Newsletter;

public class OpenNewsletter implements Command{

	public Newsletter newsletter;

	public OpenNewsletter(Newsletter newsletter) {
		this.newsletter = newsletter;
	}
	
	public String execute() {
		// TODO Send to code/logic that opens in frame
		return "Opening ...";
	}

}
