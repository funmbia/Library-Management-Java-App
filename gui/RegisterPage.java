package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Singleton.*;
import observer.User;

//TODO code to lead to action page iff valid user
public class RegisterPage extends JFrame implements Page { 
	
	private JTextField nameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField accountField;
	private JButton register;
	    
	public JPanel createPage(JFrame frame) {
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Register A New Account"));
          

        panel.setLayout(new GridLayout(13, 6));

        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel accountLabel = new JLabel("Account type:");
        

        nameField = new JTextField(SwingConstants.RIGHT);
        emailField = new JTextField(SwingConstants.RIGHT);
        passwordField = new JPasswordField();
        accountField = new JTextField(SwingConstants.RIGHT);
       

        JButton signUp = new JButton("Sign Up");
        signUp.setAlignmentY(JButton.BOTTOM_ALIGNMENT);

        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String account = accountField.getText();
                registerUser User = new registerUser(name,email,password,account);
                                          
                             
            }
        });
        
        panel.add(nameLabel);
        panel.add(nameField);
        
        panel.add(emailLabel);
        panel.add(emailField);
        
       
        panel.add(passwordLabel);
        panel.add(passwordField);
        
        panel.add(accountLabel);
        panel.add(accountField);
        
        panel.add(signUp);

        add(panel);

        setVisible(true);
        return panel; 
	}
   
    
}