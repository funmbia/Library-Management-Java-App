package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import observer.User;

public class OnlinebookPage implements Page {
	private User user;

	public OnlinebookPage(User user) {
		this.user = user;
	}

	public JPanel createPage(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Open an Online Book Page"));

		JLabel searchLabel = new JLabel("Please enter the title of the book you wish to retrieve:");
		searchLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(searchLabel);

		JTextField searchField = new JTextField(20);
		searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(searchField);

		JButton searchButton = new JButton("Search");
		searchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = searchField.getText();
				if (!query.isEmpty()) {
					if (bookExists(query)) {
						JOptionPane.showMessageDialog(frame, "Book found: " + query);
					} else {
						JOptionPane.showMessageDialog(frame, "Book not found: " + query);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter a title to search.");
				}
			}
		});
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(searchButton);
		panel.add(Box.createVerticalGlue());

		JButton logoutButton = new JButton("Logout");
		logoutButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel loginPage = new LogInPage().createPage(frame);
				frame.setContentPane(loginPage);
				frame.revalidate();
				frame.repaint();
			}
		});
		panel.add(logoutButton);

		return panel;
	}

	private boolean bookExists(String title) {
		String csvFilePath = "csv files.resources/onlinebooks.csv";

		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length > 0) {
					String extractedTitle = data[0].trim();
					System.out.println("Extracted Title: " + extractedTitle);
					if (extractedTitle.equalsIgnoreCase(title.trim())) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; 
	}

}
