package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import observer.User;

public class RentPage implements Page {
	private User user;
	private List<String[]> itemsData;

	public RentPage(User user) {
		this.user = user;
		this.itemsData = readCSV("src/csv files/physicalItem.csv");
	}

	public JPanel createPage(JFrame frame) {
		JButton backBt = new JButton("Back");
		backBt.addActionListener(e -> new ActionPage(user));

		JButton logoutBt = new JButton("Log Out");
		logoutBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel newPanel = new LogInPage().createPage(frame);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(newPanel);
				frame.revalidate();
				frame.repaint();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Rent Page"));

		String[] rentOptions = {"HardcoverBook", "CD", "Magazine"};
		for (String option : rentOptions) {
			JButton rentButton = new JButton("Rent a " + option);
			rentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					displayItemDetails(option); 
				}
			});
			panel.add(rentButton);
		}

		panel.add(Box.createRigidArea(new Dimension(0, 10))); 
		panel.add(backBt);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(logoutBt);

		return panel;
	}

	private void displayItemDetails(String itemType) {
		StringBuilder message = new StringBuilder();
		boolean found = false;

		for (String[] item : itemsData) {
			if (item.length >= 6 && item[0].equalsIgnoreCase(itemType)) {
				boolean rentable = Boolean.parseBoolean(item[3]);
				boolean purchaseable = Boolean.parseBoolean(item[4]);

				if (rentable) {
					found = true;
					message.append("Item Type: ").append(item[0]).append("\n");
					message.append("Title: ").append(item[1]).append("\n");
					message.append("Location: ").append(item[2]).append("\n");
					message.append("Rentable: ").append(rentable).append("\n");
					message.append("Purchaseable: ").append(purchaseable).append("\n");
					message.append("Price: ").append(item[5]).append("\n");

					int copiesAvail = 20;
					if (item.length >= 7) {
						copiesAvail = Integer.parseInt(item[6]); 
						copiesAvail--; 
						item[6] = String.valueOf(copiesAvail); 
						message.append("Copies Available: ").append(copiesAvail).append("\n\n");
					} else {
						message.append("Copies Available: N/A\n\n");
					}

					updateCSV(item);

					if (item[0].equalsIgnoreCase("HardcoverBook")) {
						System.out.println("HardcoverBook found!");
					}
				}
			}
		}

		if (!found) {
			message.append("No available ").append(itemType).append("s found.");
		}

		if (!found && itemType.equalsIgnoreCase("HardcoverBook")) {
			System.out.println("No HardcoverBooks found.");
		}

		JOptionPane.showMessageDialog(null, message.toString(), "Item Details", JOptionPane.INFORMATION_MESSAGE);
	}

	private void updateCSV(String[] item) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/csv files/physicalItem.csv"))) {
			for (String[] rowData : itemsData) {
				if (rowData[0].equals(item[0]) && rowData[1].equals(item[1]) && rowData[2].equals(item[2])) {
					writer.write(String.join(",", item));
				} else {
					writer.write(String.join(",", rowData));
				}
				writer.newLine(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String[]> readCSV(String filename) {
		List<String[]> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean firstLine = true;
			while ((line = br.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue; 
				}
				String[] parts = line.split(",");
				for (int i = 0; i < parts.length; i++) {
					parts[i] = parts[i].trim();
				}
				data.add(parts);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
