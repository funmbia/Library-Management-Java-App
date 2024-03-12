package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.User;

import java.awt.*;
import java.util.HashMap;

public class ActionPage {
	public JFrame frame;
    public JPanel mainPanel;
    public HashMap<String, Page> pages = new HashMap<>();
    public CardLayout cardLayout;
    private User user;

    public ActionPage(User user) {
    	this.user = user;
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the size of the screen
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        frame.setLocationRelativeTo(null);
        
        // Create and add all pages
        pages.put("logout", new LogInPage());
        pages.put("onlinebook", new OnlinebookPage(user));
        pages.put("rent", new RentPage(user));
        pages.put("newsletter", new NewsletterPage(user));
        pages.put("request", new RequestPage(user));
        pages.put("purchase", new PurchasePage(user));
        
        mainPanel = new JPanel();
        addContent();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        frame.add(mainPanel);
        frame.setVisible(true);

        // (Req 3) notification - decided to do as part of the page instead
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(350, 75));
//        JOptionPane.showMessageDialog(null, panel, "Summaries and Warnings for " + user.getName(), JOptionPane.PLAIN_MESSAGE);

        cardLayout.show(mainPanel, "main");   
    }
    
    
    private void addContent() {
        JPanel generalPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); //for button menu
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 30));

   //GENERAL CONTENT
        JLabel lb = new JLabel("Welcome " + user.getName());
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 30));
        lb.setBounds(50, 15, 800, 40);
        generalPanel.add(lb, BorderLayout.NORTH);      

        JButton logoutButton = new JButton("Log Out");
        logoutButton.setFont(new Font("Microsoft PhagsPa", 0, 15));
        logoutButton.addActionListener(e -> {
            mainPanel.add(pages.get("logout").createPage(frame), "logout");
            cardLayout.show(mainPanel, "logout");
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align button to the right
        logoutPanel.add(logoutButton);

   //BUTTON PANEL
        JButton onlineBookButton = new JButton("<html> <center> Open Online Books<br> <small>Available To You</smaller> </center> </html>");//general online books + available textbooks
        onlineBookButton.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        onlineBookButton.addActionListener(e -> {
            mainPanel.add(pages.get("onlinebook").createPage(frame), "onlinebook");
            cardLayout.show(mainPanel, "onlinebook");
        });
        
        JButton rentButton = new JButton("<html> <center> Rent <br> <small>Physical Item</small> </center> </html>");
        rentButton.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        rentButton.addActionListener(e -> {
            mainPanel.add(pages.get("rent").createPage(frame), "rent");
            cardLayout.show(mainPanel, "rent");
        });

        JButton newsletterButton = new JButton("<html> <center> Open Newsletter<br> <small> </small> </center> </html>");
        newsletterButton.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        newsletterButton.addActionListener(e -> {
            mainPanel.add(pages.get("newsletter").createPage(frame), "newsletter");
            cardLayout.show(mainPanel, "newsletter");
        });

        JButton requestButton = new JButton("<html> <center> Request Physical Book<br> <small>Not available in the library</small> </center> </html>");
        requestButton.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        requestButton.addActionListener(e -> {
            mainPanel.add(pages.get("request").createPage(frame), "request");
            cardLayout.show(mainPanel, "request");
        });

        JButton purchaseButton = new JButton("<html> <center> Purchase <br> <small>Physical Item</small> </center> </html>");
        purchaseButton.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        purchaseButton.addActionListener(e -> {
                mainPanel.add(pages.get("purchase").createPage(frame), "purchase");
                cardLayout.show(mainPanel, "purchase");
            });
        
   //SUMMARY & WARNINGS
        JPanel contentPanel = warningsAndSummaries();
        
        buttonPanel.add(onlineBookButton);
        buttonPanel.add(rentButton);
        buttonPanel.add(newsletterButton);
        buttonPanel.add(requestButton);
        buttonPanel.add(purchaseButton);
        
        generalPanel.add(logoutPanel, BorderLayout.NORTH);
        generalPanel.add(buttonPanel, BorderLayout.EAST);
        generalPanel.add(contentPanel, BorderLayout.WEST);
        mainPanel.add(generalPanel, "main"); //add all to page
    }
    
    private JPanel warningsAndSummaries() {
    	JPanel contentPanel = new JPanel(new GridLayout(0, 1));
    	contentPanel.setBorder(new EmptyBorder(50, 30, 150, 150));
    	
    	//TODO - actually get the warnings / summaries
    	JLabel pastDue = new JLabel("PAST DUE"); //color red
    	pastDue.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));
    	pastDue.setForeground(Color.RED);
//    	JLabel pastDueText = new JLabel("<html> <ul>"
//    			+ "<li> The Lion King &nbsp; 11/03/24 </li>"
//    			+ "</ul> </html> ");
    
    	JLabel almostDue = new JLabel("DUE TOMORROW");
    	almostDue.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));

    	JLabel renting = new JLabel("CURRENTLY RENTING");
    	renting.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));

    	contentPanel.add(pastDue);
    	//contentPanel.add(pastDueText);
    	contentPanel.add(almostDue);
    	contentPanel.add(renting);
    	return contentPanel;


    }


    public static void main(String[] args) {
    	
    	User a = new User(0, 0, null, null, "Jane Doe", null, null, null, null); //TODO: should actually be sent by login page
    	new ActionPage(a);
    }
}

