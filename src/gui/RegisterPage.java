package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import Singleton.*;
import builder.PurchaseOrderBuilder;
import builder.RentalOrderBuilder;
import command.Invoker;
import observer.MaintainUser;
import observer.User;

//TODO code to lead to action page iff valid user
public class RegisterPage extends JFrame implements Page { 
//	
//	private JTextField nameField;
//	private JTextField emailField;
//	private JPasswordField passwordField;
//	private JTextField accountField;
	
	public JPanel createPage(JFrame frame) { 
    	JPanel panel = new JPanel();
        panel.add(new JLabel("Register A New Account"));
          

        panel.setLayout(new GridLayout(13, 6));

        JLabel nameLabel = new JLabel("Name:"); 
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel accountLabel = new JLabel("Account type:");
        

        JTextField nameField = new JTextField(50);
        JTextField emailField = new JTextField(SwingConstants.RIGHT);
        JPasswordField passwordField = new JPasswordField();
        JTextField accountField = new JTextField(SwingConstants.RIGHT);

        JButton signUp = new JButton("Sign Up");
        signUp.setAlignmentY(JButton.BOTTOM_ALIGNMENT);

        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String account = accountField.getText();
                registerUser user = new registerUser(name,email,password,account);
                User thisUser = new User();
                
                try {
                    if (!user.createAccount()) {
                    	JOptionPane.showMessageDialog(null, user.getAccountCreationErrorReason(), "Account Creation Error", JOptionPane.PLAIN_MESSAGE);
                    } else {
//                    	MaintainUser maintain = new MaintainUser("src/csv files/userInfo.csv");
//                    	maintain.load();
//                    	ArrayList<User> allUsers = maintain.users;
//                    	for (User u: allUsers) {
//                    		if (u.getEmail().equals(email) && u.getPassword().equals(password)) thisUser = u; 
//                    	}
//                    	new ActionPage( thisUser, true );
                    	thisUser = new User(0, 0, new ArrayList<>(), null, null, name, email, password, account, new Invoker());
                    	new ActionPage(thisUser, true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }                      
                             
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

//        add(panel);
//
//        setVisible(true);
        return panel; 
	}
   
    
}