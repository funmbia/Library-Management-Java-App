package gui;

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
import observer.User;
import factory.Newsletter;

public class NewsletterPage implements Page {
    User user;

    public NewsletterPage(User user) {
        this.user = user;
    }

    public JPanel createPage(JFrame frame) {
        JButton backBt = new JButton("Back");
        backBt.addActionListener(e -> new ActionPage(user, true));

        JButton logoutBt = new JButton("Log Out");
        logoutBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel newpanel = new LogInPage().createPage(frame);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(newpanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        List<String[]> newsletters = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/csv files/newsletters.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    newsletters.add(parts);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] newsletter : newsletters) {
            listModel.addElement(newsletter[0]);
        }

        JList<String> newsletterList = new JList<>(listModel);
        newsletterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(newsletterList);

        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        newsletterList.setCellRenderer(renderer);

        JButton unsubscribeBt = new JButton("Unsubscribe");
        unsubscribeBt.addActionListener(e -> {
            int index = newsletterList.getSelectedIndex();
            if (index != -1) {
                String[] selectedNewsletter = newsletters.get(index);
                String title = selectedNewsletter[0];
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to unsubscribe from the newsletter '" + title + "'?", "Unsubscribe from Newsletter", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    Newsletter newsletter = new Newsletter();
                    newsletter.setAttributes(selectedNewsletter[0], selectedNewsletter[1], selectedNewsletter[2]);
                    newsletter.setSubscribed(false);
                    JOptionPane.showMessageDialog(null, "You have successfully unsubscribed from the newsletter '" + title + "'.", "Unsubscription Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        newsletterList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = newsletterList.getSelectedIndex();
                if (index != -1) {
                    String[] selectedNewsletter = newsletters.get(index);
                    String title = selectedNewsletter[0];
                    String url = selectedNewsletter[2];

                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to subscribe to the newsletter '" + title + "'?", "Subscribe to Newsletter", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You have successfully subscribed to the newsletter '" + title + "'.", "Subscription Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    choice = JOptionPane.showConfirmDialog(null, "Do you want to open the link to the newsletter '" + title + "'?", "Open Link", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            Desktop.getDesktop().browse(new URI(url));
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        JLabel messageLabel = new JLabel("Press on the link to a newsletter you would like to subscribe to and read.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.NORTH);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backBt, BorderLayout.WEST);
        panel.add(logoutBt, BorderLayout.EAST);
        panel.add(unsubscribeBt, BorderLayout.SOUTH);

        return panel;
    }
}
