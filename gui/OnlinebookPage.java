package gui;

import iterator.Book;
import iterator.BookCollection;
import observer.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class OnlinebookPage implements Page {
	private User user;
	private BookCollection bookCollection;
	private String csvFilePath = "src/csv files/onlinebooks.csv";

	public OnlinebookPage(User user, BookCollection bookCollection) {
		this.user = user;
		this.bookCollection = bookCollection;
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
					SearchResult result = searchBook(query);
					if (result != null && result.url != null) {
						StringBuilder message = new StringBuilder();
						message.append("Book found: ").append(result.title).append(". Do you want to open the URL?\n");
						if (!result.recommendedBooks.isEmpty()) {
							message.append("\nRecommended books starting with '").append(query.charAt(0)).append("':\n");
							for (String recommendedBook : result.recommendedBooks) {
								message.append(recommendedBook).append("\n");
							}
						} else {
							message.append("\nNo similar books found.");
						}
						int choice = JOptionPane.showConfirmDialog(frame, message.toString(), "Book Found", JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							openURL(result.url);
						}
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

	private SearchResult searchBook(String title) {
		SearchResult result = new SearchResult();
		result.title = title;
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length > 0) {
					String extractedTitle = data[0].trim();
					if (extractedTitle.equalsIgnoreCase(title.trim())) {
						result.url = data[3].trim();
					} else if (extractedTitle.startsWith(title.substring(0, 1).toUpperCase())) {
						result.recommendedBooks.add(extractedTitle);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.url != null ? result : null;
	}


	private void openURL(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static class SearchResult {
		String title;
		String url;
		List<String> recommendedBooks = new ArrayList<>();
	}
}
