public abstract class RegisteredClient {

	public LibraryManagementSysInfo subject;

	public abstract void update();

	private String email;
	private String password;

	public boolean login(String email, String password, String accountType){
		if(!isValidEmail(email) || isValidPassword(password)) {
			System.out.println("Invalid info");
			return false;
		}

		if (accountType.equals("student") || accountType.equals("faculty") || accountType.equals("staff")) {
			if (!additionalValidationForUserType(accountType)) {
				System.out.println("Additional validation failed for user type: " + accountType);
				return false;
			}
		}

		this.email = email;
		this.password = password;
		System.out.println("Successful registration.");
		return true;
	}

	private boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailSymb = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailSymb);
	}

	private boolean isValidPassword(String password) {
		if (password == null || password.isEmpty() || password.length() < 8) {
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

	private boolean additionalValidationForUserType(String userType) {

		if (userType.equals("student")) {
			System.out.println("You have now been validated as a student.");
		} else if (userType.equals("faculty")) {
			System.out.println("You have now been validated as a faculty.");
		} else if (userType.equals("staff")) {
			System.out.println("You have now been validated as a staff.");
		}
		return true;
	}

	public Newsletter subscribeNewsletter (Newsletter newsletter){
		System.out.println("You are subscribed to " + newsletter.getName());
		return newsletter;
	}

	public Newsletter cancelNewsletter(Newsletter newsletter){
		System.out.println("You are unsubscribed to " + newsletter.getName());
		return newsletter;
	}

	public OnlineBook openBook(OnlineBook onlineBook){
		System.out.println("You have opened: " + onlineBook.getTitle());
		return onlineBook;
	}

	public PhysicalItem rentPhysicalItem(PhysicalItem physicalItem){
		System.out.println("You have rented: " + physicalItem.getTitle());
		return physicalItem;
	}
}