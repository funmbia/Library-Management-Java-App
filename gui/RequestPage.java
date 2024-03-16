package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observer.User;

public class RequestPage implements Page {
	User user;
	
	public RequestPage(User user) {
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
    	panel.add(new JLabel("Request Page"));
        panel.add(backBt);
        panel.add(logoutBt);
        return panel;
    }
}
