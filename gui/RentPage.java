package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import observer.User;

public class RentPage implements Page {
	User user;
	
	public RentPage(User user) {
		this.user = user;
	}
	
    public JPanel createPage(JFrame frame) {
    	//add back button
    	JButton backBt = new JButton("Back");
    	backBt.addActionListener(  e -> new ActionPage(user));
        
    	//add logout button
        JButton logoutBt = new JButton("Log Out");
        logoutBt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    JPanel newpanel = new LogInPage().createPage(frame);        	    
        	    frame.getContentPane().removeAll();// Remove the existing page/panel from the frame
        	    frame.getContentPane().add(newpanel);
        	    frame.revalidate();
        	    frame.repaint();
        	}
        });
        
        JPanel panel = new JPanel();
    	panel.add(new JLabel("Rent Page"));
        panel.add(backBt);
        panel.add(logoutBt);
        return panel;
    }
    
    
    
}
