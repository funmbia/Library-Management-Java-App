package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Singleton.*;
import observer.User;

//TODO code to lead to action page iff valid user
public class LogInPage extends JFrame implements Page { 
	
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton LogInButton;
	    
	public JPanel createPage(JFrame frame) {
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Log on to an account"));
        
      

        panel.setLayout(new GridLayout(10, 2));

        JLabel emailLabel = new JLabel("email:");
        JLabel passwordLabel = new JLabel("Password:");
        

        emailField = new JTextField(SwingConstants.RIGHT);
        passwordField = new JPasswordField();
       

        JButton LogInButton = new JButton("Log In");
        
        LogInButton.setAlignmentY(JButton.BOTTOM_ALIGNMENT);

        LogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                User u = new User(0, 0,null, null, null, null, email, password, null, null);
                
                 
                if(Singleton.registerUser.members.contains(u.getEmail())&&Singleton.registerUser.members.contains(u.getPassword())) {
                	LogInButton.addActionListener(  t -> new ActionPage(Singleton.yorkMembers.getMember(u.getEmail())));
                }
                
                //User newUser = new User(username, password);          
               
                //System.out.println("Username: " + newUser.getUsername());
                //System.out.println("Password: " + newUser.getPassword());
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
     
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(LogInButton);

        add(panel);

        setVisible(true);
        return panel; 
	}
   
    
}

