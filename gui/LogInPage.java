package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Singleton.*;
import iterator.BookCollection;
import observer.User;

//TODO code to lead to action page iff valid user
public class LogInPage extends JFrame implements Page { 
	
	 
//	private JTextField emailField;
//	private JPasswordField passwordField;
//	private JButton LogInButton;
	    

	
	public JPanel createPage(JFrame frame) {
    	JPanel panel = new JPanel(); 
        panel.add(new JLabel("Log on to an account")); 
          

        panel.setLayout(new GridLayout(10, 2));

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        

        JTextField emailField = new JTextField(SwingConstants.RIGHT);
        JPasswordField passwordField = new JPasswordField();
       

        JButton LogInButton = new JButton("Log In");
         
        LogInButton.setAlignmentY(JButton.BOTTOM_ALIGNMENT);

        LogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
               User u = new User(0, 0, null, null, null, null, email, password, null, null);
                SystemManagement system = SystemManagement.getSystemInstance();
                
               
                try {
					u = system.loginUser(email, password);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
                
                if (u == null) {
                	JOptionPane.showMessageDialog(null, "Your email and password don't match our records. Try Again", null, JOptionPane.PLAIN_MESSAGE); 
                }
                else {
                	new ActionPage( u, true );
                }
                             
            } 
        });

        panel.add(emailLabel);
        panel.add(emailField);
     
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(LogInButton);

//        add(panel);
//
//        setVisible(true);
        return panel; 
	}
	
   
	
    
}
