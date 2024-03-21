package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import iterator.BookCollection;
import observer.*;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionPage {
	public JFrame frame;
    public JPanel mainPanel;
    public HashMap<String, Page> pages = new HashMap<>();
    public CardLayout cardLayout;
    private User user;
    public LibraryManagementSysInfo mainManagementSysInfo;
    private BookCollection bookCollection;

    public ActionPage(User user) {
    	this.user = user;
    	this.bookCollection = new BookCollection();
    	
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the size of the screen
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        frame.setLocationRelativeTo(null);
        
        // Create and add all pages
        pages.put("logout", new LogInPage());
        pages.put("onlinebook", new OnlinebookPage(user, bookCollection));
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

        cardLayout.show(mainPanel, "main");   
    }
    
    
    private void addContent() {
    	
   //GENERAL CONTENT
        JLabel name = new JLabel("Welcome " + user.getName());
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 30));
        name.setBounds(70, 15, 800, 40);      
        JLabel type = new JLabel(user.getAccountType());
        type.setHorizontalAlignment(SwingConstants.CENTER);
        type.setFont(new Font("Microsoft PhagsPa", Font.ITALIC , 15));
        type.setBounds(70, 55, 800, 40);

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
        JPanel summaryPanel = warningsAndSummaries();
   
   //BUILD PAGE
        JPanel generalPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); //for button menu
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 30));
       
        buttonPanel.add(onlineBookButton);
        buttonPanel.add(rentButton);
        buttonPanel.add(newsletterButton);
        buttonPanel.add(requestButton);
        buttonPanel.add(purchaseButton);
        
        generalPanel.add(name, BorderLayout.NORTH);
        generalPanel.add(type, BorderLayout.NORTH);
        
        if (user.getAccountType().toLowerCase() == "faculty" || user.getAccountType().toLowerCase() == "student" ) {
        	JPanel coursePanel = coursesAndTextbooks();
            generalPanel.add(coursePanel, BorderLayout.CENTER);
        }
        
        generalPanel.add(logoutPanel, BorderLayout.NORTH);
        generalPanel.add(summaryPanel, BorderLayout.WEST);
        generalPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(generalPanel, "main"); //add all to page
    }
    
    
    private JPanel coursesAndTextbooks() {
    	JPanel coursePanel = new JPanel(new GridLayout(0, 1));
        coursePanel.setBorder(new EmptyBorder(5, 20, 50, 150));
        mainManagementSysInfo = new LibraryManagementSysInfo();
        
        //STUDENT OPTION
    	if (user.getAccountType().toLowerCase() == "student") {
    		Student thisStudent = new Student(mainManagementSysInfo, user);
    		
    		List<Courses> courses = thisStudent.viewEnrolledCourses();
    		StringBuilder html = new StringBuilder("<html> <h3> Your Courses & Textbooks </h3> <ul>");
    		for (Courses c : courses) {
    			html.append("<li>");
    			html.append(c.toString());
    			for (Textbook t : thisStudent.getTextbooks(c)) {
    				html.append("<br>");
    				html.append("<a href=" + t.getURL() + ">" + t.getTextName() + "</a>");
    				//html.append(t.getURL());
    			}
    			html.append("</li>");
    		}
    		html.append("</ul> </html>");
   
    		JLabel addCourses = new JLabel(html.toString());
    		coursePanel.add(addCourses);
    	}
    	
    	//FACULTY OPTION
    	else { 
    		Faculty thisFaculty = new Faculty(mainManagementSysInfo, user);
    		Set<Textbook> allTextbooks = new HashSet<>();
    		
    		List<Courses> courses = thisFaculty.getCoursesTeaching();
    		StringBuilder html = new StringBuilder("<html> <h3> Your Courses </h3> You're currently teaching: <ul>");
    		for (Courses c : courses) {
    			html.append("<li>");
    			html.append(c.toString());
    			for (Textbook t : thisFaculty.getTextbooks(c)) {
    				html.append("<br>");
    				html.append(t.toString());
    				allTextbooks.add(t);
    			}
    			html.append("</li>");
    		}
    		html.append("</ul> </html>");
   
    		JLabel addCourses = new JLabel(html.toString());
    		coursePanel.add(addCourses);
    		
    		//create notification for any new editions & unavailability
    		String notification = "";
    		for (Textbook text : allTextbooks) {
    			if (!text.isAvailable()) notification += text.getTextName() + "is unavailable!";
    			if (text.isNewEditionAvailable()) notification += "\nNew edition of " + text.getTextName() + " available!";
    		}
	        
    		
    		if (notification != "" ) {
    			JPanel panel = new JPanel();
    			panel.setPreferredSize(new Dimension(400, 100));
    			JOptionPane.showMessageDialog(null, panel, "NOTIFICATION \n" + notification, JOptionPane.PLAIN_MESSAGE);
    		}
    		 
    		
    	}
        
        return coursePanel;
    }
    
    
    private JPanel warningsAndSummaries() {
    	JPanel contentPanel = new JPanel(new GridLayout(0, 1));
    	contentPanel.setBorder(new EmptyBorder(5, 30, 50, 350));
    	
    	JLabel warnings = new JLabel(this.user.displayRentalWarnings());
    	warnings.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));
    	warnings.setForeground(Color.RED);
    	warnings.setBounds(6, 30, 500, 350);
    	contentPanel.add(warnings);
    	
    	this.user.getBorrowedItems();
    	StringBuilder builder;
    	
		if (! user.overDue.isEmpty() ) {
    		JLabel title = new JLabel("OVERDUE ITEMS");
	    	title.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));
	    	builder = new StringBuilder("<html> <ul>");
	    	for (String item : user.overDue) {
	    		builder.append("<li> " + item + "</li>");
	    	}
	    	builder.append("</ul> </html>");
	    	contentPanel.add(title);
	    	contentPanel.add(new JLabel(builder.toString()));
    	}
    	
		if (! user.almostDue.isEmpty() ) {
    		JLabel title = new JLabel("ITEMS THAT ARE ALMOST DUE");
	    	title.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));
	    	builder = new StringBuilder("<html> <ul>");
	    	for (String item : user.almostDue) {
	    		builder.append("<li> " + item + "</li>");
	    	}
	    	builder.append("</ul> </html>");
	    	contentPanel.add(title);
	    	contentPanel.add(new JLabel(builder.toString()));
    	}
		
		if (! user.notYetDue.isEmpty() ) {
    		JLabel title = new JLabel("OTHER CURRENTLY RENTED ITEMS");
	    	title.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 15));
	    	builder = new StringBuilder("<html> <ul>");
	    	for (String item : user.notYetDue) {
	    		builder.append("<li> " + item + "</li>");
	    	}
	    	builder.append("</ul> </html>");
	    	contentPanel.add(title);
	    	contentPanel.add(new JLabel(builder.toString()));
    	}

    
    	return contentPanel;

    }


    public static void main(String[] args) {
    	Invoker myInvoker = new Invoker();
    	User a = new User(0, 0, null, null, null, "Jane Doe", null, null, "student", myInvoker); //TODO: should actually be sent by login page
    	new ActionPage(a);
    }
}

