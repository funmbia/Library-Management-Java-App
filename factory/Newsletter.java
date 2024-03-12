package factory;

public class Newsletter extends LibraryItem {
	private String organization;
	private String URL;
	private boolean isSubscribed;
        private String name;
	
	public void setAttributes(String title, String organization, String URL) {
		this.title = title;
		this.organization = organization;
		this.URL = URL;
	}
	
	public Newsletter() {
		// Default constructor
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public String getURL() {
		return URL;
	}

	public Newsletter(boolean isSubscribed) {
        	this.isSubscribed = isSubscribed;
    	}

    	public boolean isAvailable() {
       		return isSubscribed;
   	}

    	public String getName() {
       		return name;
    	}

    	public void setName(String name) {
        	this.name = name;
    	}

	public boolean isSubscribed() {
		return isSubscribed;
	}
	
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
	@Override
	public String toString() {
		return "NEWSLETTER SUMMARY:\n\tTitle: " + this.title + ", Organization: " + this.organization + ", URL: " + this.URL;
	}
}

