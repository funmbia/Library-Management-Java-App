package gui;

import javax.swing.*;
import observer.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PurchasePage implements Page {
	User user;
	JLabel totalAmountLabel;

	public PurchasePage(User user) {
		this.user = user;
	}

	double totalAmount = 0.0;

	@Override
	public JPanel createPage(JFrame frame) {
		JButton backBt = new JButton("Back");
		backBt.addActionListener(e -> new ActionPage(user,true)); 
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

		JPanel purchasePanel = new JPanel();
		purchasePanel.setLayout(new BorderLayout());

		JLabel messageLabel = new JLabel("Would you like to purchase any of these books not available in the Yorku Library System? If yes, please click on the book.");
		purchasePanel.add(messageLabel, BorderLayout.NORTH);

		JPanel bookPanel = new JPanel(new GridLayout(0, 2, 10, 10));

		String[] bookNames = {"Moon of the Crusted Snow", "Harry Potter", "Wonder"};
		double[] originalPrices = {15.0, 14.99, 20.35};
		double[] discountedPrices = {13.0, 12.99, 15.35};

		for (int i = 0; i < bookNames.length; i++) {
			String bookName = bookNames[i];
			double originalPrice = originalPrices[i];
			double discountedPrice = discountedPrices[i];

			JLabel bookLabel = new JLabel(bookName);
			bookPanel.add(bookLabel);

			JLabel priceLabel = new JLabel(String.format("Original Price: $%.2f, Discounted Price: $%.2f", originalPrice, discountedPrice));
			bookPanel.add(priceLabel);

			bookLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					JPanel paymentPanel = new JPanel();
					JButton debitButton = new JButton("Pay with Debit Card");
					JButton creditButton = new JButton("Pay with Credit Card");
					JButton walletButton = new JButton("Pay with Mobile Wallet");

					debitButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(frame, "Paid with Debit Card");
							totalAmount += discountedPrice;
							updateTotalAmountLabel(totalAmount, totalAmountLabel);
						}
					});

					creditButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(frame, "Paid with Credit Card");
							totalAmount += discountedPrice;
							updateTotalAmountLabel(totalAmount, totalAmountLabel);
						}
					});

					walletButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(frame, "Paid with Mobile Wallet");
							totalAmount += discountedPrice;
							updateTotalAmountLabel(totalAmount, totalAmountLabel);
						}
					});

					paymentPanel.add(debitButton);
					paymentPanel.add(creditButton);
					paymentPanel.add(walletButton);

					JOptionPane.showMessageDialog(frame, paymentPanel, "Select Payment Method", JOptionPane.PLAIN_MESSAGE);
				}
			});
		}

		purchasePanel.add(bookPanel, BorderLayout.CENTER);

		totalAmountLabel = new JLabel("Total Amount: $0.00");
		purchasePanel.add(totalAmountLabel, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(backBt);
		buttonPanel.add(logoutBt);
		purchasePanel.add(buttonPanel, BorderLayout.NORTH);

		return purchasePanel;
	}

	private void updateTotalAmountLabel(double amount, JLabel label) {
		label.setText("Total Amount: $" + String.format("%.2f", amount));
	}
}
