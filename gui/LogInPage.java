package gui;

import javax.swing.*;
import Singleton.*;

//TODO code to lead to action page iff valid user
public class LogInPage implements Page { 
	
    public JPanel createPage(JFrame frame) {
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Log In Page"));
        return panel; 
    }
    
}

