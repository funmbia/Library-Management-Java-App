package gui;

import javax.swing.*;
import Singleton.*;
import observer.User;

//TODO code to lead to action page iff valid user
public class RegisterPage implements Page { 
	
	User user;
	
	public RegisterPage(User user) {
		this.user = user;
	}
	
    public JPanel createPage(JFrame frame) {
    	registerUser createAccount = new registerUser(this.user.getName(),user.getEmail(),user.getPassword(), user.getAccountType());
    	
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Register New User"));
        return panel; 
    }
    
}