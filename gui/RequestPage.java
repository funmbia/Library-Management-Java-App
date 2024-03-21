package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import observer.User;

public class RequestPage implements Page {
	User user;
	
	public RequestPage(User user) {
		this.user = user;
	}
	
	public JPanel createPage(JFrame frame) {
			JButton backBt = new JButton("Back");
		    backBt.addActionListener(e -> new ActionPage(user));
		    
		    JLabel prompt = new JLabel("Request A New Book");
			JTextField textField = new JTextField(25);
			
			JLabel prompt2 = new JLabel("Select Request Type");
			
			JButton courseTeaching = new JButton("Course Teaching");
			courseTeaching.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String inputText = textField.getText();
		            JOptionPane.showMessageDialog(frame, user.request(inputText, "course teaching"),null, JOptionPane.INFORMATION_MESSAGE);
		        }
		    });
			
			JButton selfImprovement = new JButton("Self Improvement");
			selfImprovement.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String inputText = textField.getText();
		            JOptionPane.showMessageDialog(frame, user.request(inputText, "self improvement"),null, JOptionPane.INFORMATION_MESSAGE);

		        }
		    });
			
		    
		    JButton logoutBt = new JButton("Log Out");
		    logoutBt.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            JPanel newpanel = new LogInPage().createPage(frame);
		            frame.getContentPane().removeAll(); // Remove the existing page/panel from the frame
		            frame.getContentPane().add(newpanel);
		            frame.revalidate();
		            frame.repaint();
		        }
		    });
		    
		    JPanel panel = new JPanel(new BorderLayout());

		    JPanel panel2 = new JPanel();
		    panel2.add(prompt);
		    panel2.add(textField);
		    panel.add(panel2, BorderLayout.NORTH);
		    
		    JPanel panel3 = new JPanel();
		    panel3.add(prompt2);
		    panel3.add(courseTeaching);
		    panel3.add(selfImprovement);
		    panel.add(panel3, BorderLayout.CENTER);
		   
		    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		    buttonPanel.add(backBt);
		    buttonPanel.add(logoutBt);
		    panel.add(buttonPanel, BorderLayout.SOUTH);
		    
		    return panel;
		}
	


}
