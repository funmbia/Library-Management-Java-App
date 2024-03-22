package command;

import factory.Newsletter;

public class OpenNewsletter implements Command{

	public Newsletter newsletter;

	public OpenNewsletter(Newsletter newsletter) {
		this.newsletter = newsletter;
	}
	
	public String execute() {
			return "Would you like to open ";
	}

}
