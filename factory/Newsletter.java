package factory;

public class Newsletter extends LibraryItem {
	private String organization;
	private String URL;
	
	public void setAttributes(String title, String organization, String URL) {
		this.title = title;
		this.organization = organization;
		this.URL = URL;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public String getURL() {
		return URL;
	}

	@Override
	public String toString() {
		return "NEWSLETTER SUMMARY:\n\tTitle: " + this.title + ", Organization: " + this.organization + ", URL: " + this.URL;
	}
}

