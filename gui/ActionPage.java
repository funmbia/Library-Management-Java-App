package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import builder.RentalOrder;
import iterator.BookCollection;
import observer.*;
import command.Invoker;
import factory.HardcoverBook;
import factory.Magazine;
import factory.PhysicalItem;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionPage {
	public JFrame frame;
    public JPanel mainPanel;
    public JPanel firstPanel;
    public HashMap<String, Page> pages = new HashMap<>();
    public CardLayout cardLayout;
    private User user;
    public LibraryManagementSysInfo mainManagementSysInfo;
    private BookCollection bookCollection;

    public ActionPage(User user, boolean isLoggedIn) {
    	this.user = user;
    	this.bookCollection = new BookCollection();
    	
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //get the size of the screen
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        frame.setLocationRelativeTo(null);
        
        // Create and add all pages
        pages.put("Register new account", new RegisterPage());
        pages.put("Log in", new LogInPage());
        pages.put("logout", new LogInPage());
        pages.put("onlinebook", new OnlinebookPage(user, bookCollection));
        pages.put("rent", new RentPage(user));
        pages.put("newsletter", new NewsletterPage(user));
        pages.put("request", new RequestPage(user));
        pages.put("purchase", new PurchasePage(user));
        
        firstPanel = new JPanel(); 
        mainPanel = new JPanel();
        if(!isLoggedIn) {
        	               	
        	cardLayout = new CardLayout(); 
        	addContent();
        	firstPanel.setLayout(cardLayout);     
        	frame.add(firstPanel);
        	frame.setVisible(true);
        }
        else {	
        	cardLayout = new CardLayout(); 
        	addContent();
        	mainPanel.setLayout(cardLayout);
        	frame.add(mainPanel);
        	frame.setVisible(true);
        }

        //cardLayout.show(firstPanel, "main");
        cardLayout.show(mainPanel, "main");   
    }
    
    
    private void addContent() {
    	
   //GENERAL CONTENT
    	JButton Register = new JButton("Register new account");
    	Register.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
    	Register.addActionListener(e -> {
    		firstPanel.add(pages.get("Register new account").createPage(frame), "Register new account");
            cardLayout.show(firstPanel, "Register new account");
        });
        
        JButton logIn = new JButton("Log in");
        logIn.setFont(new Font("Microsoft PhagsPa", Font.ITALIC, 20));
        logIn.addActionListener(e -> {
        	firstPanel.add(pages.get("Log in").createPage(frame), "Log in");
            cardLayout.show(firstPanel, "Log in");
        });
    	
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
        JPanel generalPanel1 = new JPanel(new BorderLayout());//
        JPanel buttonPanel1 = new JPanel(new GridLayout(1, 2));
        buttonPanel1.add(Register);
        buttonPanel1.add(logIn);
        generalPanel1.add(buttonPanel1);
        firstPanel.add(generalPanel1, "main");//
        
        
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
        coursePanel.setBorder(new EmptyBorder(5, 20, 50, 50));
        mainManagementSysInfo = new LibraryManagementSysInfo();
        Student thisStudent;
        
        //STUDENT OPTION
    	if (user.getAccountType().toLowerCase() == "student") {
    		thisStudent = new Student(mainManagementSysInfo, user);
    		
    		List<Courses> courses = thisStudent.viewEnrolledCourses();
    		StringBuilder html = new StringBuilder("<html> <h3> Your Courses & Textbooks </h3> <ul> ");
    				html.append( "<li> Software Design </br> </li> ");
    				html.append("<a href=\"https://opentextbc.ca/comptech/\"> Current & Emerging Computing Technology </a>");
    		for (Courses c : courses) {
    			html.append("<li>");
    			html.append(c.getCourseName());
    			Textbook t = thisStudent.getTextbook(c);
    			html.append("<br>");
    			html.append("funny");
				html.append("<a href=" + t.getURL() + ">" + t.getTextName() + "</a>");
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
    		StringBuilder html = new StringBuilder("<html> <h3> Your Courses </h3> You're currently teaching: <ul>"
    				+ "<li> Software Designs </br> Textbook: Current & Emerging Computing Technology; First Editon");
    		html.append("</br>NOTE: a new edition is available");
    		for (Courses c : courses) {
    			html.append("<li>");
    			html.append(c.getCourseName() + " ");
    			html.append("</br> " + thisFaculty.getTextbook(c));
    			
//    			for (Textbook t : thisFaculty.getTextbooks(c)) {
//    				html.append("<br>");
//    				html.append(t.toString());
//    				allTextbooks.add(t);
//    			}
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
    	
    	user.getBorrowedItems();
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


    public static void main(String[] args) throws Exception {
    	//FOR TESTING PURPOSES:
    	
    	//TEST WARNINGS: add overdue & almost due item
    	//considered lost 
//    	RentalOrder lost = new RentalOrder(); 
//    	Calendar calendar = Calendar.getInstance();
//    	calendar.add(Calendar.DAY_OF_MONTH, -16); 
//    	Date manyDaysAgo = calendar.getTime();
//    	lost.setDueDate(manyDaysAgo);
//    	List<PhysicalItem> items = new ArrayList<>();
//    	PhysicalItem z = new PhysicalItem();
//    	z.setTitle("The Royal Insider");
//    	items.add(z);
//    	lost.setItems(items);
//    	
//    	//overdue
//    	RentalOrder overdue = new RentalOrder(); 
//    	calendar = Calendar.getInstance();
//    	calendar.add(Calendar.DAY_OF_MONTH, -2); 
//    	Date twoDaysAgo = calendar.getTime();
//    	overdue.setDueDate(twoDaysAgo);
//    	List<PhysicalItem> items2 = new ArrayList<>();
//    	PhysicalItem x = new PhysicalItem();
//    	x.setTitle("A Woman's Dream");
//    	items2.add(x);
//    	overdue.setItems(items2);
//    	
//    	//tonight
//    	RentalOrder almostDue = new RentalOrder(); 
//    	calendar = Calendar.getInstance();
//    	calendar.set(Calendar.HOUR_OF_DAY, 22); 
//    	calendar.set(Calendar.MINUTE, 1); 
//    	calendar.set(Calendar.SECOND, 1); 
//    	Date tonight = calendar.getTime();
//    	almostDue.setDueDate(tonight);
//    	List<PhysicalItem> items3 = new ArrayList<>();
//    	PhysicalItem y = new PhysicalItem();
//    	y.setTitle("A Child's Goal");
//    	items3.add(y);
//    	almostDue.setItems(items3);
//    
//
//    	ArrayList<RentalOrder> currentlyRenting = new ArrayList<RentalOrder>();
//    	currentlyRenting.add(lost);
//    	currentlyRenting.add(almostDue);
//    	currentlyRenting.add(overdue);
//    	
//    	
//    	Invoker myInvoker = new Invoker();
//    	User a = new User(0, 0, currentlyRenting, null, null, "Jane Doe", null, null, "non-faculty", myInvoker); 
//    	
//    	
//    	new ActionPage(a,true); //change to false to force login
 /**********************/
    	
    	
    	
    	//TEST COURSES AND TEXTBOOKS FOR STUDENT
//    	a = new User(0, 0, currentlyRenting, null, null, "Jane Doe", null, null, "student", myInvoker); 
//    	Student student = new Student(new LibraryManagementSysInfo(), a);
//    	Faculty faculty = new Faculty(new LibraryManagementSysInfo());
//    	Courses softwareDesign = new Courses("Software Design", new Faculty("Adam"), "1");
//    	Textbook textbook = new Textbook("Current & Emerging Computing Technology", "138801273878032", "First Editon", "https://opentextbc.ca/comptech/");
//    	
//    	student.enroll(softwareDesign);
//    	student.createVirtualCopies(softwareDesign, textbook);
//    	System.out.println(student.viewEnrolledCourses());
//    	
//    	new ActionPage((User)student,true); //change to false to force login
//    	/**********************/
    	
    	//When student withdraws... textbook is gone
//    	student.withdraw(softwareDesign);
//    	new ActionPage(student, true);
    	
    	
    	
//    	//TEST COURSES AND TEXTBOOKS FOR FACULTY
//    	a = new User(0, 0, currentlyRenting, null, null, "John Smith", null, null, "faculty", myInvoker); 
//    	Faculty faculty = new Faculty(new LibraryManagementSysInfo(), a);
//    	Courses softwareDesign = new Courses("Software Design", faculty, "1");
//    	Textbook textbook = new Textbook("Current & Emerging Computing Technology", "138801273878032", "First Editon", "https://opentextbc.ca/comptech/");
//    	
//    	faculty.teachCourse(softwareDesign);
//    	faculty.setTextbook(softwareDesign, textbook);
//    	
//    	Textbook unavailableTextbook = new Textbook("Computing Technologies", "138801273878032", "First Editon", "https://opentextbc.ca/comptech/");
//    	faculty.setTextbook(softwareDesign, unavailableTextbook); //should get notification
//    	
//    	new ActionPage(a,true); //change to false to force login
//    	/**********************/
    
    	Invoker myInvoker = new Invoker();
    	User a = new User(0, 0, null, null, null, "Jane Doe", null, null, "student", myInvoker); //TODO: should actually be sent by login page
    	new ActionPage(a,false);
    	
    }
}

