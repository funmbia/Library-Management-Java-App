package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface Page {
	//abstract method to be implemented
	public JPanel createPage(JFrame frame);
}
