import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NonFaculty extends RegisteredClient {

	private static final String CSV_FILE_PATH = "userinfo.csv";

	public NonFaculty(LibraryManagementSysInfo subject){
		this.subject=subject;
		this.subject.attachRegisteredClient(this);
	}

	@Override
	public void update(){

	}

	public boolean validation(String email, String password){
		if (!uniqueEmail(email)){
			System.out.println("Email’s already registered");
			return false;
		}

		if (!isStrongPassword(password)){
			System.out.println("Not strong enough password");
			return false;
		}
		return true;
	}

	public boolean uniqueEmail(String email) {
		try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String storedEmail = parts[1].trim();
				if (email.equals(storedEmail)) {
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean isStrongPassword(String password) {

		if (password.length() < 8) {
			return false;
		}
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;
		String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else if (specialChars.contains(String.valueOf(c))) {
				hasSpecialChar = true;
			}
		}

		return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
	}
}

