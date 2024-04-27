package Singleton;

import java.util.ArrayList;

import observer.MaintainUser;
import observer.User;

public class registerUser {
	String name;
	String email;
	String password; 
	String accountT;
	private String csvFilePath =  "Library-Management-Java-App/csv files/userInfo.csv";
	
	
	String errorReason; 
    
	User user = new User();

    public registerUser(String name, String email, String password, String accountT){
    	this.name = name;
    	this.email = email; 
    	this.password = password;
    	this.accountT = accountT;
    	
    	user.setCurrentlyRenting(new ArrayList<>());
    	
        user.setDatabaseAttributes(name,email,password,accountT,0,0,0);
        yorkMembers.members.add(user);
    }

    public String getEmail() {
		return user.getEmail();
	}
    public String getPassword() {
		return user.getPassword();
	}

    public boolean createAccount() throws Exception{
    	if (! isValidEmail(email)) {
    		errorReason = "Not a valid email format. Try Again";
    		return false;
    	}
    	if (! isValidPassword(password)) {
    		errorReason = "Your Password is not strong enough. Add a combination of uppercase letters, lowercase letters, numbers, and symbols.";
    		return false;
    	}
    	if (! accountT.trim().toLowerCase().equals("visitor")) {
    		if (! validateNonVisitor()) {
    			errorReason = "Validation as York Member failed. Try Again.";
    			return false;
    		}
    		else {
    			yorkMembers.members.add(user);
    		}
    			
    	}
    	addAccount(user);
    	return true;
    }
    
    public MaintainUser addAccount(User user) throws Exception {
    	MaintainUser maintain = new MaintainUser(csvFilePath);
    	maintain.load();
    	maintain.addUser(user);
    	maintain.update();
		return maintain;
    }
    
    public String getAccountCreationErrorReason() {
    	return errorReason;
    }

    public boolean validateNonVisitor(){
        return yorkMembers.validate(user);
    }  

	public boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailSymb = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailSymb);
	}

	public boolean isValidPassword(String password) {
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
}
